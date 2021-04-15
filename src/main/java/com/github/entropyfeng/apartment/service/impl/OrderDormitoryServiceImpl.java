package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.dao.*;
import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.domain.po.DormitoryResident;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.domain.to.ResidentBedVersion;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.exception.AlreadyCheckInException;
import com.github.entropyfeng.apartment.exception.BusinessParaException;
import com.github.entropyfeng.apartment.service.BuildingService;
import com.github.entropyfeng.apartment.service.OrderDormitoryService;
import com.github.entropyfeng.common.code.ErrorCode;
import com.github.entropyfeng.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderDormitoryServiceImpl implements OrderDormitoryService {

    Logger logger = LoggerFactory.getLogger(OrderDormitoryServiceImpl.class);
    final
    CampusGroupDao campusGroupDao;
    final
    CampusDao campusDao;
    final
    BuildingDao buildingDao;

    final StudentDao studentDao;
    final
    DormitoryDao dormitoryDao;
    final DormitoryResidentDao dormitoryResidentDao;
    final BuildingService buildingService;


    @Autowired
    public OrderDormitoryServiceImpl(StudentDao studentDao, BuildingService buildingService, CampusGroupDao campusGroupDao, CampusDao campusDao, BuildingDao buildingDao, DormitoryDao dormitoryDao, DormitoryResidentDao dormitoryResidentDao) {
        this.campusGroupDao = campusGroupDao;
        this.campusDao = campusDao;
        this.studentDao = studentDao;
        this.buildingDao = buildingDao;
        this.dormitoryDao = dormitoryDao;
        this.dormitoryResidentDao = dormitoryResidentDao;
        this.buildingService = buildingService;
    }

    @Override
    public List<String> filterAvailableCampusName(@NotNull String residentId) {
        return campusDao.queryAllCampusName();
    }

    @Override
    public List<String> filterAvailableCampusGroup(@NotNull String residentId, @NotNull String campusName) {


        InGender inGender = acquireInGender(residentId);
        if (inGender == null) {
            return new ArrayList<>();
        }
        return campusGroupDao.queryAvailableCampusAndGroupName(campusName, inGender, InGender.MIX);
    }

    public List<String> filterAvailableBuildingName(@NotNull String residentId, @NotNull String campusGroupName) {
        InGender gender = acquireInGender(residentId);

        return buildingDao.queryAvailableBuildingName(campusGroupName, gender);

    }

    @Override
    public List<DormitoryVO> filterAvailableDormitory(@NotNull String residentId, @NotNull String buildingName) {

        InGender gender = acquireInGender(residentId);
        List<Dormitory> dormitoryList = dormitoryDao.queryFilterDormitoryByBuildingName(buildingName, gender);
        return postHandlerDormitoryList(dormitoryList);
    }


    @Override
    public Boolean hasInDormitory(@NotNull String residentId) {
        return dormitoryResidentDao.querySingleResidentStatus(residentId);
    }

    /**
     * 预定寝室
     * @param residentId  用户名
     * @param dormitoryId 寝室Id
     * @param bedId       床位号
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void checkInDormitory(@NotNull String residentId, @NotNull Integer dormitoryId, @NotNull Integer bedId) throws AlreadyCheckInException {

        DormitoryResident resident = dormitoryResidentDao.queryDormitoryResidentByResidentId(residentId);
        if (resident != null) {
            if (logger.isWarnEnabled()) {
                logger.warn("resident -> residentId {} already check in", residentId);
            }
            throw new BusinessException(residentId + "已经存在住宿信息", ErrorCode.REQUEST_PARA_ERROR);
        }
        Dormitory dormitory = dormitoryDao.queryDormitoryByDormitoryId(dormitoryId);
        if (dormitory == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("dormitory's dormitoryId {} not exist", dormitoryId);
            }
            throw new BusinessParaException("dormitory's dormitoryId ->" + dormitoryId + " not exist");
        }


        Integer currentCapacity = dormitory.getCurrentCapacity();
        if (currentCapacity < dormitory.getTotalCapacity()) {
                /*
                  创建时生成skeleton,一定不为null
                 */
            ResidentBedVersion residentBedVersion = dormitoryResidentDao.queryCurrentBedInfo(dormitoryId, bedId);
            String currentResidentId = residentBedVersion.getResidentId();
            if (currentResidentId == null) {
                Long residentVersion = residentBedVersion.getVersion();

                Integer count = dormitoryResidentDao.updateDormitoryResidentRelyVersion(dormitoryId, bedId, residentId, residentVersion);
                if (count.equals(1)) {
                    Long dormitoryVersion = dormitory.getVersion();
                    Integer dormitoryCount = dormitoryDao.updateCurrentCapacityRelyVersion(dormitoryId, currentCapacity + 1, dormitoryVersion);
                    if (dormitoryCount.equals(1)) {
                        return;
                    }
                }
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }


    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void checkOutDormitory(@NotNull String residentId) {

        Dormitory dormitory = dormitoryDao.queryDormitoryByResidentId(residentId);
        if (dormitory == null) {
            throw new BusinessException("residentId :" + residentId + " not check in", ErrorCode.REQUEST_PARA_ERROR);
        }
        boolean safe = checkIsLegalCapacity(dormitory.getTotalCapacity(), dormitory.getCurrentCapacity());
        if (!safe) {
            logger.error("dataBase integrity break in check dormitory Capacity ,current residentId is {}", residentId);
        }

        DormitoryResident resident = dormitoryResidentDao.queryDormitoryResidentByResidentId(residentId);

        if (resident.getDormitoryId().equals(dormitory.getDormitoryId())) {
            Integer res1 = dormitoryDao.updateCurrentCapacityRelyDoubleVersion(dormitory.getDormitoryId(), dormitory.getCurrentCapacity() - 1, resident.getVersion(), dormitory.getVersion());
            Integer res2 = dormitoryResidentDao.updateQuitDormitoryRelyDoubleVersion(dormitory.getDormitoryId(), residentId, dormitory.getVersion() + 1, resident.getVersion());
            if (res1.equals(1) && res1.equals(res2)) {
                return;
            }
        }

        throw new BusinessException("业务繁忙，请刷新重试", ErrorCode.REQUEST_PARA_ERROR);
    }


    private List<DormitoryVO> postHandlerDormitoryList(List<Dormitory> dormitoryList) {

        Map<Integer, BuildingAndGroup> map = buildingService.acquireBuildingAndGroupMap();
        return dormitoryList.stream().map(dormitory -> new DormitoryVO(dormitory, map.get(dormitory.getBuildingId()))).collect(Collectors.toList());

    }

    private InGender acquireInGender(String residentId) {

        Gender gender = studentDao.queryStudentGenderByStudentId(residentId);
        InGender inGender;
        switch (gender) {
            case MAN:
                inGender = InGender.MAN;
                break;
            case WOMAN:
                inGender = InGender.WOMAN;
                break;
            default: {
                if (logger.isWarnEnabled()) {
                    logger.warn("residentId {} 's gender is unknown ", residentId);
                }
                throw new BusinessParaException(String.format("residentId %s 's gender is unknown ", residentId));

            }
        }
        return inGender;
    }

    private static boolean checkIsLegalCapacity(int maxCapacity, int currentCapacity) {

        return currentCapacity <= maxCapacity && currentCapacity >= 0;
    }
}

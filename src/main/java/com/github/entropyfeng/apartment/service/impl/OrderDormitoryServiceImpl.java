package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.dao.*;
import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.domain.po.DormitoryBlock;
import com.github.entropyfeng.apartment.domain.po.DormitoryResident;
import com.github.entropyfeng.apartment.domain.po.ResidentBlock;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.domain.to.ResidentBedVersion;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.exception.AlreadyCheckInException;
import com.github.entropyfeng.apartment.exception.BusinessParaException;
import com.github.entropyfeng.apartment.service.ApartmentScheduleService;
import com.github.entropyfeng.apartment.service.BuildingService;
import com.github.entropyfeng.apartment.service.OrderDormitoryService;
import com.github.entropyfeng.common.code.ErrorCode;
import com.github.entropyfeng.common.exception.BusinessException;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 管理员预定公寓/学生预定公寓的Service
 */
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

    final ApartmentScheduleService scheduleService;
    final ApartmentScheduleDao scheduleDao;


    @Autowired
    public OrderDormitoryServiceImpl(StudentDao studentDao, BuildingService buildingService, CampusGroupDao campusGroupDao, CampusDao campusDao, BuildingDao buildingDao, DormitoryDao dormitoryDao, DormitoryResidentDao dormitoryResidentDao,ApartmentScheduleService apartmentScheduleService,ApartmentScheduleDao scheduleDao) {
        this.campusGroupDao = campusGroupDao;
        this.campusDao = campusDao;
        this.studentDao = studentDao;
        this.buildingDao = buildingDao;
        this.dormitoryDao = dormitoryDao;
        this.dormitoryResidentDao = dormitoryResidentDao;
        this.buildingService = buildingService;
        this.scheduleDao=scheduleDao;
        this.scheduleService=apartmentScheduleService;
    }

    /**
     * 过滤所有可能的校区名
     * @param roleNames 当前的角色集合
     * @param residentId 当前将要入住的人员ID
     * @return 包含当前所有可行的校区名的List
     */
    @Override
    public List<String> filterAvailableCampusName(@NotEmpty List<String> roleNames, @NotNull String residentId) {
        return campusDao.queryAllCampusName();
    }

    /**
     * 过滤所有可能的校区组名
     * @param roleNames 当前的角色集合
     * @param campusName 校区名
     * @param residentId 当前将要入住的人员ID
     * @return 包含当前所有可行的校区组名的List
     */
    @Override
    public List<String> filterAvailableCampusGroupName(@NotEmpty List<String> roleNames, @NotNull String residentId, @NotNull String campusName) {


        if (isAdmin(roleNames)) {
            return campusGroupDao.queryCampusGroupNamesByCampusName(campusName);
        }
        InGender inGender = acquireInGender(residentId);
        if (inGender == null) {
            return new ArrayList<>();
        }
        return campusGroupDao.queryAvailableCampusAndGroupName(campusName, inGender, InGender.MIX);
    }
    /**
     * 过滤所有可能的楼栋名
     * @param roleNames 当前的角色集合
     * @param campusGroupName 小区组名
     * @param residentId 当前将要入住的人员ID
     * @return 包含当前所有可行的楼栋名的List
     */
    public List<String> filterAvailableBuildingName(@NotEmpty List<String> roleNames, @NotNull String residentId, @NotNull String campusGroupName) {

        if (isAdmin(roleNames)) {
            return buildingDao.queryBuildingNamesByCampusGroupName(campusGroupName);
        }

        InGender gender = acquireInGender(residentId);
        return buildingDao.queryAvailableBuildingName(campusGroupName, gender);

    }

    @Override
    public List<DormitoryVO> filterAvailableDormitory(List<DormitoryBlock> blocks) {

      Map<Integer,List<Integer>> bedIdMap=  blocks.stream().collect(Collectors.toMap(DormitoryBlock::getDormitoryId,DormitoryBlock::getBedIds));
      List<Integer> dorIds= blocks.stream().map(DormitoryBlock::getDormitoryId).collect(Collectors.toList());

      List<Dormitory> dormitoryList=dormitoryDao.queryBatchDormitory(dorIds);
  /*    //dormitoryList.stream().filter(dormitory -> dormitory.getTargetResident())
        dormitoryList.forEach(dormitory -> {
          List<ResidentBlock> afterFilter=  dormitory.getTargetResident().stream().filter(residentBlock -> bedIdMap.get(dormitory.getDormitoryId()).contains(residentBlock.getBedId())).collect(Collectors.toList());
          dormitory.setTargetResident(afterFilter);
        });*/

      return null;
    }

    /**
     * 过滤所有可能的公寓
     * @param roleNames 当前的角色集合
     * @param buildingName 楼名
     * @param residentId 当前将要入住的人员ID
     * @return 包含当前所有可行的寝室名的List
     */
    @Override
    public List<DormitoryVO> filterAvailableDormitory(@NotEmpty List<String> roleNames, @NotNull String residentId, @NotNull String buildingName) {

        InGender gender = acquireInGender(residentId);
        List<Dormitory> dormitoryList = dormitoryDao.queryFilterDormitoryByBuildingName(buildingName, gender);
        return postHandlerDormitoryList(dormitoryList);
    }


    /**
     * 判断该Id是否住进公寓
     * @param residentId 居住人ID
     * @return true->已经入住 false->未入住
     */
    @Override
    public Boolean hasInDormitory(@NotNull String residentId) {
        Boolean res = dormitoryResidentDao.querySingleResidentStatus(residentId);
        if (res == null) {
            return Boolean.FALSE;
        }
        return res;
    }

    @Override
    public Boolean hasDormitory(String residentId) {

        return null;
    }


    /**
     * 预定寝室，同时更新床位表和公寓表
     * @param residentId  用户名
     * @param dormitoryId 寝室Id
     * @param bedId       床位号
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void checkInDormitory(@NotNull String residentId, @NotNull Integer dormitoryId, @NotNull Integer bedId) throws AlreadyCheckInException {

        if (logger.isInfoEnabled()) {
            logger.info("resident {} try to check in dormitory {} bed {}", residentId, dormitoryId, bedId);
        }
        DormitoryResident resident = dormitoryResidentDao.queryDormitoryResidentByResidentId(residentId);
        if (resident != null) {
            if (logger.isWarnEnabled()) {
                logger.warn("resident -> residentId {} already check in", residentId);
            }
            throw new BusinessParaException(residentId + "已经存在住宿信息");
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
               获取当前床位，当前宿舍的具体信息
            */
            ResidentBedVersion residentBedVersion = dormitoryResidentDao.queryCurrentBedInfo(dormitoryId, bedId);
            String currentResidentId = residentBedVersion.getResidentId();
            if (currentResidentId == null) {
                //resident表的version
                Long residentVersion = residentBedVersion.getVersion();
                //公寓表的version
                Long dormitoryVersion = dormitory.getVersion();

                //同时更新公寓容量与床位信息
                Integer res = dormitoryResidentDao.updateInDorRelyDoubleVersion(dormitoryId, bedId, currentCapacity + 1, residentId, dormitoryVersion, residentVersion);
                if (res.equals(2)) {
                   return;
                }

                throw new BusinessParaException("该空床位不存在");
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("resident {} fail to check in dormitory {} bed {}", residentId, dormitoryId, bedId);
        }
        throw new BusinessParaException("房间已满");

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void checkInDormitory(String scheduleName,String residentId, Integer dormitoryId, Integer bedId) throws AlreadyCheckInException {

        if (logger.isInfoEnabled()) {
            logger.info("resident {} try to check in dormitory {} bed {}", residentId, dormitoryId, bedId);
        }
       boolean isScheduleExist=scheduleService.checkAvailable(residentId,bedId,dormitoryId,scheduleName);

       if (isScheduleExist){
          boolean isInDor= scheduleDao.queryHasDormitory(residentId,scheduleName);
          if (isInDor){
              throw new AlreadyCheckInException();
          }
          // dormitoryDao.updateTargetResident(dormitoryId,bedId,residentId);
       }

    }

    /**
     * 退宿
     * @param residentId 居住者Id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void checkOutDormitory(@NotNull String residentId) {

        if (logger.isInfoEnabled()) {
            logger.info("resident {} try check out dormitory", residentId);
        }
        Dormitory dormitory = dormitoryDao.queryDormitoryByResidentId(residentId);
        if (dormitory == null) {
            throw new BusinessException("residentId :" + residentId + " not check in but try to check out", ErrorCode.REQUEST_PARA_ERROR);
        }
        boolean safe = checkIsLegalCapacity(dormitory.getTotalCapacity(), dormitory.getCurrentCapacity());
        if (!safe) {
            logger.error("dataBase integrity break in check dormitory Capacity ,current residentId is {}", residentId);
        }

        DormitoryResident resident = dormitoryResidentDao.queryDormitoryResidentByResidentId(residentId);


        if (resident.getDormitoryId().equals(dormitory.getDormitoryId())) {
           int res= dormitoryResidentDao.updateInDorRelyDoubleVersion(dormitory.getDormitoryId(), resident.getBedId(), dormitory.getCurrentCapacity() - 1,null,dormitory.getVersion(),resident.getVersion());

           if (res==2){
               return;
           }
        }

        throw new BusinessException("业务繁忙，请刷新重试", ErrorCode.REQUEST_PARA_ERROR);
    }


    private List<DormitoryVO> postHandlerDormitoryList(List<Dormitory> dormitoryList) {

        Map<Integer, BuildingAndGroup> map = buildingService.acquireBuildingAndGroupMap();
        return dormitoryList.stream().map(dormitory -> new DormitoryVO(dormitory, map.get(dormitory.getBuildingId()))).collect(Collectors.toList());

    }

    /**
     * 获取性别并转换
     * @param residentId 居住人ID
     * @return 居住性别
     */
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

    /**
     * 判断当前容量是否合法
     * @param maxCapacity 最大容量
     * @param currentCapacity 当前容量
     * @return true->合法; false->非法
     */
    private static boolean checkIsLegalCapacity(int maxCapacity, int currentCapacity) {

        return currentCapacity <= maxCapacity && currentCapacity >= 0;
    }

    private boolean isAdmin(List<String> strings) {
        return strings.contains("administrator");
    }
}

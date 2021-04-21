package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.vo.DetailDormitory;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface DormitoryService {


    void addNewDormitory(DormitoryVO simpleDormitoryVO);
    void deleteDormitoryByDormitoryName(String dormitoryName);
    List<DormitoryVO> queryAllDormitories();
    int acquireDormitoryNum();
    int acquireResidentNum();
    int acquireWomanBedNum();
    int acquireManBedNum();
    int acquireBedNum();
    DetailDormitory queryMyDormitory(String username);
    DetailDormitory queryDetailDormitory(Integer dormitoryId,List<String> roleList);
    List<DormitoryVO> queryDormitory(DormitoryVO simpleDormitoryVO);
    List<DormitoryVO> queryDormitories(Integer buildingId);
    List<DormitoryVO> queryDormitories(String buildingName);
    DormitoryVO queryDormitory(@NotNull Integer dormitoryId);
    DormitoryVO queryDormitory(@NotNull String dormitoryName);


}

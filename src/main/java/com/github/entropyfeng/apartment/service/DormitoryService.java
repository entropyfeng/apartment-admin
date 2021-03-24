package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.domain.vo.SimpleDormitoryVO;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface DormitoryService {


    void addNewDormitory(SimpleDormitoryVO simpleDormitoryVO);
    List<SimpleDormitoryVO> queryAllDormitories();
    List<SimpleDormitoryVO> queryDormitories(Integer buildingId);
    SimpleDormitoryVO querySimpleDormitory(@NotNull Integer dormitoryId);
    SimpleDormitoryVO querySimpleDormitory(@NotNull String dormitoryName);
    DormitoryVO acquireDormitory(@NotNull String dormitoryName);
    DormitoryVO acquireUserDormitory(@NotNull String residentId);

}

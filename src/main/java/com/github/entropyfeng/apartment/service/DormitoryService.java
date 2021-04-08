package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.vo.DetailDormitory;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface DormitoryService {


    void addNewDormitory(DormitoryVO simpleDormitoryVO);
    List<DormitoryVO> queryAllDormitories();

    DetailDormitory queryMyDormitory(String username);
    List<DormitoryVO> queryDormitory(DormitoryVO simpleDormitoryVO);
    List<DormitoryVO> queryDormitories(Integer buildingId);
    DormitoryVO queryDormitory(@NotNull Integer dormitoryId);
    DormitoryVO queryDormitory(@NotNull String dormitoryName);


}

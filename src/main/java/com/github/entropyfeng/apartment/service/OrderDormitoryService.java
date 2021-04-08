package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrderDormitoryService {


    List<DormitoryVO> filterAvailableDormitory(@NotNull String residentId, @NotNull String buildingName);

    List<String> filterAvailableCampusGroup(@NotNull String residentId,@NotNull String campusName);

    List<String> filterAvailableCampusName(@NotNull String residentId);

    List<String> filterAvailableBuildingName(@NotNull String residentId,@NotNull String campusGroupName);

    Boolean hasInDormitory(@NotNull String residentId);

    Boolean checkInDormitory(@NotNull String residentId,@NotNull Integer dormitoryId,@NotNull Integer bedId);

    Boolean checkInDormitory(@NotNull String residentId,@NotNull Integer dormitoryId);

    Boolean quitDormitory(@NotNull String residentId);

}


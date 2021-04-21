package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.exception.AlreadyCheckInException;
import com.github.entropyfeng.apartment.exception.AlreadyCheckOutException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrderDormitoryService {


    List<DormitoryVO> filterAvailableDormitory(@NotEmpty List<String> roleNames,@NotNull String residentId, @NotNull String buildingName);

    List<String> filterAvailableCampusGroupName(@NotEmpty List<String> roleNames, @NotNull String residentId, @NotNull String campusName);

    List<String> filterAvailableCampusName(@NotEmpty List<String> roleNames, @NotNull String residentId);


    List<String> filterAvailableBuildingName(@NotEmpty List<String> roleNames,@NotNull String residentId,@NotNull String campusGroupName);

    Boolean hasInDormitory(@NotNull String residentId);

    void checkInDormitory(@NotNull String residentId,@NotNull Integer dormitoryId,@NotNull Integer bedId)throws AlreadyCheckInException;

    /**
     * 为什么通过异常返回结果？
     * 当内部乐观锁出现版本冲突时，抛出异常，由声明式事务进行回滚
     * 若作为分布式事务的一个分支，则分布式事务的所有调用链都进行回滚
     * @param residentId 居住者Id
     */
    void checkOutDormitory(@NotNull String residentId);

}


package com.github.entropyfeng.apartment.dao;


import com.github.entropyfeng.apartment.domain.po.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollegeDao {

    void insertCollege(@Param("collegeId") Integer collegeId, @Param("collegeName") String collegeName);

    void truncateCollege();

    void deleteCollegeByCollegeName(@Param("collegeName") String collegeName);

    void deleteCollegeByCollegeId(@Param("collegeId") String collegeId);

    College queryCollegeByCollegeName(@Param("collegeName") String collegeName);

    College queryCollegeByCollegeId(@Param("collegeId") String collegeId);

    Boolean selectCollegeStatusByCollegeName(@Param("collegeName") String collegeName);
    List<College> queryAllCollege();
}

package com.github.entropyfeng.apartment.service;

public interface ApartmentIdService {

    String CAMPUS_ID_FIELD = "campus_id";

    String CAMPUS_GROUP_ID_FIELD = "c_g_id";
    String BUILDING_ID_FIELD = "b_id";
    String DORMITORY_ID_FIELD = "d_id";

    Integer getNextCampusId();

    Integer getNextCampusGroupId();

    Integer getNextBuildingId();

    Integer getNextDormitoryId();

    void clearAllIds();
}

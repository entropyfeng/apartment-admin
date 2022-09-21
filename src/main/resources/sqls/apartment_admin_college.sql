create table college
(
    id                  int auto_increment
        primary key,
    college_id          int                                null,
    college_name        varchar(255)                       null,
    college_description varchar(255)                       null,
    create_time         datetime default CURRENT_TIMESTAMP null,
    update_time         datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint department_department_id_uindex
        unique (college_id)
);

INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (1, 1, '物理学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (2, 2, '电器学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (3, 3, '数学学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (4, 4, '电子学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (5, 5, '马克思学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (6, 6, '文学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (7, 7, '航空学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (8, 8, '医学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (9, 9, '农学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (10, 10, '机械学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (11, 11, '计算机学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (12, 12, '软件学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (13, 13, '化学学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
INSERT INTO `apartment_admin`.college (id, college_id, college_name, college_description, create_time, update_time) VALUES (14, 14, '能源学院', null, '2021-07-02 07:05:01', '2021-07-02 07:05:01');
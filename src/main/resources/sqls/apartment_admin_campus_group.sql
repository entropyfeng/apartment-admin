create table campus_group
(
    id                       int auto_increment
        primary key,
    campus_group_id          int                                null,
    campus_name              varchar(255)                       null,
    in_gender                tinyint                            null,
    campus_group_name        varchar(255)                       null,
    campus_group_description varchar(255)                       null,
    create_time              datetime default CURRENT_TIMESTAMP null,
    update_time              datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (1, 1, '南京校区', 1, '南京桃苑', '南京桃苑', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (2, 2, '南京校区', 2, '南京李苑', '南京李苑', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (3, 3, '北京校区', 3, '北京北苑', '北京北苑', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (4, 4, '北京校区', 3, '北京西苑', '北京西苑', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (5, 5, '天津校区', 2, '天津一区', '天津一区', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (6, 6, '天津校区', 1, '天津二区', '天津二区', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (7, 7, '天津校区', 3, '天津三区', '天津三区', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (8, 8, '天津校区', 1, '天津四区', '天津四区', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (9, 9, '西安校区', 1, '长安', '长安', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (10, 10, '西安校区', 3, '未央', '未央', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus_group (id, campus_group_id, campus_name, in_gender, campus_group_name, campus_group_description, create_time, update_time) VALUES (11, 11, '广州校区', 3, '广州桃苑', '广州桃苑', '2021-07-02 07:24:38', '2021-07-02 07:24:38');
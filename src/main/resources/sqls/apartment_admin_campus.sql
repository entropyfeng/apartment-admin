create table campus
(
    id                 bigint auto_increment
        primary key,
    campus_name        varchar(255)                       null,
    campus_description varchar(255)                       null,
    create_time        datetime default CURRENT_TIMESTAMP null,
    update_time        datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

INSERT INTO `apartment_admin`.campus (id, campus_name, campus_description, create_time, update_time) VALUES (1, '南京校区', '这是南京校区', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus (id, campus_name, campus_description, create_time, update_time) VALUES (2, '北京校区', '这是北京校区', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus (id, campus_name, campus_description, create_time, update_time) VALUES (3, '天津校区', '这是天津校区', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus (id, campus_name, campus_description, create_time, update_time) VALUES (4, '西安校区', '这是西安校区', '2021-07-02 07:05:21', '2021-07-02 07:05:21');
INSERT INTO `apartment_admin`.campus (id, campus_name, campus_description, create_time, update_time) VALUES (7, '广州校区', '这是广州校区', '2021-07-02 07:23:21', '2021-07-02 07:23:21');
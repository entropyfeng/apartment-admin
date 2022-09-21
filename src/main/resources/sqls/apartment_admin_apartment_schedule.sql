create table apartment_schedule
(
    id                    int auto_increment,
    schedule_name         varchar(255)                       null,
    schedule_year         varchar(20)                        null,
    schedule_description  varchar(2000)                      null,
    begin_time            datetime                           null,
    end_time              datetime                           null,
    create_time           datetime default CURRENT_TIMESTAMP null,
    update_time           datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    enable_schedule       tinyint                            null,
    target_apartment      json                               null,
    target_result         json                               null,
    target_student_num    int                                null,
    target_bed            json                               null,
    target_student_gender tinyint                            null,
    target_bed_num        int                                null,
    target_student_id     json                               null,
    constraint apartment_schedule_id_uindex
        unique (id),
    constraint apartment_schedule_schedule_name_uindex
        unique (schedule_name)
);

alter table apartment_schedule
    add primary key (id);

INSERT INTO `apartment_admin`.apartment_schedule (id, schedule_name, schedule_year, schedule_description, begin_time, end_time, create_time, update_time, enable_schedule, target_apartment, target_result, target_student_num, target_bed, target_student_gender, target_bed_num, target_student_id) VALUES (7, '2020男博士搬迁', '2020', null, '2021-10-04 08:40:26', '2021-10-30 10:42:28', '2021-10-30 08:40:35', '2021-10-30 08:40:35', null, '[]', '[]', null, '[]', 1, null, '[]');
INSERT INTO `apartment_admin`.apartment_schedule (id, schedule_name, schedule_year, schedule_description, begin_time, end_time, create_time, update_time, enable_schedule, target_apartment, target_result, target_student_num, target_bed, target_student_gender, target_bed_num, target_student_id) VALUES (8, '2020女博士搬迁', '2020', null, '2021-10-04 08:40:26', '2021-10-30 10:42:28', '2021-10-30 08:40:47', '2021-10-30 08:40:47', null, '[]', '[]', null, '[]', 2, null, '[]');
INSERT INTO `apartment_admin`.apartment_schedule (id, schedule_name, schedule_year, schedule_description, begin_time, end_time, create_time, update_time, enable_schedule, target_apartment, target_result, target_student_num, target_bed, target_student_gender, target_bed_num, target_student_id) VALUES (9, '2020女硕士搬迁', '2020', null, '2021-10-04 08:40:26', '2021-10-30 10:42:28', '2021-10-30 08:40:59', '2021-10-30 08:40:59', null, '[]', '[]', null, '[]', 2, null, '[]');
INSERT INTO `apartment_admin`.apartment_schedule (id, schedule_name, schedule_year, schedule_description, begin_time, end_time, create_time, update_time, enable_schedule, target_apartment, target_result, target_student_num, target_bed, target_student_gender, target_bed_num, target_student_id) VALUES (10, '2020男硕士搬迁', '2020', null, '2021-10-04 08:40:26', '2021-10-30 10:42:28', '2021-10-30 08:41:12', '2021-10-30 08:41:12', null, '[]', '[]', null, '[]', 1, null, '[]');
INSERT INTO `apartment_admin`.apartment_schedule (id, schedule_name, schedule_year, schedule_description, begin_time, end_time, create_time, update_time, enable_schedule, target_apartment, target_result, target_student_num, target_bed, target_student_gender, target_bed_num, target_student_id) VALUES (11, '2021男硕士搬迁', '2020', null, '2021-10-04 08:40:26', '2021-10-30 10:42:28', '2021-10-30 08:45:36', '2021-10-30 08:45:36', null, '[]', '[]', null, '[]', 1, null, '[]');
INSERT INTO `apartment_admin`.apartment_schedule (id, schedule_name, schedule_year, schedule_description, begin_time, end_time, create_time, update_time, enable_schedule, target_apartment, target_result, target_student_num, target_bed, target_student_gender, target_bed_num, target_student_id) VALUES (12, '2021女硕士搬迁', '2021', null, '2022-10-12 16:00:57', '2022-10-13 08:57:54', '2021-10-30 08:53:04', '2021-10-30 08:53:04', null, '[]', '[]', null, '[]', 2, null, '[]');
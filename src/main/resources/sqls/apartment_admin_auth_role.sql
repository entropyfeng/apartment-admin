create table auth_role
(
    id               bigint unsigned auto_increment
        primary key,
    auth_role_id     bigint                             null,
    auth_role_name   varchar(100)                       null,
    auth_status      varchar(50)                        null,
    auth_description varchar(255)                       null,
    create_time      datetime default CURRENT_TIMESTAMP null,
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint auth_role_auth_role_id_uindex
        unique (auth_role_id),
    constraint auth_role_auth_role_name_uindex
        unique (auth_role_name)
);

INSERT INTO `apartment_admin`.auth_role (id, auth_role_id, auth_role_name, auth_status, auth_description, create_time, update_time) VALUES (1, 1, 'base', null, null, '2021-07-02 07:06:08', '2021-07-02 07:06:08');
INSERT INTO `apartment_admin`.auth_role (id, auth_role_id, auth_role_name, auth_status, auth_description, create_time, update_time) VALUES (2, 2, 'administrator', null, null, '2021-07-02 07:06:08', '2021-07-02 07:06:08');
INSERT INTO `apartment_admin`.auth_role (id, auth_role_id, auth_role_name, auth_status, auth_description, create_time, update_time) VALUES (3, 3, 'student', null, null, '2021-07-02 07:06:08', '2021-07-02 07:06:08');
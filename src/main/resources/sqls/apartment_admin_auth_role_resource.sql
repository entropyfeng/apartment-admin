create table auth_role_resource
(
    id               bigint unsigned auto_increment
        primary key,
    auth_role_id     bigint                             not null,
    auth_resource_id bigint                             not null,
    create_time      datetime default CURRENT_TIMESTAMP not null,
    update_time      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint auth_role_resource_auth_role_id_auth_resource_id_uindex
        unique (auth_role_id, auth_resource_id)
);

INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (1, 1, 4, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (2, 1, 5, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (3, 1, 6, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (4, 1, 7, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (5, 2, 1, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (6, 2, 2, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (7, 2, 3, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (8, 2, 4, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (9, 2, 8, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (10, 2, 12, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (11, 2, 21, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (12, 2, 14, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (13, 2, 17, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (14, 2, 10, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (15, 2, 18, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (16, 2, 15, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (17, 2, 19, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (18, 2, 11, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (19, 2, 20, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (20, 2, 9, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (21, 2, 13, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (22, 2, 16, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (23, 2, 22, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (24, 2, 36, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (25, 2, 37, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (26, 2, 23, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (27, 2, 44, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (28, 2, 25, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (29, 2, 26, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (30, 2, 24, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (31, 2, 46, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (32, 2, 27, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (33, 2, 28, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (34, 2, 29, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (35, 2, 30, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (36, 2, 45, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (37, 2, 43, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (38, 2, 39, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (39, 2, 40, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (40, 2, 42, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (41, 2, 41, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (42, 2, 38, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (43, 2, 47, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (44, 2, 48, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (45, 2, 49, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (46, 2, 31, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (47, 2, 32, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (48, 3, 4, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (49, 3, 26, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (50, 3, 27, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (51, 3, 28, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (52, 3, 29, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (53, 3, 30, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (54, 3, 25, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (55, 3, 33, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (56, 3, 34, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_role_resource (id, auth_role_id, auth_resource_id, create_time, update_time) VALUES (57, 3, 35, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
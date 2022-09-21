create table auth_user_role
(
    id           bigint unsigned auto_increment
        primary key,
    auth_role_id bigint                             not null,
    auth_user_id bigint                             not null,
    create_time  datetime default CURRENT_TIMESTAMP not null,
    update_time  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint auth_user_role_auth_user_id_auth_role_id_uindex
        unique (auth_user_id, auth_role_id)
);

INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (1, 1, 1, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (2, 2, 1, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (3, 3, 2, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (4, 3, 3, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (5, 3, 4, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (6, 3, 5, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (7, 3, 6, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (8, 3, 7, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (9, 3, 8, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (10, 3, 9, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (11, 3, 10, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (12, 3, 11, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (13, 3, 12, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (14, 3, 13, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (15, 3, 14, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (16, 3, 15, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (17, 3, 16, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (18, 3, 17, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (19, 3, 18, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (20, 3, 19, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (21, 3, 20, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (22, 3, 21, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (23, 3, 22, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (24, 3, 23, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (25, 3, 24, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (26, 3, 25, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (27, 3, 26, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (28, 3, 27, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (29, 3, 28, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (30, 3, 29, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (31, 3, 30, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (32, 3, 31, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (33, 3, 32, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (34, 3, 33, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (35, 3, 34, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (36, 3, 35, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (37, 3, 36, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (38, 3, 37, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (39, 3, 38, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (40, 3, 39, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (41, 3, 40, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (42, 3, 41, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (43, 3, 42, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (44, 3, 43, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (45, 3, 44, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (46, 3, 45, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (47, 3, 46, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (48, 3, 47, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (49, 3, 48, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (50, 3, 49, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (51, 3, 50, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (52, 3, 51, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (53, 3, 52, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (54, 3, 53, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (55, 3, 54, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (56, 3, 55, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (57, 3, 56, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (58, 3, 57, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (59, 3, 58, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (60, 3, 59, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (61, 3, 60, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (62, 3, 61, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (63, 3, 62, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (64, 3, 63, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (65, 3, 64, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (66, 3, 65, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (67, 3, 66, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (68, 3, 67, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (69, 3, 68, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (70, 3, 69, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (71, 3, 70, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (72, 3, 71, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (73, 3, 72, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (74, 3, 73, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (75, 3, 74, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (76, 3, 75, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (77, 3, 76, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (78, 3, 77, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (79, 3, 78, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (80, 3, 79, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (81, 3, 80, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (82, 3, 81, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (83, 3, 82, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (84, 3, 83, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (85, 3, 84, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (86, 3, 85, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (87, 3, 86, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (88, 3, 87, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (89, 3, 88, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (90, 3, 89, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (91, 3, 90, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (92, 3, 91, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (93, 3, 92, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (94, 3, 93, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (95, 3, 94, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (96, 3, 95, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (97, 3, 96, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (98, 3, 97, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (99, 3, 98, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (100, 3, 99, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (101, 3, 100, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
INSERT INTO `apartment_admin`.auth_user_role (id, auth_role_id, auth_user_id, create_time, update_time) VALUES (102, 3, 101, '2021-07-02 07:06:09', '2021-07-02 07:06:09');
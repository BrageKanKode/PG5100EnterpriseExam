create sequence hibernate_sequence start with 1 increment by 1;
create table copy
(
    id                  bigint  not null,
    amount              integer not null,
    item_information_id bigint,
    owned_by_userid     varchar(255),
    primary key (id)
);
create table item
(
    id      bigint       not null,
    ability varchar(255) not null,
    amount  integer      not null,
    name    varchar(255) not null,
    value   integer      not null,
    primary key (id)
);
create table users
(
    userid          varchar(255) not null,
    currency        integer      not null,
    email           varchar(255),
    enabled         boolean      not null,
    first_name      varchar(128),
    hashed_password varchar(255) not null,
    last_name       varchar(128),
    lootboxes       integer      not null,
    primary key (userid)
);
create table users_owned_by
(
    all_users_userid varchar(255) not null,
    owned_by_id      bigint       not null
);
create table users_roles
(
    users_userid varchar(255) not null,
    roles        varchar(255)
);
alter table users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table copy
    add constraint FKsadw2gxrjmsdpwwauacde7n1d foreign key (item_information_id) references item;
alter table copy
    add constraint FKqn21pe3mrcdrybw4nrvrox6u9 foreign key (owned_by_userid) references users;
alter table users_owned_by
    add constraint FKculgn3bi7uj01atwlbccf3894 foreign key (owned_by_id) references item;
alter table users_owned_by
    add constraint FKdnkfgyp1owtc5959dlhyosaow foreign key (all_users_userid) references users;
alter table users_roles
    add constraint FKnqgxij5udu4xrsqju9dtbc8pr foreign key (users_userid) references users;
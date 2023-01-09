create table adress
(
    id              int auto_increment
        primary key,
    city            varchar(45) null,
    street          varchar(45) null,
    building_number int         null
);

create table client_table
(
    id         int auto_increment
        primary key,
    number     varchar(13) not null,
    login      varchar(20) not null,
    password   varchar(20) not null,
    isManager  tinyint(1)  null,
    first_name varchar(45) null,
    last_name  varchar(45) null
);

create table dish
(
    id         int auto_increment
        primary key,
    price      decimal(10, 2) not null,
    name       varchar(50)    not null,
    weight     int            not null,
    describing varchar(500)   null,
    image_path varchar(250)   null,
    category   varchar(50)    null
);

create table dish_content
(
    dish_id        int          not null,
    language_id    int          not null,
    dishName       varchar(50)  not null,
    dishCategory   varchar(50)  not null,
    dishDescribing varchar(500) null,
    constraint dish_content_ibfk_1
        foreign key (dish_id) references dish (id)
            on update cascade on delete cascade,
    constraint dish_content_ibfk_2
        foreign key (dish_id) references dish (id)
            on update cascade on delete cascade
);

create index dish_id
    on dish_content (dish_id);

create table language
(
    id           int auto_increment
        primary key,
    languageName varchar(10) null
);

create table order_table
(
    id         int auto_increment
        primary key,
    price      decimal(10, 2) not null,
    client_id  int            not null,
    status     varchar(10)    null,
    adress_id  int            null,
    order_date timestamp      null,
    constraint order_adress
        foreign key (adress_id) references adress (id)
            on update cascade on delete cascade,
    constraint order_table_ibfk_1
        foreign key (client_id) references client_table (id)
            on update cascade on delete cascade
);

create table order_dish
(
    order_id int not null,
    dish_id  int not null,
    quantity int null,
    constraint order_dish_ibfk_1
        foreign key (order_id) references order_table (id)
            on update cascade on delete cascade,
    constraint order_dish_ibfk_2
        foreign key (dish_id) references dish (id)
            on update cascade on delete cascade
);

create index dish_id
    on order_dish (dish_id);

create index order_id
    on order_dish (order_id);

create index client_id
    on order_table (client_id);
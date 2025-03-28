create table roles(
                      id serial primary key ,
                      role varchar(20) not null
);
insert into roles (role) values
    ('USER'),
    ('ADMIN');
create table users
(
    id        serial primary key,
    user_name varchar(100) unique not null,
    email     varchar(255) unique not null,
    password  varchar(255)        not null,
    first_name   varchar(100) NOT NULL,
    second_name  varchar(100) not null,
    birth_date   date,
    role_id int,
    foreign key (role_id) references roles(id)
);

create table currency(
                         id serial primary key ,
                         name varchar(20) not null,
                         symbol varchar(10)
);
insert into currency(name, symbol) values
                                       ('RUB', '₽'),
                                       ('EUR', '€'),
                                       ('USD', '$');

create table announcement
(
    id          serial primary key,
    title        varchar(100)  not null,
    description text,
    price int default 0 not null ,
    currency_id int,
    user_id     int,
    foreign key (currency_id) references currency(id) on delete cascade,
    foreign key (user_id) references users (id) on delete cascade

);

create table images
(
    id serial primary key,
    storage_file_name varchar(255) not null ,
    original_file_name        varchar(255) not null ,
    file_path        varchar(255) not null ,
    file_type varchar(20) not null,
    size int,
    announcement_id int,
    foreign key (announcement_id) references announcement(id) on delete cascade

);


create table favorites
(
    id              serial primary key,
    user_id         int,
    announcement_id int,
    unique (user_id, announcement_id),
    foreign key (user_id) references users (id) on delete cascade,
    foreign key (announcement_id) references announcement (id) on delete cascade

);

create table reviews(
    id serial primary key,
    rate int,
    comment text,
    user_id int,
    announcement_id int,
    foreign key (user_id) references users (id) on delete cascade,
    foreign key (announcement_id) references announcement (id) on delete cascade
);

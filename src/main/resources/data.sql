create table users
(
    id        serial primary key,
    user_name varchar(100) unique not null,
    email     varchar(255) unique not null,
    password  varchar(255)        not null,
    first_name   varchar(100) NOT NULL,
    second_name  varchar(100) not null,
    birth_date   date

);

create table announcement
(
    id          serial primary key,
    name        varchar(100) unique not null,
    description text,
    user_id     int,
    foreign key (user_id) references users (id) on delete cascade

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
create table images
(
    id               serial primary key,
    announcement_id  int not null,
    file_name        varchar(255),
    file_path        text,
    foreign key (announcement_id) references announcement (id) on delete cascade
);


select *
from users;
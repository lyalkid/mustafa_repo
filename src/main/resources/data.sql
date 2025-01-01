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
    title        varchar(100) unique not null,
    description text,
    user_id     int,
    image_id int,
    foreign key (user_id) references users (id) on delete cascade,
    foreign key (image_id) references images(id) on delete cascade

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
    file_name        varchar(100) not null ,
    file_path        varchar(100) not null ,
    file_type varchar(20) not null
);



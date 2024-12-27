create table users (
    id serial primary key,
    user_name varchar(100) unique not null,
    email varchar(255) unique not null,
    password varchar(255) not null
);

create table announcement(
    id serial primary key,
    name varchar(100) unique not null,
    description text

);

create table user_and_announcement(
    id serial primary key,
    user_id int,
    announcement_id int,
    foreign key (user_id) references users(id) on delete cascade ,
    foreign key (announcement_id) references announcement(id) on delete cascade
);


select * from users;
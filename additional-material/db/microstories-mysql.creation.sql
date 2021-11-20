create table Story
(
    id int auto_increment
        primary key,
    content varchar(1000) not null,
    date datetime not null,
    genre varchar(9) not null,
    mainTheme varchar(15) not null,
    publicated bit not null,
    secondaryTheme varchar(15) null,
    title varchar(80) not null
);


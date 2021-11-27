create table Story
(
    id int auto_increment
        primary key,
    author varchar(100) not null,
    content varchar(1000) not null,
    date datetime not null,
    genre varchar(9) not null,
    mainTheme varchar(15) not null,
    publicated bit not null,
    secondaryTheme varchar(15) null,
    title varchar(80) not null
);

create table visit_date
(
    id int not null,
    date datetime null,
    constraint FK90v9x8f0qu1o4rBtdlsqgk3uf
        foreign key (id) references Story(id)
);


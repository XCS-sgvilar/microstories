DROP DATABASE IF EXISTS `dgss2122_teamA_microstories`;
CREATE DATABASE `dgss2122_teamA_microstories` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `dgss2122_teamA_microstories`;

--
-- User creation
--
CREATE USER microstories@'%' IDENTIFIED BY 'microstoriespass';
GRANT ALL PRIVILEGES ON dgss2122_teamA_microstories.* TO microstories@'%';
FLUSH PRIVILEGES;


--
-- Tables creation
--

-- Write here the table creation queries.



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
    title varchar(80) not null,
   FOREIGN KEY (author) REFERENCES User(login)
);
create table User
(
    login varchar(100) not null  primary key,
    password varchar(100) not null,
);


--
-- Data insertion
--

-- Write here the data insertion queries.
insert into User (login,password)
values ("JK Rowling" ,"JK RowlingPasswd"),
("Scott Aaronson" ,"Scott AaronsonPasswd")
("Victor" ,"VictorPasswd")
("Brandon Sanderson" ,"Brandon SandersonPasswd")
("Santiago" ,"SantiagoPasswd")
("Ines" ,"InesPasswd")
("Bruno" ,"BrunoPasswd")
("Yudkowsky" ,"I am a potato 927")
("Greg Egan" ,"Greg EganPsswd")





insert into Story (id,author, date, title,  content,  genre, mainTheme, secondaryTheme, publicated)
values  (1, 'JK Rowling' ,'2000-02-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'ROMANCE', 'HORROR', true),
        (2,'JK Rowling', '2000-02-01 01:01:01', 'Integer aliquam adipiscing lacus.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'CHILD', 'HORROR', true),
        (3,'JK Rowling', '2000-02-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'HISTORIC', 'HORROR', true),
        (4,'JK Rowling', '2000-02-01 01:01:01', 'sed pede. Cum sociis', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'HORROR', 'HORROR', true),
        (5,'JK Rowling', '2000-02-01 01:01:01', 'eleifend. Cras sed leo.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'ROMANCE', 'HORROR', true),
        (6,'JK Rowling', '2000-02-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam a felis', 'NANOSTORY', 'SUSPENSE', 'HORROR', true),
        (7,'JK Rowling', '2000-02-01 01:01:01', 'In nec orci. Donec', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'HISTORIC', 'HORROR', true),
        (8,'JK Rowling' ,'2001-12-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'et ultrices posuere cubilia Curae Donec tincidunt. Donec vitae erat vel pede blandit congue.', 'POETRY', 'HISTORIC', 'HORROR', true),
        (9,'JK Rowling' ,'2001-02-01 01:01:01', 'erat. Etiam vestibulum massa', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'SCIENCE_FICTION', 'HORROR', true),
        (10,'JK Rowling' , '2001-02-01 01:01:01', 'consectetuer mauris id sapien.', 'lacus. Aliquam rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam', 'STORY', 'HORROR', 'HORROR', true),
        (11,'JK Rowling' , '2001-02-01 01:01:01', 'accumsan laoreet ipsum. Curabitur', 'sit amet ornare lectus justo eu arcu. Morbi sit amet massa. Quisque porttitor eros', 'POETRY', 'ROMANCE', 'HORROR', true),
        (12,'JK Rowling' , '2001-02-01 01:01:01', 'dapibus quam quis diam.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'ROMANCE', 'HORROR', true),
        (13,'JK Rowling' , '2001-02-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'SUSPENSE', 'HORROR', true),
        (14,'Scott Aaronson', '2001-02-01 01:01:01', 'In tincidunt congue turpis.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'HISTORIC', 'HORROR', true),
        (15,'Scott Aaronson', '2001-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'senectus et netus et malesuada fames ac turpis egestas. Fusce aliquet magna a neque.', 'POETRY', 'ROMANCE', 'HORROR', true),
        (16,'Scott Aaronson', '2001-03-01 01:01:01', 'mattis. Cras eget nisi', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'SUSPENSE', 'HORROR', true),
        (17,'Scott Aaronson', '2001-03-01 01:01:01', 'Aenean eget metus. In', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'SUSPENSE', 'HORROR', true),
        (18,'Scott Aaronson', '2001-03-01 01:01:01', 'ligula consectetuer rhoncus. Nullam', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'CHILD', 'HORROR', true),
        (19,'Scott Aaronson', '2001-03-01 01:01:01', 'magna nec quam. Curabitur', 'dolor vitae dolor. Donec fringilla. Donec feugiat metus sit amet ante. Vivamus non lorem', 'POETRY', 'HISTORIC', 'HORROR', true),
        (20,'Scott Aaronson', '2001-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'mauris a nunc. In at pede. Cras vulputate velit eu sem. Pellentesque ut ipsum', 'POETRY', 'SCIENCE_FICTION', 'HORROR', true),
        (21,'Scott Aaronson', '2001-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'HORROR', 'HORROR', true),
        (22,'Scott Aaronson', '2001-03-01 01:01:01', 'egestas. Duis ac arcu.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'SCIENCE_FICTION', 'HORROR', true),
        (23,'Scott Aaronson', '2001-03-01 01:01:01', 'ante dictum cursus. Nunc', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'CHILD', 'HORROR', true),
        (24,'Scott Aaronson', '2001-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'CHILD', 'HORROR', true),
        (25,'Scott Aaronson', '2001-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'neque et nunc. Quisque ornare tortor at risus. Nunc ac sem ut dolor dapibus', 'POETRY', 'SCIENCE_FICTION', 'HORROR', true),
        (26,'Scott Aaronson', '2001-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'CHILD', 'HORROR', true),
        (27,'Scott Aaronson', '2021-03-01 01:01:01', 'sed pede. Cum sociis', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'HISTORIC', 'HORROR', true),
        (28,'Scott Aaronson', '2021-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'HORROR', 'HORROR', true),
        (29,'Scott Aaronson', '2021-03-01 01:01:01', 'Integer vitae nibh. Donec', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'CHILD', 'HORROR', true),
        (30,'Scott Aaronson', '2021-03-01 01:01:01', 'neque non quam. Pellentesque', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'ROMANCE', 'HORROR', true),
        (31,'Scott Aaronson', '2021-03-01 01:01:01', 'Nam ac nulla. In', 'ridiculus mus. Aenean eget magna. Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus.', 'POETRY', 'ROMANCE', 'HORROR', true),
        (32,'Victor', '2021-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'HORROR', 'HORROR', true),
        (33,'Victor', '2021-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'HORROR', 'HORROR', true),
        (34,'Victor', '2021-03-01 01:01:01', 'ultrices. Duis volutpat nunc', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'HISTORIC', 'HORROR', true),
        (35,'Victor', '2021-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'SCIENCE_FICTION', 'HORROR', true),
        (36,'Victor', '2021-03-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'Phasellus ornare. Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl', 'STORY', 'ROMANCE', 'HORROR', true),
        (37,'Victor', '2021-01-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac', 'POETRY', 'HISTORIC', 'HORROR', true),
        (38,'Victor', '2021-01-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'ROMANCE', 'HORROR', true),
        (39,'Brandon Sanderson', '2021-04-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'ligula. Aenean euismod mauris eu elit. Nulla facilisi. Sed neque. Sed eget lacus. Mauris', 'STORY', 'SCIENCE_FICTION', 'HORROR', true),
        (40,'Brandon Sanderson', '2021-04-01 01:01:01', 'Phasellus vitae mauris sit', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'HISTORIC', 'HORROR', true),
        (41,'Brandon Sanderson', '2021-04-01 01:01:01', 'justo eu arcu. Morbi', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'SUSPENSE', 'HORROR', true),
        (42,'Brandon Sanderson', '2021-04-01 01:01:01', 'egestas blandit. Nam nulla', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'HORROR', 'HORROR', true),
        (43,'Brandon Sanderson', '2021-04-01 01:01:01', 'urna suscipit nonummy. Fusce', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'CHILD', 'HORROR', true),
        (44,'Brandon Sanderson', '2021-04-01 01:01:01', 'sociis natoque penatibus et', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'HORROR', 'HORROR', true),
        (45,'Brandon Sanderson', '2021-04-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'SCIENCE_FICTION', 'HORROR', true),
        (46,'Brandon Sanderson', '2021-04-01 01:01:01', 'ornare sagittis felis. Donec', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'ROMANCE', 'HORROR', true),
        (47,'Brandon Sanderson', '2021-04-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'NANOSTORY', 'SCIENCE_FICTION', 'HORROR', true),
        (48,'Brandon Sanderson', '2021-04-01 01:01:01', 'Aliquam ultrices iaculis odio.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'SUSPENSE', 'HORROR', true),
        (49,'Brandon Sanderson', '2021-04-01 01:01:01', 'Lorem ipsum dolor sit', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'POETRY', 'SCIENCE_FICTION', 'HORROR', true),
        (50,'Brandon Sanderson', '2021-04-01 01:01:01', 'In tincidunt congue turpis.', 'eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,', 'STORY', 'SUSPENSE', 'HORROR', true),
        (51,'Brandon Sanderson', '2021-04-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'SCIENCE_FICTION', 'HORROR', true),
        (52,'Santiago','2021-04-01 01:01:01', 'Praesent eu dui. Cum', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'SCIENCE_FICTION', 'HORROR', true),
        (53,'Santiago','2021-04-01 01:01:01', 'fringilla euismod enim. Etiam', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'STORY', 'SCIENCE_FICTION', 'HORROR', true),
        (54,'Santiago','2021-04-01 01:01:01', 'pede nec ante blandit', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'ROMANCE', 'HORROR', true),
        (55,'Santiago','2021-05-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'HISTORIC', 'HORROR', true),
        (56,'Santiago','2021-05-01 01:01:01', 'et ultrices posuere cubilia', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'SCIENCE_FICTION', 'HORROR', true),
        (57,'Santiago','2021-05-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'SUSPENSE', 'HORROR', true),
        (58,'Santiago','2021-05-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'STORY', 'SCIENCE_FICTION', 'HORROR', true),
        (59,'Santiago','2021-05-01 01:01:01', 'ac ipsum. Phasellus vitae', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'HISTORIC', 'HORROR', true),
        (60,'Santiago','2021-05-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'STORY', 'ROMANCE', 'HORROR', true),
        (61,'Santiago','2021-05-01 01:01:01', 'ut mi. Duis risus', 'molestie. Sed id risus quis diam luctus lobortis. Class aptent taciti sociosqu ad litora', 'POETRY', 'HORROR', 'HORROR', true),
        (62,'Santiago','2021-05-01 01:01:01', 'commodo ipsum. Suspendisse non', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'ROMANCE', 'HORROR', true),
        (63,'Santiago','2021-05-01 01:01:01', 'ac libero nec ligula', 'Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus. Aenean sed pede nec ante', 'POETRY', 'ROMANCE', 'HORROR', true),
        (64,'Santiago','2021-05-01 01:01:01', 'Cum sociis natoque penatibus', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'STORY', 'SCIENCE_FICTION', 'HORROR', true),
        (65,'Santiago','2021-05-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'CHILD', 'HORROR', true),
        (66,'Ines','2021-05-01 01:01:01', 'sem egestas blandit. Nam', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'SCIENCE_FICTION', 'HORROR', true),
        (67,'Ines','2021-05-01 01:01:01', 'sapien imperdiet ornare. In', 'erat vitae risus. Duis a mi fringilla mi lacinia mattis. Integer eu lacus. Quisque', 'POETRY', 'CHILD', 'HORROR', true),
        (68,'Ines','2021-05-01 01:01:01', 'metus sit amet ante.', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'SUSPENSE', 'HORROR', true),
        (69,'Ines','2021-06-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'STORY', 'HORROR', 'HISTORIC', false),
        (70,'Ines','2021-06-01 01:01:01', 'convallis ligula. Donec luctus', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'HISTORIC', 'HISTORIC', false),
        (71,'Ines','2021-06-01 01:01:01', 'neque non quam. Pellentesque', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'HISTORIC', 'HISTORIC', false),
        (72,'Ines','2021-06-01 01:01:01', 'Nulla semper tellus id', 'a nunc. In at pede. Cras vulputate velit eu sem. Pellentesque ut ipsum ac', 'NANOSTORY', 'ROMANCE', 'HISTORIC', false),
        (73,'Ines','2021-06-01 01:01:01', 'neque. Nullam ut nisi', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'ROMANCE', 'HISTORIC', false),
        (74,'Bruno', '2009-06-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'HISTORIC', 'HISTORIC', false),
        (75,'Bruno', '2009-06-01 01:01:01', 'dictum sapien. Aenean massa.', 'netus et malesuada fames ac turpis egestas. Aliquam fringilla cursus purus. Nullam scelerisque neque', 'STORY', 'CHILD', 'HISTORIC', false),
        (76,'Bruno', '2009-06-01 01:01:01', 'fermentum fermentum arcu. Vestibulum', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'SUSPENSE', 'HISTORIC', false),
        (77,'Bruno', '2009-06-01 01:01:01', 'Nulla semper tellus id', 'tristique neque venenatis lacus. Etiam bibendum fermentum metus. Aenean sed pede nec ante blandit', 'NANOSTORY', 'HORROR', 'HISTORIC', false),
        (78,'Bruno', '2009-06-01 01:01:01', 'enim commodo hendrerit. Donec', 'orci. Ut sagittis lobortis mauris. Suspendisse aliquet molestie tellus. Aenean egestas hendrerit neque. In', 'NANOSTORY', 'HORROR', 'HISTORIC', false),
        (79,'Bruno', '2019-07-01 01:01:01', 'volutpat. Nulla facilisis. Suspendisse', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'HORROR', 'HISTORIC', false),
        (80,'Yudkowsky', '2019-07-01 01:01:01', 'Nulla semper tellus id', 'Donec egestas. Duis ac arcu. Nunc mauris. Morbi non sapien molestie orci tincidunt adipiscing.', 'STORY', 'SUSPENSE', 'HISTORIC', false),
        (81,'Yudkowsky', '2019-07-01 01:01:01', 'Proin dolor. Nulla semper', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'HORROR', 'HISTORIC', false),
        (82,'Yudkowsky', '2014-07-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'HORROR', 'HISTORIC', false),
        (83,'Yudkowsky', '2014-07-01 01:01:01', 'Nam interdum enim non', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'STORY', 'SCIENCE_FICTION', 'HISTORIC', false),
        (84,'Yudkowsky', '2014-07-01 01:01:01', 'Nulla semper tellus id', 'Donec tincidunt. Donec vitae erat vel pede blandit congue. In scelerisque scelerisque dui. Suspendisse', 'STORY', 'SUSPENSE', 'HISTORIC', false),
        (85,'Yudkowsky', '2014-07-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'HISTORIC', 'HISTORIC', false),
        (86,'Yudkowsky', '2014-07-01 01:01:01', 'augue ut lacus. Nulla', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'STORY', 'SUSPENSE', 'HISTORIC', false),
        (87,'Yudkowsky', '2014-07-01 01:01:01', 'Nulla semper tellus id', 'luctus et ultrices posuere cubilia Curae Donec tincidunt. Donec vitae erat vel pede blandit', 'STORY', 'SUSPENSE', 'HISTORIC', false),
        (88,'Yudkowsky', '2014-07-01 01:01:01', 'lobortis tellus justo sit', 'tortor. Nunc commodo auctor velit. Aliquam nisl. Nulla eu neque pellentesque massa lobortis ultrices.', 'POETRY', 'ROMANCE', 'HISTORIC', false),
        (89,'Yudkowsky', '2014-07-01 01:01:01', 'lorem semper auctor. Mauris', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'HISTORIC', 'HISTORIC', false),
        (90,'Yudkowsky', '2014-07-01 01:01:01', 'Nulla semper tellus id', 'Morbi quis urna. Nunc quis arcu vel quam dignissim pharetra. Nam ac nulla. In', 'STORY', 'ROMANCE', 'HISTORIC', false),
        (91,'Yudkowsky', '2014-07-01 01:01:01', 'Integer eu lacus. Quisque', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'STORY', 'HORROR', 'HISTORIC', false),
        (92,'Yudkowsky', '2014-07-01 01:01:01', 'euismod est arcu ac', 'fringilla est. Mauris eu turpis. Nulla aliquet. Proin velit. Sed malesuada augue ut lacus.', 'NANOSTORY', 'SUSPENSE', 'HISTORIC', false),
        (93,'Yudkowsky', '2014-07-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'SUSPENSE', 'HISTORIC', false),
        (94,'Yudkowsky', '2014-07-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'STORY', 'HISTORIC', 'HISTORIC', false),
        (95,'Yudkowsky', '2014-07-01 01:01:01', 'Nulla semper tellus id', 'amet orci. Ut sagittis lobortis mauris. Suspendisse aliquet molestie tellus. Aenean egestas hendrerit neque.', 'NANOSTORY', 'CHILD', 'HISTORIC', false),
        (96,'Yudkowsky', '2014-07-01 01:01:01', 'lorem semper auctor. Mauris', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'HISTORIC', 'HISTORIC', false),
        (97,'Yudkowsky', '2014-07-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'HISTORIC', 'HISTORIC', false),
        (98,'Yudkowsky', '2014-07-01 01:01:01', 'Nulla semper tellus id', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'CHILD', 'HISTORIC', false),
        (99,'Yudkowsky', '2014-07-01 01:01:01', 'orci. Ut sagittis lobortis', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'NANOSTORY', 'ADVENTURE', 'HISTORIC', false),
        (100,'Greg Egan','2014-07-01 01:01:01', 'Curabitur vel lectus. Cum', 'est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu', 'POETRY', 'ADVENTURE', 'HISTORIC', false)

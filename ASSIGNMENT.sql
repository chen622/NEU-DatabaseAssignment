create database assignment;
use assignment;
create table dvd_prop
(
  prop_id int auto_increment primary key,
  title varchar(64) not null,
  release_date date not null,
  director  varchar(32) not null,
  constraint title
  unique (title, release_date)
);

create table cast
(
  prop_id int not null,
  cast varchar(255) not null,
  primary key (prop_id, cast),
  constraint prop_id_constraint
  foreign key (prop_id) references dvd_prop (prop_id)
);

create table genre_type
(
  genre_type_id int auto_increment primary key,
  genre_type_name varchar(32) not null,
  constraint genre_type_genre_type_name_uindex
  unique (genre_type_name)
);

create table dvd_genre
(
  prop_id  int not null,
  genre_type_id int not null,
  primary key (prop_id, genre_type_id),
  constraint genre_dvd_prop_id_constraint
  foreign key (prop_id) references dvd_prop (prop_id),
  constraint genre_type_id_constraint
  foreign key (genre_type_id) references genre_type (genre_type_id)
);

create table library
(
  name varchar(64)  not null,
  address varchar(255) not null,
  primary key (name)
);

create table dvd_entity
(
  entity_id int auto_increment primary key,
  library_name varchar(64) null,
  prop_id int not null,
  constraint dvd_library_name_constraint
  foreign key (library_name) references library (name)
    on update cascade,
  constraint dvd_prop_id_constraint
  foreign key (prop_id) references dvd_prop (prop_id)
    on update cascade
);

create table member_category
(
  category_id int auto_increment primary key,
  category_name varchar(32) not null,
  price decimal(8, 2) not null,
  constraint category_name
  unique (category_name)
);

create table member
(
  member_id int auto_increment primary key,
  member_name varchar(32) not null,
  member_address varchar(255)  not null,
  category  int not null,
  balance decimal(8, 2) default '0.00' not null,
  constraint category_id_constraint
  foreign key (category) references member_category (category_id)
    on update cascade
    on delete cascade
);

create table rental
(
  rental_id  int auto_increment primary key,
  member_id  int not null,
  entity_id  int not null,
  date_taken_from date  not null,
  library_taken_from varchar(64) not null,
  date_return_on  date  null,
  library_return_on  varchar(64) null,
  money decimal(8, 2) null,
  constraint member_id_constraint
  foreign key (member_id) references member (member_id)
    on update cascade
    on delete cascade,
  constraint entity_id_constraint
  foreign key (entity_id) references dvd_entity (entity_id),
  constraint library_taken_from_constraint
  foreign key (library_taken_from) references library (name),
  constraint library_return_on_constraint
  foreign key (library_return_on) references library (name)
);


INSERT INTO `member_category` (`category_id`, `category_name`, `price`) VALUES
  (1, 'normal', '5.00'),
  (2, 'premium', '3.00');
INSERT INTO `genre_type` (`genre_type_id`, `genre_type_name`) VALUES
  (1, 'comedy'),
  (2, 'action'),
  (3, 'horror'),
  (4, 'romance'),
  (5, 'factual');
INSERT INTO `dvd_prop` (`prop_id`, `title`, `release_date`, `director`) VALUES
  (1, 'Introduction to Database', '2018-01-01', 'David'),
  (2, 'Top Secret Record', '1989-06-04', 'Unknown'),
  (3, 'Hirai Ken Music Video Collection', '2017-01-01', 'Hirai Ken'),
  (4, 'The Interview', '2015-01-01', 'Kim Jong-un'),
  (5, 'Sleep Helping Videos', '2018-01-01', 'A really famous directory'),
  (6, 'North Korea Adventure', '1970-01-09', 'Kim Il-sung'),
  (7, 'Machine Learning', '2014-01-01', 'Andrew Ng'),
  (8, 'Black Mirror', '2013-01-18', 'Otto Bathurst, Brian Welsh');
INSERT INTO `cast` (`prop_id`, `cast`) VALUES
  (1, 'David'),
  (4, 'Kim Jong-un'),
  (2, 'People calling for \"free\"'),
  (3, 'Hirai Ken, Yamata Shinichi'),
  (5, 'Bored Stuff'),
  (6, 'People from DPRK'),
  (7, 'Andrew Ng'),
  (8, 'Lenora Crichlow, Daniel Rigby, Hayley Atwell');
INSERT INTO `dvd_genre` (`prop_id`, `genre_type_id`) VALUES
  (1, 5),
  (2, 5),
  (3, 4),
  (4, 1),
  (8, 3),
  (7, 5),
  (5, 1),
  (6, 5);
INSERT INTO `library` (`name`, `address`) VALUES
  ('NEUHN', 'Hunan District, Shenyang, Liaoning, China.'),
  ('NEUNH', 'Heping District, Shenyang, Liaoning, China.'),
  ('TAIYUAN', 'Taiyuan St. Shenyang, Liaoning, China.');

INSERT INTO `member` (`member_id`, `member_name`, `member_address`, `category`, `balance`) VALUES
  (1, 'Bruce Lee', 'Hunnan Campus of NEU, Shenyang, China.', 2, '100.00');

INSERT INTO `dvd_entity` (`entity_id`, `library_name`, `prop_id`) VALUES
  (1, 'NEUHN', 1),
  (2, 'NEUHN', 1),
  (3, 'NEUNH', 1),
  (4, 'NEUNH', 1),
  (5, 'TAIYUAN', 1),
  (6, 'NEUHN', 2),
  (7, 'NEUHN', 2),
  (8, 'NEUNH', 3),
  (9, 'NEUHN', 3),
  (10, 'TAIYUAN', 3),
  (11, 'TAIYUAN', 3),
  (12, 'TAIYUAN', 3),
  (13, 'TAIYUAN', 4),
  (14, 'NEUHN', 4),
  (15, 'NEUHN', 4);



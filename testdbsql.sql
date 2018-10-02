drop table Legendary;
drop table User;
drop table PrimaryItem;
drop table Task;

-- create tables
create table Legendary(
  id int primary key AUTO_INCREMENT,
  name varchar(20),
  pictureReference VARCHAR(40),
  type VARCHAR(20),
  game VARCHAR(30)
);

create table User(
  id int primary key AUTO_INCREMENT,
  username VARCHAR(20),
  password VARCHAR(20)
);

create table PrimaryItem(
  id int PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30),
  pictureReference VARCHAR(40)
);

create table Task(
  id int primary key AUTO_INCREMENT,
  name VARCHAR(30),
  primaryItemId int,
  foreign key (primaryItemId) references PrimaryItem(id)
    on delete cascade
);

-- insert data
INSERT into Legendary (name, type, game) values(
  'Bolt', 'Sword', 'Base'
);

INSERT into Legendary (name, type, game) values(
  'The Shining Blade', 'Sword', 'HoT'
);

INSERT into Legendary (name, type, game) values(
  'Chuka and Champawat', 'Shortbow', 'HoT'
);

INSERT into Legendary (name, type, game) values(
  'Sharur', 'Hammer', 'HoT'
);

INSERT into Legendary (name, type, game) values(
  'Astralaria', 'Axe', 'HoT'
);

INSERT into Legendary (name, type, game) values(
  'The Minstrel', 'Focus', 'Base'
);

INSERT into Legendary (name, type, game) values(
  'The Binding of Ipos', 'Focus', 'PoF'
);
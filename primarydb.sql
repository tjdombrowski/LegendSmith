drop table UserRole;
drop table UserLegendaryPrimaryItemTask;
drop table UserLegendaryPrimaryItem;
drop table UserLegendary;
drop table LegendaryPrimaryItem;
drop table User;
drop table GameLegendary;
drop table Game;
drop table Task;
drop table PrimaryItem;
drop table Legendary;

-- auto-generated definition
CREATE TABLE Legendary
(
  id               INT         NOT NULL
  AUTO_INCREMENT PRIMARY KEY,
  name             VARCHAR(20) NULL,
  pictureReference VARCHAR(40) NULL,
  type             VARCHAR(20) NULL,
  game             VARCHAR(30) NULL
);

CREATE TABLE Game
(
  name varchar(6) primary key
);

CREATE TABLE GameLegendary
(
  legendaryId int,
  gameName varchar(6),
  primary key (legendaryId, gameName),
  CONSTRAINT GameLegendary_legendaryId_fk
  FOREIGN KEY (legendaryId) REFERENCES Legendary(id),
  CONSTRAINT GameLegendary_gameName_fk
  FOREIGN KEY (gameName) REFERENCES Game(name)
);

-- auto-generated definition
CREATE TABLE User
(
  id       INT         NOT NULL
  AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(20) NULL,
  password VARCHAR(20) NULL
);

CREATE TABLE UserRole
(
  userId int,
  role VARCHAR(15),
  CONSTRAINT UserRole_fk
  FOREIGN KEY (userId) REFERENCES User(id)
);

-- auto-generated definition
CREATE TABLE PrimaryItem
(
  id               INT         NOT NULL
  AUTO_INCREMENT PRIMARY KEY,
  name             VARCHAR(30) NULL,
  pictureReference VARCHAR(40) NULL
);

-- auto-generated definition
CREATE TABLE LegendaryPrimaryItem
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  legendaryId   INT NULL,
  primaryItemId INT NULL,
  CONSTRAINT LegendaryPrimaryItem_legendaryId_fk
  FOREIGN KEY (legendaryId) REFERENCES Legendary (id),
  CONSTRAINT LegendaryPrimaryItem_primaryItemId_fk
  FOREIGN KEY (primaryItemId) REFERENCES PrimaryItem (id)
);

-- auto-generated definition
CREATE TABLE Task
(
  id            INT         NOT NULL
  AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(30) NULL,
  description   VARCHAR(500) NULL,
  quantity      INT DEFAULT 1,
  prerequisite INT NULL,
  legendaryPrimaryItemId INT         NULL,
  CONSTRAINT Task_legendaryPrimaryItemId_fk
  FOREIGN KEY (legendaryPrimaryItemId) REFERENCES LegendaryPrimaryItem (id),
  CONSTRAINT Task_prerequisite_fk
  FOREIGN KEY (legendaryPrimaryItemId) REFERENCES Task (id)
);


-- auto-generated definition
CREATE TABLE UserLegendary
(
  userId      INT             NOT NULL,
  legendaryId INT             NOT NULL,
  progress    INT             NULL,
  tracking    INT DEFAULT '0' NULL,
  priority    INT DEFAULT '0' NULL,
  PRIMARY KEY (userId, legendaryId),
  CONSTRAINT UserLegendary_userId_fk
  FOREIGN KEY (userId) REFERENCES User (id),
  CONSTRAINT UserLegendary_legendaryId_fk
  FOREIGN KEY (legendaryId) REFERENCES Legendary (id)
);


-- auto-generated definition
CREATE TABLE UserLegendaryPrimaryItem
(
  id                     INT NOT NULL
    PRIMARY KEY AUTO_INCREMENT,
  legendaryPrimaryItemId INT NULL,
  userId                 INT NULL,
  progress               INT NULL,
  CONSTRAINT UserLegendaryPrimaryItem_fk1
  FOREIGN KEY (legendaryPrimaryItemId) REFERENCES LegendaryPrimaryItem (id),
  CONSTRAINT UserLegendaryPrimaryItem_userId_fk2
  FOREIGN KEY (userId) REFERENCES User (id)
);

-- auto-generated definition
CREATE TABLE UserLegendaryPrimaryItemTask
(
  userLegendaryPrimaryItemId INT             NOT NULL,
  taskId                     INT             NOT NULL,
  completion                 INT DEFAULT '0' NULL,
  dateCompleted              DATE            NULL,
  PRIMARY KEY (taskId, userLegendaryPrimaryItemId),
  CONSTRAINT UserLegendPrimItem_fk1
  FOREIGN KEY (userLegendaryPrimaryItemId) REFERENCES UserLegendaryPrimaryItem (id),
  CONSTRAINT UserLegendPrimItem_fk2
  FOREIGN KEY (taskId) REFERENCES Task (id)
);

-- insert data for Legendary
INSERT into Legendary (name, type) values(
  'Bolt', 'Sword'
);

INSERT into Legendary (name, type) values(
  'The Shining Blade', 'Sword'
);

INSERT into Legendary (name, type) values(
  'Twilight', 'Greatsword'
);

INSERT into Legendary (name, type) values(
  'Kudzu', 'Longbow'
);

INSERT into Legendary (name, type) values(
  'Xiuquatl', 'Scepter'
);

INSERT into Legendary (name, type) values(
  'Sunrise', 'Greatsword'
);

INSERT into Legendary (name, type) values(
  'Astralaria', 'Axe'
);

INSERT into Legendary (name, type) values(
  'Sharur', 'Hammer'
);

INSERT into Legendary (name, type) values(
  'Chuka and Champawat', 'Shortbow'
);

INSERT into Legendary (name, type) values(
  'Frostfang', 'Axe'
);

INSERT into Legendary (name, type) values(
  'The Binding of Ipos', 'Focus'
);

-- insert data for Game
INSERT into Game values(
    'Base'
);

INSERT into Game values(
    'HoT'
);

INSERT into Game values(
    'PoF'
);

-- insert data for GameLegendary
INSERT into GameLegendary VALUES (
  1, 'Base'
);

INSERT into GameLegendary VALUES (
  2, 'HoT'
);

INSERT into GameLegendary VALUES (
  2, 'PoF'
);

INSERT into GameLegendary VALUES (
  3, 'Base'
);

INSERT into GameLegendary VALUES (
  4, 'Base'
);

INSERT into GameLegendary VALUES (
  5, 'HoT'
);

INSERT into GameLegendary VALUES (
  5, 'PoF'
);

INSERT into GameLegendary VALUES (
  6, 'Base'
);

INSERT into GameLegendary VALUES (
  7, 'HoT'
);

INSERT into GameLegendary VALUES (
  8, 'HoT'
);

INSERT into GameLegendary VALUES (
  8, 'PoF'
);

INSERT into GameLegendary VALUES (
  9, 'HoT'
);

INSERT into GameLegendary VALUES (
  10, 'Base'
);

INSERT into GameLegendary VALUES (
  11, 'HoT'
);

INSERT into GameLegendary VALUES (
  11, 'PoF'
);

-- insert data for PrimaryItem
-- for The Shining Blade
insert into PrimaryItem (name) VALUES(
  'Save the Queen'
);

insert into PrimaryItem (name) VALUES(
  'Gift of the Blade'
);

insert into PrimaryItem (name) VALUES(
  'Gift of Maguuma Mastery'
);

insert into PrimaryItem (name) VALUES(
  'Mystic Tribute'
);

-- insert data for LegendaryPrimaryItem
-- for The Shining Blade
insert into LegendaryPrimaryItem (primaryItemId, legendaryId) VALUES(
  1, 2
);

insert into LegendaryPrimaryItem (primaryItemId, legendaryId) VALUES(
  2, 2
);

insert into LegendaryPrimaryItem (primaryItemId, legendaryId) VALUES(
  3, 2
);

insert into LegendaryPrimaryItem (primaryItemId, legendaryId) VALUES(
  4, 2
);

-- insert data for Task
-- for The Shining Blade, Gift of Maguuma Mastery
insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of Maguuma', 'Combine Gift of the Jungle, Gift of the Chak, Gift of Tarir, and Gift of the Fleet together in the Mystic Forge.', 3
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of Insights', '<Mystic Forge Recipe>', 3
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Bloodstone Shard', 'Purchased from Miyani or any Mystic Forge Attendant for 200 Spirit Shards.', 3
);

insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
  'Crystalline Ingot', '<recipe>', 3, 250
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of the Fleet', 'Obtained upon map completion of Verdant Brink.', 3
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of Tarir', 'Obtained upon map completion of Auric Basin.', 3
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of the Chak', 'Obtained upon map completion of Tangled Depths.', 3
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of the Jungle', 'Obtained upon map completion of Dragon\'s Stand.', 3
);

-- for The Shining Blade, Save the Queen
insert into Task (name, description, legendaryPrimaryItemId) values (
  'Exemplar\'s Edge', 'The first tier precursor of Save the Queen.', 1
);

insert into Task (name, description, legendaryPrimaryItemId) VALUES (
    'Vengeance', 'The second tier precursor of Save The Queen.', 1
);
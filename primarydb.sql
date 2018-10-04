drop table UserLegendaryPrimaryItemTask;
drop table UserLegendaryPrimaryItem;
drop table LegendaryPrimaryItem;
drop table UserLegendary;
drop table User;
drop table Legendary;
drop table Task;
drop table PrimaryItem;

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

-- auto-generated definition
CREATE TABLE User
(
  id       INT         NOT NULL
  AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(20) NULL,
  password VARCHAR(20) NULL
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
CREATE TABLE Task
(
  id            INT         NOT NULL
  AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(30) NULL,
  description   VARCHAR(500) NULL,
  quantity      INT DEFAULT 1,
  primaryItemId INT         NULL,
  CONSTRAINT Task_ibfk_1
  FOREIGN KEY (primaryItemId) REFERENCES PrimaryItem (id)
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
  CONSTRAINT UserLegendary_ibfk_1
  FOREIGN KEY (userId) REFERENCES User (id),
  CONSTRAINT UserLegendary_ibfk_2
  FOREIGN KEY (legendaryId) REFERENCES Legendary (id)
);


-- auto-generated definition
CREATE TABLE LegendaryPrimaryItem
(
  id            INT NOT NULL
    PRIMARY KEY AUTO_INCREMENT,
  legendaryId   INT NULL,
  primaryItemId INT NULL,
  CONSTRAINT LegendaryPrimaryItem_ibfk_1
  FOREIGN KEY (legendaryId) REFERENCES Legendary (id),
  CONSTRAINT LegendaryPrimaryItem_ibfk_2
  FOREIGN KEY (primaryItemId) REFERENCES PrimaryItem (id)
);

-- auto-generated definition
CREATE TABLE UserLegendaryPrimaryItem
(
  id                     INT NOT NULL
    PRIMARY KEY AUTO_INCREMENT,
  legendaryPrimaryItemId INT NULL,
  userId                 INT NULL,
  progress               INT NULL,
  CONSTRAINT UserLegendaryPrimaryItem_ibfk_1
  FOREIGN KEY (legendaryPrimaryItemId) REFERENCES LegendaryPrimaryItem (id),
  CONSTRAINT UserLegendaryPrimaryItem_ibfk_2
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
  CONSTRAINT UserLegendaryPrimaryItemTask_ibfk_1
  FOREIGN KEY (userLegendaryPrimaryItemId) REFERENCES UserLegendaryPrimaryItem (id),
  CONSTRAINT UserLegendaryPrimaryItemTask_ibfk_2
  FOREIGN KEY (taskId) REFERENCES Task (id)
);

-- insert data for Legendary
INSERT into Legendary (name, type, game) values(
  'Bolt', 'Sword', 'Base'
);

INSERT into Legendary (name, type, game) values(
  'The Shining Blade', 'Sword', 'HoT'
);

INSERT into Legendary (name, type, game) values(
  'Twilight', 'Greatsword', 'Base'
);

INSERT into Legendary (name, type, game) values(
  'Kudzu', 'Longbow', 'Base'
);

INSERT into Legendary (name, type, game) values(
  'Xiuquatl', 'Scepter', 'PoF'
);

INSERT into Legendary (name, type, game) values(
  'Sunrise', 'Greatsword', 'Base'
);

INSERT into Legendary (name, type, game) values(
  'Astralaria', 'Axe', 'HoT'
);

INSERT into Legendary (name, type, game) values(
  'Sharur', 'Hammer', 'HoT'
);

INSERT into Legendary (name, type, game) values(
  'Chuka and Champawat', 'Shortbow', 'HoT'
);

INSERT into Legendary (name, type, game) values(
  'Frostfang', 'Axe', 'Base'
);

INSERT into Legendary (name, type, game) values(
  'The Binding of Ipos', 'Focus', 'PoF'
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

-- insert data for Task
-- for The Shining Blade, Gift of Maguuma Mastery
insert into Task (name, description, primaryItemId) values (
  'Gift of Maguuma', 'Combine Gift of the Jungle, Gift of the Chak, Gift of Tarir, and Gift of the Fleet together in the Mystic Forge.', 3
);

insert into Task (name, description, primaryItemId) values (
  'Gift of Insights', '<Mystic Forge Recipe>', 3
);

insert into Task (name, description, primaryItemId) values (
  'Bloodstone Shard', 'Purchased from Miyani or any Mystic Forge Attendant for 200 Spirit Shards.', 3
);

insert into Task (name, description, primaryItemId, quantity) values (
  'Crystalline Ingot', '<recipe>', 3, 250
);

insert into Task (name, description, primaryItemId) values (
  'Gift of the Fleet', 'Obtained upon map completion of Verdant Brink.', 3
);

insert into Task (name, description, primaryItemId) values (
  'Gift of Tarir', 'Obtained upon map completion of Auric Basin.', 3
);

insert into Task (name, description, primaryItemId) values (
  'Gift of the Chak', 'Obtained upon map completion of Tangled Depths.', 3
);

insert into Task (name, description, primaryItemId) values (
  'Gift of the Jungle', 'Obtained upon map completion of Dragon\'s Stand.', 3
);

-- for The Shining Blade, Save the Queen

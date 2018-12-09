  drop table UserRole;
  drop table UserLegendaryPrimaryItemTask;
  drop table UserLegendaryPrimaryItem;
  drop table UserLegendary;
  drop table TaskItem;
  drop table Item;
  drop table Task;
  drop table LegendaryPrimaryItem;
  drop table User;
  drop table GameLegendary;
  drop table Game;
  drop table PrimaryItem;
  drop table Legendary;

  -- create tables
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
    FOREIGN KEY (legendaryId) REFERENCES Legendary(id)
    ON DELETE CASCADE,
    CONSTRAINT GameLegendary_gameName_fk
    FOREIGN KEY (gameName) REFERENCES Game(name)
    ON DELETE CASCADE
  );

  CREATE TABLE User
  (
    id       INT         NOT NULL
    AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NULL unique,
    password VARCHAR(20) NULL
  );

  CREATE TABLE UserRole
  (
    id int primary key AUTO_INCREMENT,
    userId int,
    role VARCHAR(15),
    username VARCHAR(20),
    CONSTRAINT UserRole_fk
    FOREIGN KEY (userId) REFERENCES User(id)
    ON DELETE CASCADE
  );

  CREATE TABLE PrimaryItem
  (
    id               INT         NOT NULL
    AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(30) NULL,
    pictureReference VARCHAR(40) NULL
  );

  CREATE TABLE LegendaryPrimaryItem
  (
    id INT PRIMARY KEY AUTO_INCREMENT,
    legendaryId   INT NULL,
    primaryItemId INT NULL,
    CONSTRAINT LegendaryPrimaryItem_legendaryId_fk
    FOREIGN KEY (legendaryId) REFERENCES Legendary (id)
    ON DELETE CASCADE,
    CONSTRAINT LegendaryPrimaryItem_primaryItemId_fk
    FOREIGN KEY (primaryItemId) REFERENCES PrimaryItem (id)
    ON DELETE CASCADE
  );

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
    FOREIGN KEY (legendaryPrimaryItemId) REFERENCES LegendaryPrimaryItem (id)
    ON DELETE CASCADE,
    CONSTRAINT Task_prerequisite_fk
    FOREIGN KEY (prerequisite) REFERENCES Task (id)
    ON DELETE CASCADE
  );

  CREATE TABLE Item
  (
    gw2ItemId int PRIMARY KEY,
    name VARCHAR(25) NOT NULL
  );

  CREATE TABLE TaskItem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    gw2ItemId int,
    taskId int,
    quantity int default 1,
    CONSTRAINT TaskItem_gw2ItemId_fk FOREIGN KEY (gw2ItemId) REFERENCES Item (gw2ItemId) ON DELETE CASCADE,
    CONSTRAINT TaskItem_taskId_fk FOREIGN KEY (taskId) REFERENCES Task (id) ON DELETE CASCADE
  );


  CREATE TABLE UserLegendary
  (
    id          INT primary key AUTO_INCREMENT,
    userId      INT             NOT NULL,
    legendaryId INT             NOT NULL,
    progress    double  DEFAULT 0  NOT NULL,
    tracking    INT DEFAULT '0' NOT NULL,
    priority    INT DEFAULT '0' NOT NULL,
    CONSTRAINT UserLegendary_userId_fk
    FOREIGN KEY (userId) REFERENCES User (id)
    ON DELETE CASCADE,
    CONSTRAINT UserLegendary_legendaryId_fk
    FOREIGN KEY (legendaryId) REFERENCES Legendary (id)
    ON DELETE CASCADE
  );


  CREATE TABLE UserLegendaryPrimaryItem
  (
    id                     INT NOT NULL
      PRIMARY KEY AUTO_INCREMENT,
    legendaryPrimaryItemId INT NULL,
    userId                 INT NULL,
    progress               double NOT NULL default 0,
    CONSTRAINT UserLegendaryPrimaryItem_fk1
    FOREIGN KEY (legendaryPrimaryItemId) REFERENCES LegendaryPrimaryItem (id)
    ON DELETE CASCADE,
    CONSTRAINT UserLegendaryPrimaryItem_userId_fk2
    FOREIGN KEY (userId) REFERENCES User (id)
    ON DELETE CASCADE
  );


  CREATE TABLE UserLegendaryPrimaryItemTask
  (
    id int primary key AUTO_INCREMENT,
    userLegendaryPrimaryItemId INT             NOT NULL,
    taskId                     INT             NOT NULL,
    completion                 INT DEFAULT '0' NULL,
    dateCompleted              DATE            NULL,
    CONSTRAINT UserLegendPrimItem_fk1
    FOREIGN KEY (userLegendaryPrimaryItemId) REFERENCES UserLegendaryPrimaryItem (id)
    ON DELETE CASCADE,
    CONSTRAINT UserLegendPrimItem_fk2
    FOREIGN KEY (taskId) REFERENCES Task (id)
    ON DELETE CASCADE
  );

-- insert data for User
INSERT INTO User (username, password) VALUES (
    'valence', 'dancingajnya'
);

INSERT INTO User (username, password) VALUES (
    'silkie', 'rabbitscream'
);

INSERT INTO User (username, password) VALUES (
    'vinzio', 'immortalgrace'
);

INSERT INTO User (username, password) VALUES (
    'thylaeza', 'thelionwhispers'
);

INSERT INTO User (username, password) VALUES (
    'ajnya', 'lungtwisted'
);

INSERT INTO User (username, password) VALUES (
  'sophrosyne', 'shieldandsword'
);

INSERT INTO User (username, password) VALUES (
  'lex', 'cocoahooves'
);


-- insert data for UserRole
INSERT into UserRole (id, userId, role, username) VALUES (
  1, 1, 'user', 'valence'
);
INSERT into UserRole (id, userId, role, username) VALUES (
  2, 2, 'user', 'silkie'
);
INSERT into UserRole (id, userId, role, username) VALUES (
  3, 3, 'user', 'vinzio'
);
INSERT into UserRole (id, userId, role, username) VALUES (
  4, 4, 'user', 'thylaeza'
);
INSERT into UserRole (id, userId, role, username) VALUES (
  5, 5, 'user', 'ajnya'
);
INSERT into UserRole (id, userId, role, username) VALUES (
  6, 6, 'user', 'sophrosyne'
);
INSERT into UserRole (id, userId, role, username) VALUES (
  7, 7, 'user', 'lex'
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

INSERT into Legendary (name, type) values(
  'Incinerator', 'Dagger'
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
  'Gift of Insights', 'Mystic Forge Recipe', 3
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Bloodstone Shard', 'Purchased from Miyani or any Mystic Forge Attendant for 200 Spirit Shards.', 3
);

insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
  'Crystalline Ingot', 'recipe', 3, 250
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
insert into Task (name, description, legendaryPrimaryItemId) VALUES (
  'Save the Queen', 'The precursor for The Shining Blade.', 1
);

-- for the Shining Blade, Gift of the Blade
insert into Task (name, description, legendaryPrimaryItemId) VALUES (
  'Gift of Metal', 'Created in the Mystic Forge. <div class="recipe">Recipe</div>', 2
);
insert into Task (name, description, legendaryPrimaryItemId) VALUES (
  'Gift of the Mists', 'Created in the Mystic Forge. <div class="recipe">Recipe</div>', 2
);
insert into Task (name, description, legendaryPrimaryItemId, quantity) VALUES (
  'Shard of the Crown', 'Crafted by Weaponsmiths. Requires 450 skill.<div class="recipe">Recipe<ul><li>1 Tribute to the Queen</li><li>1 Mystic Curio</li><li>30 Mithril Ingots</li><li>20 Elder Wood Planks</li></ul></div>', 2, 100
);
insert into Task (name, description, legendaryPrimaryItemId, quantity) VALUES (
  'Mystic Runestone', 'Purchased from Miyani for 1g.', 2, 100
);


-- for the Shining Blade, Mystic Tribute
insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
  'Gift of Condensed Magic', 'Created in the Mystic Forge. <div class="recipe">Recipe</div>', 4, 2
);
insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
  'Gift of Condensed Might', 'Created in the Mystic Forge. <div class="recipe">Recipe</div>', 4, 2
);
insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
  'Mystic Clover', 'Created in the mystic forge, earned through PvP reward tracks, or from log-in rewards.', 4, 77
);
insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
  'Mystic Coin', 'Earned from log-in rewards or purchasable on the trading post.', 4, 250
);

-- Item data
insert into Item (name, gw2ItemId) VALUES (
  'Mithril Ingot', 19684
);
insert into Item (name, gw2ItemId) VALUES (
  'Elder Wood Plank', 19709
);
insert into Item (name, gw2ItemId) VALUES (
  'Platinum Ingot', 19686
);
insert into Item (name, gw2ItemId) VALUES (
  'Orichalcum Ingot', 19685
);
insert into Item (name, gw2ItemId) VALUES (
  'Iron Ingot', 19683
);
insert into Item (name, gw2ItemId) VALUES (
  'Darksteel Ingot', 19681
);
insert into Item (name, gw2ItemId) VALUES (
  'Deldrimor Steel Ingot', 46738
);
insert into Item (name, gw2ItemId) VALUES (
  'Amalgamated Gemstone', 68063
);
insert into Item (name, gw2ItemId) VALUES (
  'Mystic Coin', 19976
);

-- ItemTask data for The Shining Blade
insert into TaskItem (gw2ItemId, taskId, quantity) VALUES (
  19976,30, 1
);
insert into TaskItem (gw2ItemId, taskId, quantity) VALUES (
  19976,19, 1
);
insert into TaskItem (gw2ItemId, taskId, quantity) VALUES (
  68063, 4, 1
);


-- --------------------------------------------------------------------------------------------------------------------
-- insert data for PrimaryItem
-- for The Binding of Ipos (ids 5-6)
insert into PrimaryItem (name) VALUES(
  'Ars Goetia'
);

insert into PrimaryItem (name) VALUES(
  'Gift of Ipos'
);

-- insert data for LegendaryPrimaryItem
-- for the Binding of Ipos (ids 5,6,7,8)
insert into LegendaryPrimaryItem (primaryItemId, legendaryId) VALUES (
  3,11
);
insert into LegendaryPrimaryItem (primaryItemId, legendaryId) VALUES (
  4,11
);
insert into LegendaryPrimaryItem (primaryItemId, legendaryId) VALUES (
    6,11
);
insert into LegendaryPrimaryItem (primaryItemId, legendaryId) VALUES (
    5,11
);

-- insert data for Task
-- for the Binding of Ipos, Ars Goetia
insert into Task (name, description, legendaryPrimaryItemId) values (
  'Mark of the Unnamed', 'The first tier precursor of The Binding of Ipos.', 8
);
insert into Task (name, description, legendaryPrimaryItemId) VALUES (
  'The True Name', 'The second tier precursor of The Binding of Ipos.', 8
);
insert into Task (name, description, legendaryPrimaryItemId) VALUES (
  'Ars Goetia', 'The precursor for The Binding of Ipos.', 8
);

-- for the Binding of Ipos, Gift of Ipos
insert into Task (name, description, legendaryPrimaryItemId) VALUES (
  'Gift of the Mists', 'Created in the Mystic Forge. <div class="recipe">Recipe</div>.', 7
);
insert into Task (name, description, legendaryPrimaryItemId, quantity) VALUES (
  'Shard of the Dark Arts', 'Crafted by Artificers. Requires 450 skill.<div class="recipe">Recipe<ul><li>1 Tribute to the Dark Arts</li><li>1 Mystic Curio</li><li>30 Mithril Ingots</li><li>20 Elder Wood Planks</li></ul></div>', 7, 100
);
insert into Task (name, description, legendaryPrimaryItemId, quantity) VALUES (
  'Mystic Runestone', 'Purchased from Miyani for 1g.', 7, 100
);
insert into Task (name, description, legendaryPrimaryItemId) VALUES (
  'Gift of Energy', 'Created in the Mystic Forge. <div class="recipe">Recipe</div>.', 7
);

-- for the Binding of Ipos, Mystic Tribute
insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
'Gift of Condensed Magic', 'Created in the Mystic Forge. <div class="recipe">Recipe</div>', 6, 2
);
insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
  'Gift of Condensed Might', 'Created in the Mystic Forge. <div class="recipe">Recipe</div>', 6, 2
);
insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
  'Mystic Clover', 'Created in the mystic forge, earned through PvP reward tracks, or from log-in rewards.', 6, 77
);
insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
  'Mystic Coin', 'Earned from log-in rewards or purchasable on the trading post.', 6, 250
);

-- for The Binding of Ipos, Gift of Maguuma Mastery
insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of Maguuma', 'Combine Gift of the Jungle, Gift of the Chak, Gift of Tarir, and Gift of the Fleet together in the Mystic Forge.', 5
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of Insights', 'Created in the Mystic Forge. <div class="recipe">Recipe</div>', 5
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Bloodstone Shard', 'Purchased from Miyani or any Mystic Forge Attendant for 200 Spirit Shards.', 5
);

insert into Task (name, description, legendaryPrimaryItemId, quantity) values (
  'Crystalline Ingot', 'Crafted by weaponsmiths. <div class="recipe">Recipe</div>', 5, 250
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of the Fleet', 'Obtained upon map completion of Verdant Brink.', 5
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of Tarir', 'Obtained upon map completion of Auric Basin.', 5
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of the Chak', 'Obtained upon map completion of Tangled Depths.', 5
);

insert into Task (name, description, legendaryPrimaryItemId) values (
  'Gift of the Jungle', 'Obtained upon map completion of Dragon\'s Stand.', 5
);

-- insert data for Sunrise

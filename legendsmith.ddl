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
  primaryItemId INT         NULL,
  CONSTRAINT Task_ibfk_1
  FOREIGN KEY (primaryItemId) REFERENCES PrimaryItem (id)
);
CREATE INDEX primaryItemId
  ON Task (primaryItemId);


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
CREATE INDEX legendaryId
  ON UserLegendary (legendaryId);


-- auto-generated definition
CREATE TABLE LegendaryPrimaryItem
(
  id            INT NOT NULL
    PRIMARY KEY,
  legendaryId   INT NULL,
  primaryItemId INT NULL,
  CONSTRAINT LegendaryPrimaryItem_ibfk_1
  FOREIGN KEY (legendaryId) REFERENCES Legendary (id),
  CONSTRAINT LegendaryPrimaryItem_ibfk_2
  FOREIGN KEY (primaryItemId) REFERENCES PrimaryItem (id)
);
CREATE INDEX legendaryId
  ON LegendaryPrimaryItem (legendaryId);
CREATE INDEX primaryItemId
  ON LegendaryPrimaryItem (primaryItemId);

-- auto-generated definition
CREATE TABLE UserLegendaryPrimaryItem
(
  id                     INT NOT NULL
    PRIMARY KEY,
  legendaryPrimaryItemId INT NULL,
  userId                 INT NULL,
  progress               INT NULL,
  CONSTRAINT UserLegendaryPrimaryItem_ibfk_1
  FOREIGN KEY (legendaryPrimaryItemId) REFERENCES LegendaryPrimaryItem (id),
  CONSTRAINT UserLegendaryPrimaryItem_ibfk_2
  FOREIGN KEY (userId) REFERENCES User (id)
);
CREATE INDEX legendaryPrimaryItemId
  ON UserLegendaryPrimaryItem (legendaryPrimaryItemId);
CREATE INDEX userId
  ON UserLegendaryPrimaryItem (userId);

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
CREATE INDEX userLegendaryPrimaryItemId
  ON UserLegendaryPrimaryItemTask (userLegendaryPrimaryItemId);


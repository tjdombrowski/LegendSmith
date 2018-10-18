delete from LegendaryPrimaryItem;
delete from PrimaryItem;
delete from Legendary;
INSERT into Legendary (id, name, type) values(1, 'Bolt', 'Sword');
INSERT into Legendary (id, name, type) values(2, 'The Shining Blade', 'Sword');
INSERT into Legendary (id, name, type) values(3, 'Chuka and Champawat', 'Shortbow');
INSERT into Legendary (id, name, type) values(4, 'Sharur', 'Hammer');
INSERT into Legendary (id, name, type) values(5, 'Astralaria', 'Axe');
INSERT into Legendary (id, name, type) values(6, 'The Minstrel', 'Focus');
INSERT into Legendary (id, name, type) values(7, 'The Binding of Ipos', 'Focus');
insert into PrimaryItem (id, name) VALUES(1, 'Save the Queen');
insert into PrimaryItem (id, name) VALUES(2, 'Gift of the Blade');
insert into PrimaryItem (id, name) VALUES(3, 'Gift of Maguuma Mastery');
insert into PrimaryItem (id, name) VALUES(4, 'Mystic Tribute');
insert into LegendaryPrimaryItem (id, legendaryId, primaryItemId) VALUES(1, 2, 1);
insert into LegendaryPrimaryItem (id, legendaryId, primaryItemId) VALUES(2, 2, 2);
insert into LegendaryPrimaryItem (id, legendaryId, primaryItemId) VALUES(3, 2, 3);
insert into LegendaryPrimaryItem (id, legendaryId, primaryItemId) VALUES(4, 2, 4);


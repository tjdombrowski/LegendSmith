# Database Design

### User
Represents the user. Not a lot of data on the user is stored.
 * Id [pk]
 * Username
 * Password

### Legendary
Represents each individual legendary weapon.
 * Id [pk]
 * Name
 * PictureReference
 * Type (Weapon type)
 
 ### Game
 * Name [pk]
 
 ### GameLegendary
 * GameName [pk/fk]
 * LegendaryId [pk/fk] 

### UserLegendary
Represents the intersection between the user
 * UserId [pk/fk]
 * LegendaryId [pk/fk]
 * Progress (stored as a percentage and calculated from the individual progress in UserLegendaryPrimaryItem)
 * Tracking (boolean that represents whether this weapon is being worked on/tracked by the user. Defaults false)
 * Priority (boolean that represents whether this weapon is the focus of the user, and should be displayed first. Only ONE can have this status at a time)

### PrimaryItem
One of the four primary items needed to craft a legendary. Some of these are used by multiple legendaries. 
 * Id [pk]
 * Name
 * PictureReference

### LegendaryPrimaryItem
Represents the intersection between a legendary weapon and the primary item it requires.
* Id [pk]
* LegendaryId [fk]
* PrimaryItemId [fk]

### UserLegendaryPrimaryItem
Represents the intersection between a user and the specific primary item (corresponding to a single legendary).
* Id [pk]
* LegendaryPrimaryItemId [fk]
* UserId [fk]
* Progress (stored as a percentage and calculated from completion of corresponding tasks)

### Task
Represents each individual task or item needed to complete a primary item. These are unique for each primary item.
* Id [pk]
* Name
* PrimaryItemId [fk]

### UserLegendaryPrimaryItemTask
The intersection between an user's primary item and a single task.
* UserLegendaryPrimaryItemId [pk/fk]
* TaskId [pk/fk]
* Completion (a boolean value that is false by default)
* DateCompleted (Value is established at the same time Completion is set to true, but if set back to false, then the date is removed)





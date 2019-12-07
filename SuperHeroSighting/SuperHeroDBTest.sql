Drop Database If Exists SuperHeroSightingTest;

Create DataBase If Not Exists SuperHeroSightingTest;

Use SuperHeroSightingTest;


Create Table superPower(
	superPowerId Int Primary Key Auto_Increment,
    powerType varChar(50) Not Null
);

Create Table hero(
	heroId Int Primary Key Auto_Increment,
	`name` varChar(50) Not Null,
    `description` varChar(100) Not Null,
    superPowerId Int Not Null,
    Foreign Key (superPowerId) references superPower(superPowerId)
);

Create Table location(
	locationId Int Primary Key Auto_Increment,
    `name` varChar(50) Not Null,
    `description` varChar(100), 
    address varChar(100) Not Null,
    latitude FLOAT( 10, 6 ) NOT NULL,
    longitude FLOAT( 10, 6 ) NOT NULL
);

Create Table `organization`(
	organizationId Int Primary Key Auto_Increment,
    locationId Int Not Null,
    `name` varChar(50) Not Null,
    `description` varChar(100),
    phone varChar(50) Not Null,
    email varChar(50) Not Null,
    Foreign Key(locationId) References location(locationId)
);

Create Table heroOrganization(
	heroId Int Not Null,
    organizationId Int Not Null,
    Primary Key (heroId, organizationId),
    Foreign Key (heroId) references hero(heroId),
    Foreign Key (organizationId) references `organization`(organizationId)
);

Create Table sighting(
	sightingId Int Primary Key Auto_Increment, 
    locationId Int Not Null,
    heroId Int Not Null, 
    seenDate Date Not Null,
    Foreign Key (locationId) references location(locationId),
	Foreign Key (heroId) references hero(heroId)    
);

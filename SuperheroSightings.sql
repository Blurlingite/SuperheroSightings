DROP DATABASE IF EXISTS SuperheroSightings;
CREATE DATABASE SuperheroSightings;
USE SuperheroSightings;

-- isHero can be null to represent normal people 
-- If is Hero is false, they are a supervillan, if true, they are a superhero
CREATE TABLE IF NOT EXISTS Person (
 PersonID INT NOT NULL AUTO_INCREMENT,
 FirstName VARCHAR(100) NOT NULL,
 LastName VARCHAR(100) NULL,
  isHero BOOLEAN NULL,
 PersonDescription VARCHAR(100) NOT NULL,
 PRIMARY KEY (PersonID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS Superpowers (
 SuperpowerID INT NOT NULL AUTO_INCREMENT,
 Superpower VARCHAR(45) NOT NULL,
 SuperpowerDescription VARCHAR(200),
 PRIMARY KEY (SuperpowerID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS PersonSuperpowers(
PersonSuperpowerID INT NOT NULL AUTO_INCREMENT,
PersonID INT NOT NULL,
SuperpowerID INT NOT NULL,
PRIMARY KEY(PersonSuperpowerID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS Organizations(
OrganizationID INT NOT NULL AUTO_INCREMENT,
OrganizationName VARCHAR(100) NOT NULL,
-- doesn't have to be a hero or villian organization
isHeroOrganization BOOLEAN NOT NULL,
OrganizationDescription VARCHAR(100) NOT NULL,
OrganizationCountry VARCHAR(100) NOT NULL,
OrganizationState VARCHAR(100) NOT NULL,
OrganizationCity VARCHAR(100) NOT NULL,
OrganizationStreet VARCHAR(100) NOT NULL,
OrganizationZipCode VARCHAR(10) NOT NULL,
PRIMARY KEY(OrganizationID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS OrganizationMembers(
OrganizationMemberID INT NOT NULL AUTO_INCREMENT,
OrganizationID INT NOT NULL,
PersonID INT  NOT NULL,
PRIMARY KEY(OrganizationMemberID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS Location(
LocationID INT NOT NULL AUTO_INCREMENT,
LocationName VARCHAR(100) NOT NULL,
LocationDescription VARCHAR(100) NOT NULL,
LocationCountry VARCHAR(100) NOT NULL,
LocationState VARCHAR(100) NOT NULL,
LocationCity VARCHAR(100) NOT NULL,
LocationStreet VARCHAR(100) NOT NULL,
LocationZipCode VARCHAR(10) NOT NULL,
Latitude DECIMAL(10,4) NOT NULL,
Longitude DECIMAL(10,4) NOT NULL,
PRIMARY KEY(LocationID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS Sightings(
SightingID INT NOT NULL AUTO_INCREMENT,
isHeroSighting BOOLEAN NULL,
PersonID INT NOT NULL,
LocationID INT NOT NULL,
SightingDate DATETIME NOT NULL,
PRIMARY KEY(SightingID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;




CREATE TABLE IF NOT EXISTS Users(

UserID INT NOT NULL AUTO_INCREMENT,
Username VARCHAR(45) NOT NULL,
UserPassword VARCHAR(100) NOT NULL,
FirstName VARCHAR(45) NOT NULL,
LastName VARCHAR(45) NOT NULL,
Email VARCHAR(100) NOT NULL,
isAdmin BOOLEAN NOT NULL,
Enabled TINYINT(1) NOT NULL DEFAULT 1,
PRIMARY KEY(UserID),
 KEY Username (Username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;





CREATE TABLE IF NOT EXISTS Authorities(

Username VARCHAR(45) NOT NULL,
Authority VARCHAR(45) NOT NULL,
 KEY Username (Username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS OrganizationAdmins(
OrganizationAdminID INT NOT NULL AUTO_INCREMENT,
OrganizationID INT NOT NULL,
UserID INT NOT NULL,
PRIMARY KEY(OrganizationAdminID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;








ALTER TABLE Sightings
ADD CONSTRAINT fk_Sightings_Person
FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
ON DELETE CASCADE;

ALTER TABLE Sightings
ADD CONSTRAINT fk_Sightings_Location
FOREIGN KEY (LocationID) REFERENCES Location(LocationID)
ON DELETE CASCADE;







ALTER TABLE PersonSuperpowers
ADD CONSTRAINT fk_Person_PersonSuperpowers
FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
ON DELETE CASCADE;

ALTER TABLE PersonSuperpowers
ADD CONSTRAINT fk_Superpowers_PersonSuperpowers
FOREIGN KEY (SuperpowerID) REFERENCES Superpowers(SuperpowerID)
ON DELETE CASCADE;

ALTER TABLE OrganizationMembers
ADD CONSTRAINT fk_Organizations_OrganizationMembers
FOREIGN KEY (OrganizationID) REFERENCES Organizations(OrganizationID)
ON DELETE CASCADE;

ALTER TABLE OrganizationMembers
ADD CONSTRAINT fk_Person_OrganizationMembers
FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
ON DELETE CASCADE;




ALTER TABLE OrganizationAdmins
ADD CONSTRAINT fk_OrganizationAdmins_Organizations
FOREIGN KEY (OrganizationID) REFERENCES Organizations(OrganizationID)
ON DELETE CASCADE;

ALTER TABLE OrganizationAdmins
ADD CONSTRAINT fk_OrganizationAdmins_Users
FOREIGN KEY (UserID) REFERENCES Users(UserID)
ON DELETE CASCADE;






INSERT INTO Person(FirstName, LastName, isHero, PersonDescription)
VALUES('Storm', NULL, TRUE, 'One of the X-Men'), 
('Raven', NULL, TRUE, 'A Teen Titan'), 
('Nick', 'Fury', NULL, 'Commander at S.H.I.E.L.D'),
('Overload', NULL, FALSE, 'A giant electrical supervillian'),
('Dr. Strange', NULL, TRUE, ' the primary protector of Earth against magical and mystical threats'),
('Magneto', NULL, FALSE, ' A powerful mutant who can control magnetic fields');


INSERT INTO Superpowers(Superpower, SuperpowerDescription)
VALUES('Weather Power', 'Can control the weather'),
('Power of Flight', 'Can fly'),
('None', 'No Superpower'),
('Earth Power', 'Can move earth'),
('Electric Power', 'Can control electricity'),
('Time Power','Can control time'),
('Magnetize', 'Can control magnetism');


INSERT INTO PersonSuperpowers(PersonID, SuperpowerID)
VALUES(1,1),
(1,2),
(2,2),
(2,4),
(3,3),
(4,5),
(5,6),
(5,2),
(6,2),
(6,7);

INSERT INTO Organizations(OrganizationName, isHeroOrganization, OrganizationDescription, OrganizationCountry, OrganizationState, OrganizationCity, OrganizationStreet, OrganizationZipcode )
VALUES('X-Men', TRUE, 'Mutants that fight crime', 'United States', 'New York', 'Westchester County', '1407 Graymalkin Lane, Salem Center', '20837' ),
('Teen Titans', TRUE, 'Teenage superheroes led by Robin', 'United States', 'California', 'San Francisco', 'On an island', '12345'),
('S.H.I.E.L.D', TRUE, 'Strategic Hazard Intervention Espionage Logistics Directorate for special law enforncement', 'United States', 'New York', 'New York City', '34th Street', '36838'),
('Brotherhood of Evil', FALSE, 'Archenemies of the original Doom Patrol and the Teen Titans', 'France', 'Île-de-France', 'Paris', '17th Street', '34899'),
('Brotherhood of Mutants', FALSE, 'Founded by Magneto. Enemies of the X-Men', 'United States', 'Washington', 'Seattle', '13th Street', '34129');

INSERT INTO OrganizationMembers(OrganizationID, PersonID)
VALUES(1,1),
(2,2),
(3,3),
(4,4),
(5,6);

INSERT INTO Location(LocationName, LocationDescription, LocationStreet, LocationCity, LocationState, LocationZipCode, LocationCountry, Latitude, Longitude)
VALUES('The Bean', 'A coffee spot with vegan options', 'West 4th Street', 'New York City', 'New York', '10003', 'United States',  40.445, 42.345),
('Panera Bread', 'Counter-serve bakery/cafe chain serving sandwiches, salads & more, known for its bread & free WiFi', '61-35 Junction Blvd,', 'Rego Park', 'New York', '11374', 'United States', 22.322, 23.246),
('National Solar Observatory', 'A  public research institute to advance the knowledge of the physics of the Sun', '3004 Telescope Loop', 'Sunspot', 'New Mexico', '88349', 'United States', 21.222, 22.543),
('The Cheesecake Factory', 'A restaurant company and distributor of cheesecakes based in the United States', '90-15 Queens Blvd, Elmhurst', 'New York City', 'New York', '11373', 'United States', 26.292, 27.523),
('Whole Foods Market', 'Eco-minded chain with natural & organic grocery items, housewares & other products (most sell wine) ', '808 Columbus Ave,', 'New York City', 'New York', '10025', 'United States', 11.272, 17.523);


INSERT INTO Sightings(isHeroSighting, PersonID, LocationID, SightingDate)
VALUES(TRUE, 1, 1,  '2016-11-05 10:12:33'),
(TRUE, 2, 2,  '2018-03-07 09:22:13'),
(NULL, 3, 3,  '2015-01-15 01:42:19'),
(FALSE, 4, 3,  '2013-04-25 06:18:53'),
(TRUE,5,4,'2018-03-07 07:18:53'),
(FALSE,6,4,'2018-03-07 07:18:53'),
(TRUE,3,1,'2018-03-07 07:18:53'),
(TRUE,2,4,'2018-03-07 07:18:53'),
(FALSE,6,4,'2018-03-07 07:18:53'),
(FALSE,6,3,'2018-03-07 07:18:53'),
(TRUE,1,5,'2019-01-07 01:48:53');




INSERT INTO Users(Username, UserPassword, FirstName, LastName, Email, isAdmin, Enabled)
VALUES('jbonds', 'OceanSpy', 'James', 'Bond', 'jbonds@gmail.com', TRUE, 1),
('jkennedy', 'jackie06', 'Jackie', 'Kennedy', 'jkennedy@gmail.com', TRUE, 1),
('swilliams', 'sasha11', 'Sasha', 'Williams', 'swilliams@gmail.com', TRUE, 1),
('dsmith', 'dante09', 'Dante', 'Smith', 'dsmith@gmail.com', TRUE, 1),
('admin', 'admin', 'Admin', 'Admin', 'admin@gmail.com', TRUE, 1),
('user', 'user', 'User', 'User', 'user@gmail.com', FALSE, 1);





INSERT INTO Authorities(Username, Authority)
VALUES('jbonds','ROLE_ADMIN'),
('jkennedy','ROLE_USER'),
('swilliams','ROLE_USER'),
('dsmith','ROLE_USER'),
('admin','ROLE_ADMIN'),
('user','ROLE_USER');







INSERT INTO OrganizationAdmins(OrganizationID, UserID)
VALUES(1,1),
(2,2),
(3,3),
(4,4);


UPDATE Users
SET
UserPassword = '$2a$10$ruJxouftFc3EU2cwRZnVeeFqsAX9J7EqVK7ruPAOyv1pTsOZQwJPe'
WHERE UserID = 5;


UPDATE Users
SET
UserPassword = '$2a$10$q3V5FS4q/.t63.Z9NbbeM.zUoKeuqPzXkwJ0StWYuQC3YjkJWOMq.'
WHERE UserID = 6;

-- vd



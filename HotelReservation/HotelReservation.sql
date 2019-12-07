Drop database if exists hotelreservation;

Create database hotelReservation;

Use hotelreservation;

Create table RoomType(
	RoomTypeId Int primary key Auto_Increment,
    RoomTypeName varchar(10) Not Null,
	StandardOccupancy Int Not Null,
    MaximumOccupancy Int Not Null,
    BasePrice Decimal(6, 2)  Null,
	ExtraPerson Int	Not Null
    
    );

Create table RoomData (
	RoomNumberId Int  primary key Not Null,
	RoomTypeID Int Not Null,
	ADAaccessible bool Not Null Default 1,

	foreign key fk_RoomAmenity_RoomTypeId (RoomTypeId)
	references RoomType(RoomTypeId)
    );


Create Table Amenity(
	AmenityId Int primary key Not Null,
	AmenityName VarChar(40) Not Null,
    AmenityCost Decimal(6,2) Null
    );
    
Create Table RoomAmenity(
	primary key(RoomnumberId, AmenityId),
    RoomNumberId Int Not Null,
    AmenityId Int Not Null,
    
    foreign key fk_RoomoomAmenity_RoomNumberId(RoomNumberId)
	references RoomData(RoomNumberId),
    
	foreign key fk_RoomAmenity_AmenityId (AmenityId)
	references Amenity(AmenityId)
    
    );


Create Table Guest(
	GuestId Int primary key Auto_Increment,
	Address VarChar(40) Not Null,
	FirstName VarChar(40) Not Null,
    LastName VarChar(40) Not Null,
    City VarChar(40) Not Null,
    State VarChar(40) Not Null,
    Zip Int Not Null,
    Phone VarChar(40) Not Null
    );

Create Table Reservation (
	ReservationId Int primary key Auto_Increment,
    GuestId Int Not Null,
    StartDate Date Not Null,
    EndDate Date Not Null,
    
    foreign key fk_Reservation_GuestId(GuestId)
	references Guest(GuestId)
    );

Create Table RoomReservation(
primary key(RoomNumberId, ReservationId),
	RoomNumberId Int Not Null,
	ReservationId Int  Not Null,
    Adult Int Not Null,
    Children Int Not Null,
    
    foreign key fk_RoomReservation_RoomNumberId(RoomNumberId)
	references RoomData(RoomNumberId),
    
    foreign key fk_RoomReservation_ReservationId(ReservationId)
	references Reservation(ReservationId)
);


Insert Into RoomType (RoomTypeName, StandardOccupancy, MaximumOccupancy, BasePrice, ExtraPerson)
values
('Single', 2, 2, 149.99, 0),
('Double', 2, 4, 174.99, 10),
('Suite', 3, 8, 399.99, 20);

Insert Into RoomData (RoomNumberId, RoomTypeID, ADAaccessible)
values
(201, 2, false),
(202, 2, true),
(203, 2, false),
(204, 2, true),
(205, 1, false),
(206, 1, true),
(207, 1, false),
(208, 1, true),
(301, 2, false),
(302, 2, true),
(303, 2, false),
(304, 2, true),
(305, 1, false),
(306, 1, true),
(307, 1, false),
(308, 1, true),
(401, 3, true),
(402, 3, true);

Insert Into Amenity (AmenityId, AmenityName, AmenityCost)
values
(1, 'Microwave', 0),
(2, 'Refrigerator', 0),
(3, 'Oven', 0),
(4, 'Jacuzzi', 25.00);

Insert Into RoomAmenity (RoomNumberId, AmenityId)
values
(201, 1),
(201, 4),
(202, 2),
(203, 1),
(203, 4),
(204, 2),
(205, 1),
(205, 2),
(205, 4),
(206, 1),
(206, 2),
(207, 1),
(207, 2),
(207, 4),
(208, 1),
(208, 2),
(301, 1),
(301, 4),
(302, 2),
(303, 1),
(303, 4),
(304, 2),
(305, 1),
(305, 2),
(305, 4),
(306, 1),
(306, 2),
(307, 1),
(307, 2),
(307, 4),
(308, 1),
(308, 2),
(401, 1),
(401, 2),
(401, 3),
(402, 1),
(402, 2),
(402, 3);


Insert Into Guest (Address, FirstName, LastName, City, State, Zip, Phone)
values

('379 Old Shore Street', 'Mack', 'Simmer', 'Council Bluffs', 'IA',	51501, '(291) 553-0508'),
('750 Wintergreen Dr.', 'Bettyann', 'Seery', 'Wasilla', 'AK', 99654, '(478) 277-9632'),
('9662 Foxrun Lane', 'Duane', 'Cullison', 'Harlingen', 'TX', 78552, '(308) 494-0198'),
('9378 W. Augusta Ave.', 'Karie', 'Yang', 'West Deptford',	'NJ', 08096, '(214) 730-0298'),
('762 Wild Rose Stree', 'Aurore', 'Lipton', 'Saginaw',	'MI', 48601, '(377) 507-0974'),
(' Poplar Dr.', 'Zachery', 'Luechtefeld',	'Arvada', 'CO', 80003, '(814) 485-2615'),
('70 Oakwood St.', 'Jeremiah', 'Pendergrass', 'Zion', 'IL', 60099, '(279) 491-0960'),
('7556 Arrowhead St.', 'Walter', 'Holaway', 'Cumberland', 'RI', 02864, '(446) 396-6785'),
('77 West Surrey Street', 'Wilfred', 'Vise', 'Oswego', 'NY', 13126, '(834) 727-1001'),
('939 Linda Rd.', 'Maritza', 'Tilton', 'Burke', 'VA', 22015, '(446) 351-6860'),
('87 Queen St.', 'Joleen', 'Tison', 'Drexel Hill', 'PA', 19026, '(231) 893-2755'),
('3041 bryant Ave, S.', 'Jun Ho', 'Lee', 'Minneapolis', 'MN', 55408, '(612) 720-2357');

Insert Into Reservation (GuestId, StartDate, EndDate)
values
(1, '2023-2-2', '2023-2-4'),
(2, '2023-2-5', '2023-2-10'),
(3, '2023-2-22', '2023-2-24'),
(4, '2023-3-6', '2023-3-7'),
(12, '2023-3-17', '2023-3-20'),
(5, '2023-3-18', '2023-3-23'),
(6, '2023-3-29', '2023-3-31'),
(7, '2023-3-31', '2023-4-5'),
(8, '2023-4-9', '2023-4-13'),
(9, '2023-4-23', '2023-4-24'),
(10, '2023-5-30', '2023-6-2'),
(11, '2023-6-10', '2023-6-14'),
(5,'2023-6-17', '2023-6-18'),
(12, '2023-6-28', '2023-7-2'),
(8, '2023-7-13', '2023-7-14'),
(9, '2023-7-18', '2023-7-21'),
(2, '2023-7-28', '2023-7-29'),
(2, '2023-8-30', '2023-9-1'),
(1, '2023-9-16', '2023-9-17'),
(4, '2023-9-13', '2023-9-15'),
(3, '2023-11-22', '2023-11-25'),
(1, '2023-11-22', '2023-11-25'),
(10, '2023-12-24', '2023-12-28');

Insert Into RoomReservation (RoomNumberId, ReservationId, Adult, Children)
values
(308, 1, 1, 0),
(203, 2, 2, 1),	
(305, 3, 2, 0),	
(201, 4, 2, 2),	
(307, 5, 1, 1),	
(302, 6, 3, 0),	
(202, 7, 2, 2),	
(304, 8, 2, 0),	
(301, 9, 1, 0),	
(207, 10, 1, 1),	
(401, 11, 2, 4),	
(206, 12, 2, 0),	
(208, 12, 1, 0),	
(304, 13, 3, 0),	
(205, 14, 2, 0),	
(204, 15, 3, 1),	
(401, 16, 4, 2),	
(303, 17, 2, 1),	
(305, 18, 1, 0),	
(208, 19, 2, 0),	
(203, 20, 2, 2),	
(401, 21, 2, 2),	
(206, 22, 2, 0),	
(301, 22, 2, 2),	
(302, 23, 2, 0);

Delete From RoomReservation
Where ReservationId = 8;

Delete From reservation
Where GuestId = 7;

Delete From guest
Where GuestId = 7;

-- Write a query that returns a list of reservations that end in July 2023, including the name of the guest,
-- the room number(s), and the reservation dates.
Select * 
From Reservation
Where  month(EndDate) = 7 and year(EndDate) = 2023;

-- Write a query that returns a list of all reservations for rooms with a jacuzzi, 
-- displaying the guest's name, the room number, and the dates of the reservation.
Select ra.RoomNumberId, ra.AmenityId, g.FirstName, g.LastName, r.StartDate, r.EndDate
From RoomAmenity ra
Inner join RoomData rd on ra.RoomNumberId = rd.RoomNumbeRId
Inner join RoomReservation rr on rd.RoomNumberId = rr.RoomNumberId
Inner join Reservation r on rr.ReservationId = r.ReservationId
Inner join Guest g on r.GuestId = g.GuestId
Where AmenityId = 4;

-- Write a query that returns all the rooms reserved for a specific guest, including the guest's name, 
-- the room(s) reserved, the starting date of the reservation, 
-- and how many people were included in the reservation. (Choose a guest's name from the existing data.)
Select g.FirstName, g.LastName, rd.RoomNumberId, rr.Adult + rr.Children TotalPeople, r.StartDate
From guest g
Inner join reservation r on g.GuestId = r.GuestId
Inner join RoomReservation rr on r.ReservationId = rr.ReservationId
Inner join RoomData rd on rr.RoomNumberId = rd.RoomNumberId
where FirstName = 'Mack';

-- Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation.
--  The results should include all rooms, whether or not there is a reservation associated with the room.
Select rd.RoomNumberId, r.ReservationId, (rt.BasePrice + Sum(a.AmenityCost) + 
(greatest(rr.Adult - rt.StandardOccupancy, 0))*rt.ExtraPerson)*DATEDIFF(r.EndDate,r.StartDate) TotalPrice
From RoomData rd
Left join RoomReservation rr on rd.RoomNumberId = rr.RoomNumberId
Left join Reservation r on rr.ReservationId = r.ReservationId
Left join RoomType rt on rd.RoomTypeId = rt.RoomTypeId
Left join RoomAmenity am on rd.RoomNumberId = am.RoomNumberId
Left join Amenity a on am.AmenityId = a.AmenityId
Group By rr.RoomNumberId,r.ReservationId;

-- Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023.
Select rd.RoomNumberId,(sum(rr.Adult + rr.Children) >= 3) guestsOver3
From Reservation r 
Inner join RoomReservation rr on r.ReservationId = rr.ReservationId
Inner join RoomData rd on rr.RoomNumberId = rd.RoomNumberId
Inner join RoomType rt on rd.RoomTypeId = rt.RoomTypeId
where month(EndDate) = 4 and year(EndDate) = 2023
group by rd.RoomNumberId 
having guestsOver3;

-- Write a query that returns a list of all guest names and the number of reservations per guest, 
-- sorted starting with the guest with the most reservations and then by the guest's last name.
Select g.guestId, count(r.ReservationId) AmountOfReservation, g.FirstName, g.LastName
from guest g
Inner join reservation r on g.GuestId = r.guestId
Inner join RoomReservation rr on r.ReservationId = rr.ReservationId
group by g.GuestId;

-- Write a query that displays the name, address, and phone number of a guest based on their phone number. 
-- (Choose a phone number from the existing data.)
Select *
from guest g
where Phone = '(291) 553-0508';
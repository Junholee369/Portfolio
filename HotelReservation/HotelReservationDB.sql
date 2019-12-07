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
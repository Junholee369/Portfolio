Use hotelreservation;

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
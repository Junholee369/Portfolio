Drop database if exists GuessingGameTest;

Create database GuessingGameTest;

Use GuessingGameTest;

create table Game(
	GameId int primary key Auto_Increment,
    Answer int not null,
	EndGame bool not null

);

create table Round(
	roundId int primary key Auto_Increment,
	GameId int not null,
	roundTime dateTime not null,
	userGuess int not null,
    partialGuess int not null,
	exactGuess int not null,
    
	foreign key fk_round_GameId (GameId)
	references Game(GameId)
);
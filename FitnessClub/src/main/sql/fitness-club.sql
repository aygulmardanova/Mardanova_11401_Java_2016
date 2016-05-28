CREATE TABLE Users (
	"id" SERIAL PRIMARY KEY,
	"login" VARCHAR(30) NOT NULL,
	"password" VARCHAR(30) NOT NULL,
	"email" VARCHAR(40) NOT NULL,
	"name" VARCHAR(30),
	"surname" VARCHAR(40),
	"photo" VARCHAR(100),
	"phone_number" VARCHAR(11),
	"role" VARCHAR(20)
);

CREATE TABLE Class (
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(30) NOT NULL,
	"description" VARCHAR(2000),
	"photo" VARCHAR(100)
);

CREATE TABLE Instructor(
	"id" SERIAL PRIMARY KEY,
	"users_id" INTEGER NOT NULL REFERENCES Users("id"),
	"description" VARCHAR(1000),
	"qualification" VARCHAR(400),
	"awards" VARCHAR(400),
	"experience" DATE
);

CREATE TABLE Schedule(
	"id" SERIAL PRIMARY KEY,
	"class_id" INTEGER REFERENCES Class("id"),
	"instr_id" INTEGER REFERENCES Instructor("id"),
	"startTime" INTEGER NOT NULL CONSTRAINT CH_start_time CHECK ("startTime" BETWEEN 9 AND 20),
	"dayOfWeek" VARCHAR(10) NOT NULL CONSTRAINT CH_day_of_week CHECK("dayOfWeek" IN ('MONDAY',
		'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'))
);

CREATE TABLE ProbablyInstructor (
	"id" SERIAL PRIMARY KEY,
	"users_id" INTEGER NOT NULL REFERENCES Users("id")
);

CREATE TABLE Subscription (
	"id" SERIAL PRIMARY KEY,
	"validity" INTEGER NOT NULL CONSTRAINT CH_validity CHECK ("validity" BETWEEN 1 AND 15),
	"price" INTEGER
);

CREATE TABLE Purchase (
	"id" SERIAL PRIMARY KEY,
	"users_id" INTEGER NOT NULL REFERENCES Users("id"),
	"subscr_id" INTEGER NOT NULL REFERENCES Subscription("id"),
	"buy_date" DATE NOT NULL,
	"prolong" INTEGER CONSTRAINT CH_prolong CHECK ("prolong" IN (0, 1))
);

ALTER TABLE users ADD CONSTRAINT UQ_login UNIQUE (login);
ALTER TABLE users ADD CONSTRAINT UQ_email UNIQUE (email);
ALTER TABLE class ADD CONSTRAINT UQ_name UNIQUE (name);
ALTER TABLE subscription ADD CONSTRAINT UQ_validity UNIQUE (validity);




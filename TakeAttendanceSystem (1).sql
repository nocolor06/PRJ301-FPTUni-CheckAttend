﻿--CREATE DATABASE PRJ301_TakeAttendanceSystem
USE PRJ301_TakeAttendanceSystem

CREATE TABLE Course
(
	courseId varchar(10) NOT NULL,
	courseName nvarchar(100),
	CONSTRAINT PK_Course PRIMARY KEY (courseId)
 )

 CREATE TABLE Instructor
 (
	instructorId varchar(20) NOT NULL,
	instructorName nvarchar(100),
	instructorImage varchar(max),
	CONSTRAINT PK_Instructor PRIMARY KEY (instructorId)

)

CREATE TABLE Student
(
	studentId varchar(8) NOT NULL,
	studentName nvarchar(100),
	studentImage varchar(max),
	CONSTRAINT PK_Student PRIMARY KEY (studentId)
 )

 CREATE TABLE TimeSlot
 (
	slotId int NOT NULL,
	slotNumber int,
	startTime time(0),
	endTime time(0),
	CONSTRAINT PK_TimeSlot PRIMARY KEY (slotId)
)

CREATE TABLE Room
(
	roomId varchar(10) NOT NULL,
	CONSTRAINT PK_Room PRIMARY KEY (roomId)
)

CREATE TABLE [Group]
(
	groupId int NOT NULL,
	groupName varchar(20) NULL,
	courseId varchar(10) NULL,
	instructorId varchar(20) NULL,
	CONSTRAINT PK_Group PRIMARY KEY (groupId)
)

CREATE TABLE [UserInstructor]
(
	username varchar(50) not null,
	[password] varchar(50) not null,
	id varchar(20) not null,
	role int not null
	CONSTRAINT PK_UserInstructor PRIMARY KEY (username)
)

CREATE TABLE [UserStudent]
(
	username varchar(50) not null,
	[password] varchar(50) not null,
	id varchar(8) not null,
	role int not null
	CONSTRAINT PK_UserStudent PRIMARY KEY(username)
)
ALTER TABLE [UserStudent] ADD CONSTRAINT FK_UserStudent_Student FOREIGN KEY(id)
REFERENCES STUDENT(studentId)
ALTER TABLE [UserInstructor] ADD CONSTRAINT FK_User_Instructor FOREIGN KEY(id)
REFERENCES Instructor(instructorId)

ALTER TABLE [Group] ADD CONSTRAINT FK_Group_Course FOREIGN KEY(courseId)
REFERENCES Course (courseId)
ALTER TABLE [Group] ADD CONSTRAINT FK_Group_Instructor FOREIGN KEY(instructorId)
REFERENCES Instructor (instructorId)

 CREATE TABLE Participate
 (
	groupId int NOT NULL,
	studentId varchar(8) NOT NULL,
	CONSTRAINT PK_Participate PRIMARY KEY (groupId, studentId)
)

ALTER TABLE Participate ADD CONSTRAINT FK_Participate_Group FOREIGN KEY(groupId)
REFERENCES [Group] (groupId)
ALTER TABLE Participate ADD CONSTRAINT FK_Participate_Student FOREIGN KEY(studentId)
REFERENCES Student (studentId)

 CREATE TABLE [Session]
 (
	sessionId int NOT NULL,
	sessionName nvarchar(200) NULL,
	[date] date NULL, 
	slotId int NULL,
	lecturerId varchar(20) NULL,
	groupId int NULL,
	roomId varchar(10) NULL,
	CONSTRAINT PK_Session PRIMARY KEY (sessionId)
)
ALTER TABLE [Session] ADD CONSTRAINT FK_Session_Group FOREIGN KEY(groupId)
REFERENCES [Group] (groupId)
ALTER TABLE [Session] ADD CONSTRAINT FK_Session_Room FOREIGN KEY(roomId)
REFERENCES Room (roomId)
ALTER TABLE [Session] ADD CONSTRAINT FK_Session_TimeSlot FOREIGN KEY(slotId)
REFERENCES TimeSlot (slotId)
ALTER TABLE [Session] ADD CONSTRAINT FK_Session_Instructor FOREIGN KEY(lecturerId)
REFERENCES Instructor(instructorId)

CREATE TABLE Attend
(
	aId int not Null IDENTITY(1,1) primary key,
	studentId varchar(8) NOT NULL,
	sessionId int NOT NULL,
	[status] bit NULL,
	recordTime datetime NULL,
	comment nvarchar(max),
)
ALTER TABLE Attend ADD CONSTRAINT FK_Attend_Session FOREIGN KEY(sessionId)
REFERENCES [Session] (sessionId)
ALTER TABLE Attend ADD CONSTRAINT FK_Attend_Student FOREIGN KEY(studentId)
REFERENCES Student (studentId)

/*
DROP TABLE Attend
DROP TABLE Participate
DROP TABLE [Session]
DROP TABLE [Group]
DROP TABLE Student
DROP TABLE Room
DROP TABLE Instructor
DROP TABLE TimeSlot
DROP TABLE Course
*/

/*
SELECT * FROM Student
SELECT * FROM Participate
SELECT * FROM [Session]
SELECT * FROM [Group]
SELECT * FROM Room
SELECT * FROM Instructor
SELECT * FROM TimeSlot
SELECT * FROM Attend
SELECT * FROM Course

--	Thứ tự xóa bảng 
DELETE FROM Attend
DELETE FROM Participate
DELETE FROM [Session]
DELETE FROM [Group]
DELETE FROM Student
DELETE FROM Room
DELETE FROM Instructor
DELETE FROM TimeSlot
DELETE FROM Course
*/
CREATE DATABASE Project1;
USE Project1;

CREATE TABLE Contractor
(
    ContractorID  INT PRIMARY KEY DEFAULT 1,       
    Username VARCHAR(50),
    Password VARCHAR(50),
    Email  VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into Contractor( Username, Password, Email)
values ( 'David', 'DC1000', 'David@gmail.com');

# Stores information about registered clients. ClientID is generated as a unique identifier
CREATE TABLE Client (
    ClientID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Password VARCHAR(50),
    Address VARCHAR(200),
    CreditCardInfo VARCHAR(10),
    PhoneNumber VARCHAR(10),
    Email VARCHAR(50) UNIQUE,
    UNIQUE (ClientID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into Client(  FirstName, LastName, Password, Address, CreditCardInfo, PhoneNumber, Email)
values ( 'Susie ', 'Guzman','susie123', '1234 whatever street detroit MI 48202','10000123', '11111', 'susie@gmail.com'),
  ('Lawson', 'Lee', 'mar123', '1234 ivan street tata CO 12561','10021250', '99999','margarita@gmail.com'),
  ('Brady', 'Plum', 'jo123', '3214 marko street brat DU 54321','10003674', '9990','jo@gmail.com'),
  ('Moore', 'Mone', 'wall123','4500 frey street sestra MI 48202','10500455', '19000', 'wallace@gmail.com'),
  ('Phillips', 'Zipp','ameli123','1245 m8s street baka IL 48000','12904550', '245000', 'amelia@gmail.com'),
  ('Francis','Hawkin', 'angelo123','4680 egypt street lolas DT 13579','10075670', '23440', 'angelo@gmail.com'),
  ('Smith', 'Joe','rudy123','1234 sign street samo ne tu MH 09876','10006785', '34560', 'rudy@gmail.com'),
  ('Stone', 'Pills','jean123','0981 snoop street kojik HW 87654','18006780', '99990', 'jeannette@gmail.com'),
  ('Susie', 'Guun', 'susie123', '1234 Whatever Street, Detroit, MI 48202', '10000123', '11111', 'susie12New@gmail.com'),
  ('John', 'Doe', 'john123', '5678 Main Street, Chicago, IL 60601', '98765432', '22222', 'john.doe@example.com'),
  ('Alice', 'Smith', 'alice123', '9101 Oak Avenue, New York, NY 10001', '54321098', '33333', 'alice.smith@example.com'),
  ('Robert', 'Johnson', 'robert123', '2468 Elm Drive, Los Angeles, CA 90001', '13579246', '44444', 'robert.j@example.com'),
  ('Emily', 'Clark', 'emily123', '1357 Pine Road, San Francisco, CA 94101', '86420975', '55555', 'emily.clark@example.com'),
  ('Daniel', 'White', 'daniel123', '7890 Cedar Lane, Houston, TX 77001', '75395128', '66666', 'daniel.white@example.com'),
  ('Megan', 'Miller', 'megan123', '1122 Birch Street, Miami, FL 33101', '46813579', '77777', 'megan.m@example.com'),
  ('Charlie', 'Davis', 'charlie123', '9922 Maple Avenue, Seattle, WA 98101', '24680135', '88888', 'charlie.d@example.com'),
  ('Sophia', 'Wilson', 'sophia123', '4455 Sycamore Lane, Atlanta, GA 30301', '91357248', '99999', 'sophia.w@example.com'),
  ('Ethan', 'Taylor', 'ethan123', '6677 Walnut Street, Denver, CO 80201', '35792468', '101010', 'ethan.t@example.com'),
  ('Emma', 'Harris', 'emma123', '8899 Oakwood Drive, Phoenix, AZ 85001', '24680135', '111111', 'emma.h@example.com'),
  ('Matthew', 'Brown', 'matthew123', '2233 Redwood Boulevard, Dallas, TX 75201', '46813579', '121212', 'matthew.b@example.com'),
  ('Olivia', 'Moore', 'olivia123', '7788 Pinecrest Road, Orlando, FL 32801', '75395128', '131313', 'olivia.m@example.com'),
  ('Aiden', 'Robinson', 'aiden123', '3355 Cedar Crest, San Diego, CA 92101', '13579246', '141414', 'aiden.r@example.com'),
  ('Ava', 'Perez', 'ava123', '6677 Cypress Lane, Austin, TX 78701', '86420975', '151515', 'ava.p@example.com');

select* from Client; 

# Stores information about tree-cutting requests submitted by clients. The Status field indicates whether the request is pending, accepted, or rejected
CREATE TABLE QuoteRequest
(
    RequestID   INT AUTO_INCREMENT PRIMARY KEY,
    ClientID   INT,   
    RequestDate DATE,
	NumberOfTrees INT,
    Note        VARCHAR(200),
    Size        VARCHAR(10), 
    Height      DECIMAL(3, 1), 
    Location    VARCHAR(100),
    ProximityToHouse FLOAT (20),
    Status      ENUM('Pending', 'Opened', 'Accepted') ,
    UNIQUE (RequestID),
    FOREIGN KEY (ClientID) REFERENCES Client (ClientID)    
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 insert into QuoteRequest (ClientID, RequestDate, NumberOfTrees, Note, Size, Height, Location, ProximityToHouse, Status)
 values  (1, '2022-11-10', 7, 'Need somefjj', 'Large', 12.5, 'backyard', 5.3, 'Accepted'),
    (2, '2022-11-12', 5, 'tata', 'Medium', 9.2, 'frontyard', 2.7, 'Pending'),
    (3, '2022-12-10', 4, 'brat', 'Small', 7.8, 'frontyard', 1.5, 'Accepted'),
    (4, '2022-12-01', 8, 'sestra', 'Medium', 8.6, 'backyard', 3.2, 'Accepted'),
    (5, '2023-08-12', 2, 'baka', 'Large', 11.4, 'backyard', 4.7, 'Accepted'),
    (6, '2023-06-02', 1, 'ides', 'Small', 6.9, 'frontyard', 1.8, 'Pending'),
    (7, '2023-06-04', 5, 'lolas', 'Large', 10.1, 'frontyard', 6.5, 'Accepted'),
    (8, '2023-03-12', 4, 'samo ne tu', 'Medium', 9.8, 'frontyard', 2.1, 'Accepted'),
    (9, '2023-04-05', 8, 'Lily Road', 'Large', 12.0, 'backyard', 5.7, 'Accepted'),
    (10, '2023-04-12', 5, 'Maple Street', 'Medium', 8.5, 'frontyard', 3.5, 'Accepted'),
    (11, '2023-05-01', 5, 'Oak Avenue', 'Small', 7.0, 'backyard', 2.0, 'Pending'),
    (12, '2023-05-18', 3, 'Pine Lane', 'Large', 11.2, 'frontyard', 4.8, 'Accepted'),
    (13, '2023-06-10', 2, 'Birch Street', 'Small', 6.0, 'backyard', 1.5, 'Accepted'),
    (14, '2023-06-25', 5, 'Willow Avenue', 'Medium', 9.8, 'frontyard', 3.2, 'Accepted'),
    (15, '2023-07-05', 2, 'Cherry Lane', 'Large', 13.5, 'backyard', 6.2, 'Pending'),
    (16, '2023-07-15', 1, 'Sycamore Street', 'Medium', 8.7, 'frontyard', 2.8, 'Accepted'),
    (17, '2023-07-25', 4, 'Magnolia Avenue', 'Large', 11.8, 'backyard', 5.1, 'Accepted'),
    (18, '2023-08-05', 7, 'Cedar Road', 'Small', 7.5, 'frontyard', 1.2, 'Accepted'),
    (19, '2023-08-15', 1, 'Palm Street', 'Large', 12.3, 'backyard', 4.3, 'Accepted'),
    (20, '2023-09-01', 1, 'Redwood Drive', 'Medium', 9.1, 'frontyard', 3.9, 'Accepted'),
    (21, '2023-09-10', 3, 'Poplar Lane', 'Small', 6.5, 'backyard', 1.9, 'Pending'),
    (22, '2023-09-20', 3, 'Fir Avenue', 'Large', 10.9, 'frontyard', 5.4, 'Accepted'),
    (23, '2023-10-01', 5, 'Elm Road', 'Medium', 8.4, 'backyard', 2.5, 'Accepted'),
    (1, '2022-11-10', 3, 'Need somefjj', 'Large', 12.5, 'backyard', 5.3, 'Accepted'),
    (3, '2022-12-10', 5, 'brat', 'Small', 7.8, 'frontyard', 1.5, 'Accepted'),
    (4, '2022-12-01', 1, 'sestra', 'Medium', 8.6, 'backyard', 3.2, 'Accepted'),
    (5, '2023-08-12', 4, 'baka', 'Large', 11.4, 'backyard', 4.7, 'Accepted'),
    (6, '2023-06-02', 8, 'ides', 'Small', 6.9, 'frontyard', 1.8, 'Pending'),
    (7, '2023-06-04', 5, 'lolas', 'Large', 10.1, 'frontyard', 6.5, 'Accepted'),
    (8, '2023-03-12', 6, 'samo ne tu', 'Medium', 9.8, 'frontyard', 2.1, 'Accepted');
            
 select * from QuoteRequest;           

CREATE TABLE QuoteResponse
(
    ResponseID INT AUTO_INCREMENT PRIMARY KEY,
    RequestID  INT,
    ClientID INT,
    ResponseDate DATE,
    Price      DOUBLE,
    WorkPeriodFrom DATE,
    WorkPeriodTo DATE,     
    Note       VARCHAR(200),
     UNIQUE (ResponseID),
    FOREIGN KEY (RequestID) REFERENCES QuoteRequest (RequestID),
	FOREIGN KEY (ClientID) REFERENCES QuoteRequest (ClientID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into QuoteResponse (RequestID, ClientID, ResponseDate, Price, WorkPeriodFrom, WorkPeriodTo, Note) 
values(1, 1, '2022-05-12', 1500.0, '2022-05-12', '2023-09-14', 'Need clean area to work'),
	(2, 2, '2023-09-14', 1200.0, '2023-09-14', '2023-07-18', 'can start soon'),
    (3, 3, '2023-07-18', 1352.0, '2023-07-18', '2023-01-31', 'Note3'),
	(4, 4, '2023-01-31', 1133.0, '2023-01-31', '2023-08-05', 'Note4'),
	(5, 5, '2023-08-05', 1088.0, '2023-08-05', '2022-12-25', 'Note5'),
	(6, 6, '2022-12-25', 1200.0, '2022-12-25', '2022-11-20', 'Note6'),
	(7, 7, '2022-11-20', 1300.0, '2022-11-20', '2023-06-12', 'Note7'),
	(8, 8, '2023-06-12', 1287.0, '2023-06-12', '2023-03-20', 'Note8'),	 
	(9, 9, '2023-03-20', 1508.0, '2023-03-20', '2023-04-12', 'will take 5 hours to finish work'),
(10, 10, '2023-05-01', 1890.0, '2023-05-10', '2023-05-25', 'ready to start as soon as possible'),
(11, 11, '2023-06-15', 1200.0, '2023-07-01', '2023-07-15', 'can complete within two weeks'),
(12, 12, '2023-08-01', 850.0, '2023-08-10', '2023-08-20', 'efficient and quality work assured'),
(13, 13, '2023-09-10', 2000.0, '2023-09-20', '2023-10-05', 'flexible work schedule'),
(14, 14, '2023-10-20', 1800.0, '2023-11-01', '2023-11-15', 'special discount available for this project');
    
select * from QuoteResponse;

CREATE TABLE ClientRespondToQuoteResponse (
    ClientResponseID INT AUTO_INCREMENT PRIMARY KEY,
    ContractorID INT,
    ResponseID INT,
    ResponseDate DATE,
    Status ENUM('Accepted', 'Rejected', 'Pending', 'RequestRevision'),
    Note VARCHAR(255),
     UNIQUE (ClientResponseID),
    -- Other response-related fields
    FOREIGN KEY (ContractorID) REFERENCES Contractor(ContractorID),
    FOREIGN KEY (ResponseID) REFERENCES QuoteResponse(ResponseID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO ClientRespondToQuoteResponse (ContractorID, ResponseID, ResponseDate, Status, Note)
VALUES (1, 1, '2023-09-09', 'Accepted', 'Thanks'), 
       (1, 1, '2023-09-19', 'Pending', 'Waiting'),
       (1, 2, '2023-01-01', 'Rejected', 'High price'),
       (1, 3, '2023-05-15', 'Pending', 'Considering options'),
       (1, 4, '2023-02-28', 'Accepted', 'Great offer!'),
       (1, 5, '2023-10-10', 'Rejected', 'Not suitable'),
       (1, 6, '2023-08-20', 'Pending', 'Need more details'),
       (1, 7, '2023-07-01', 'RequestRevision', 'Please revise the terms'),
       (1, 8, '2023-04-05', 'Accepted', 'Ready to proceed'),
       (1, 9, '2023-03-10', 'Rejected', 'Looking for a better deal'),
       (1, 10, '2023-06-15', 'Pending', 'Discussing internally'),
       (1, 11, '2023-09-30', 'Accepted', 'Excited to start the project'),
       (1, 12, '2023-07-25', 'Rejected', 'Not within budget'),
       (1, 13, '2023-04-18', 'Pending', 'Waiting for additional information'),
       (1, 14, '2023-11-05', 'Accepted', 'Agreed to terms');

select * from ClientRespondToQuoteResponse;

CREATE TABLE ContractorRespondToClientResponse (
    ContractorResponseID INT AUTO_INCREMENT PRIMARY KEY,
    ContractorID INT,
    ClientResponseID INT,
    ResponseDate DATE,
    Status ENUM('Accepted', 'Rejected', 'Pending', 'RequestRevision'),
    Note VARCHAR(255),
    ModifiedPrice DOUBLE,
    ModifiedWorkPeriodFrom DATE,
    ModifiedWorkPeriodTo DATE,  
    FOREIGN KEY (ContractorID) REFERENCES Contractor(ContractorID),
    FOREIGN KEY (ClientResponseID) REFERENCES ClientRespondToQuoteResponse(ClientResponseID)
);

INSERT INTO ContractorRespondToClientResponse ( ContractorID, ClientResponseID, Status, ResponseDate, Note,ModifiedPrice,ModifiedWorkPeriodFrom,ModifiedWorkPeriodTo) 
VALUES (1, 2, 'Accepted', '2022-12-29','need some extra time',1000.0,'2023-01-01', '2023-01-02'),
  (1, 3, 'Accepted', '2023-09-09', 'Thanks for the opportunity', 988.50, '2023-09-10', '2023-09-20'),
  (1, 4, 'Rejected', '2023-09-15', 'Unable to commit to the timeline', 1032.0, NULL, NULL),
  (1, 5, 'Pending', '2023-09-20', 'Considering other commitments', 1132.0, NULL, NULL),
  (1, 6, 'Accepted', '2023-09-25', 'Excited to work on the project', 1050.0, '2023-10-01', '2023-10-15'),
  (1, 7, 'Rejected', '2023-09-30', 'Unable to proceed at this time', 980.0, NULL, NULL),
  (1, 8, 'Pending', '2023-10-05', 'Awaiting further project details', 890.0, NULL, NULL),
  (1, 9, 'RequestRevision', '2023-10-10', 'Proposing changes to the terms', 909.0, NULL, NULL),
  (1, 10, 'Accepted', '2023-10-15', 'Agreed to revised terms', 948.0, '2023-10-20', '2023-11-05'),
  (1, 11, 'Rejected', '2023-10-20', 'Looking for a higher budget', 980.0, NULL, NULL),
  (1, 12, 'Pending', '2023-10-25', 'Evaluating workload', 990.0, NULL, NULL);

select * from ContractorRespondToClientResponse;

CREATE TABLE OrderOfWork (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    RequestID INT,
    ClientID INT,   
    StartDate DATE,
    EndDate DATE,
    Status ENUM('Started', 'In Progress', 'Pending', 'Completed'),    
    NumberOfTreesCut INT,
    DateOfCut DATE,
    ContractorID VARCHAR(50),
    FOREIGN KEY (ClientID) REFERENCES Client(ClientID),
    FOREIGN KEY (RequestID) REFERENCES QuoteRequest(RequestID)   
);

INSERT INTO OrderOfWork (RequestID, ClientID, StartDate , EndDate, Status, NumberOfTreesCut, DateOfCut, ContractorID)
values (1, 1, '2022-11-10', '2022-11-15', 'Started', 10, '2022-11-12', '1'),
       (2, 2, '2022-11-12', '2022-11-20', 'In Progress', 5, '2022-11-18', '1'),
       (3, 3, '2022-12-10', '2022-12-15', 'Completed', 9, '2022-12-12', '1'),
       (4, 4, '2022-12-01', '2022-12-10', 'Pending', 9, '2022-12-05', '1'),
       (5, 5, '2023-08-12', '2023-08-20', 'Started', 6, '2023-08-15', '1'),
       (6, 6, '2023-06-02', '2023-06-10', 'In Progress', 9, '2023-06-05', '1'),
       (7, 7, '2023-06-04', '2023-06-12', 'Completed', 10, '2023-06-08', '1'),
       (8, 8, '2023-03-12', '2023-03-20', 'Completed', 10, '2023-03-15', '1'),
       (9, 9, '2023-04-05', '2023-04-15', 'Pending', 8, '2023-04-15', '1'),
       (10, 10, '2023-04-12', '2023-04-25', 'Completed', 5, '2023-4-25', '1'),
       (11, 11, '2023-05-01', '2023-05-10', 'Pending', 5, '2023-05-01', '1'),
       (12, 11, '2023-05-18', '2023-06-02', 'Completed', 3, '2023-06-02', '1'),
       (13, 12, '2023-06-10', '2023-06-20', 'Pending', 2, '2023-06-20', '1');
       
select * from OrderOfwork;       
       
# Stores bills generated based on completed work. It links to the order of work and indicates the status of the bill (e.g., pending, in dispute)

CREATE TABLE Bills (
    BillID INT AUTO_INCREMENT PRIMARY KEY,
    Amount DECIMAL(10, 2),
    GeneratedDate DATE,
    BillStatus ENUM('Issued', 'Pending', 'Paid', 'Cancelled'),
    PaymentStatus ENUM('Unpaid', 'Paid', 'Processing', 'Overdue', 'Failed'),
    OrderID INT, 
    ContractorID INT, 
    FOREIGN KEY (OrderID) REFERENCES OrderOfWork(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ContractorID) REFERENCES Contractor(ContractorID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO Bills (  Amount, GeneratedDate, BillStatus, PaymentStatus, OrderID,ContractorID) 
VALUES ( 1089, '2022-12-29','Issued', 'Unpaid' ,1,1), 	  
    ( 1104, '2022-12-29', 'Issued', 'Overdue', 2, 1),
    ( 1190, '2023-01-15', 'Pending', 'Overdue', 3, 1),
    ( 1200, '2023-01-20', 'Issued', 'Paid', 4, 1),
    ( 985, '2023-02-05', 'Pending', 'Unpaid', 5, 1),
    ( 899, '2023-02-10', 'Issued', 'Paid', 6, 1),
    ( 955, '2023-03-01', 'Issued', 'Unpaid', 7, 1),
    ( 1125, '2023-03-15', 'Pending', 'Unpaid', 8, 1),
    ( 1005, '2023-04-02', 'Issued', 'Paid', 9, 1),
    ( 1098, '2023-05-15', 'Issued', 'Unpaid', 10, 1),
    ( 1082, '2023-06-01', 'Issued', 'Paid', 11, 1),
    ( 1105, '2023-07-10', 'Issued', 'Unpaid', 12, 1),
    ( 1129, '2023-08-05', 'Issued', 'Paid', 13, 1);
    
select * from Bills;       
       
CREATE TABLE ResponseToBill (
    ResponseToBillID INT AUTO_INCREMENT PRIMARY KEY,
    BillID INT,
    ClientID INT,
    ResponseDate DATE,
    Note VARCHAR(50),
    PaymentStatus ENUM('Paid', 'Unpaid'),
    FOREIGN KEY (BillID) REFERENCES Bills(BillID) ON DELETE CASCADE,
    FOREIGN KEY (ClientID) REFERENCES Client(ClientID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
ALTER TABLE ResponseToBill AUTO_INCREMENT = 1;

INSERT INTO ResponseToBill ( BillID, ClientID, ResponseDate, Note, PaymentStatus) 
VALUES (1,1,'2023-04-29','installment 2', 'Unpaid'),
	   ( 2, 2,'2023-06-19','will pay soon', 'Paid'), 
	   ( 3, 3,'2023-05-13','will pay soon', 'Unpaid'), 
	   ( 4, 4,'2023-06-18','will pay soon', 'Paid'), 
	   ( 5, 5,'2023-07-17','pending payment', 'Unpaid'), 
	   ( 6, 6,'2023-08-29','paid in advance', 'Paid'), 
	   ( 7, 7,'2023-09-12','payment due', 'Unpaid'), 
	   ( 8, 8,'2023-10-24','completed payment', 'Paid'), 
	   ( 9, 9,'2023-11-09','late payment', 'Unpaid'), 
	   (10,10,'2023-12-29','early payment', 'Paid'), 
	   (11,11,'2023-01-29','partial payment', 'Unpaid'), 
	   (12,12,'2023-06-02','full payment', 'Paid'), 
	   (13,13,'2023-03-29','installment 1', 'Unpaid'); 

select * from ResponseToBill;

# Stores login credentials for administrators with access to the system
CREATE TABLE Administrator
(
    AdminID  INT AUTO_INCREMENT PRIMARY KEY,
    Email  VARCHAR(50) ,
    Username VARCHAR(50),
    Password VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
insert into Administrator(Username, Password, Email)
                values ( 'Cummis', 'DC1000', 'Cu@gmail.com'),
                ( 'Laws', 'ML1234', 'La@gmail.com');              

select * from administrator;

# Stores records of revenue generated for specific time periods
CREATE TABLE Revenue
(
    RevenueID INT AUTO_INCREMENT PRIMARY KEY,
    Date      DATE,
    Amount    FLOAT
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- ROLES - (David Smith, clients, or Admin root)





















-- drop database project1;

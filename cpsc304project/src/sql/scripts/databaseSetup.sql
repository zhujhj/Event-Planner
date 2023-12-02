CREATE TABLE Guest (
                       guest_name VARCHAR2(20),
                       email VARCHAR2(20),
                       ticket_number INTEGER,
                       phone_number INTEGER,
                       PRIMARY KEY (guest_name, ticket_number)
);

CREATE TABLE Staff (
                       staff_id INTEGER,
                       staff_role VARCHAR2(255), -- Assuming VARCHAR2 for role
                       email VARCHAR2(255),
                       phone_number INTEGER,
                       name VARCHAR2(255), -- Assuming VARCHAR2 for name
                       PRIMARY KEY (staff_id, name)
);

CREATE TABLE Sponsor (
                         sponsor_name VARCHAR2(255),
                         amount INTEGER,
                         request VARCHAR2(255),
                         phone_number INTEGER,
                         email VARCHAR2(255),
                         PRIMARY KEY (sponsor_name, email)
);

CREATE TABLE Performer (
                           performer_name VARCHAR2(255) PRIMARY KEY, -- Assuming VARCHAR2 for performer_name
                           agent VARCHAR2(255),
                           phone_number INTEGER,
                           email VARCHAR2(255)
);

CREATE TABLE Event (
                       event_time TIMESTAMP, -- Assuming TIMESTAMP for event_time
                       event_id INTEGER PRIMARY KEY,
                       event_name VARCHAR2(255),
                       event_description VARCHAR2(255)
);

CREATE TABLE Department (
                            dept_name VARCHAR2(255),
                            dept_description VARCHAR2(255),
                            event_id INTEGER,
                            PRIMARY KEY (dept_name, event_id),
                            FOREIGN KEY (event_id) REFERENCES Event(event_id)
);

CREATE TABLE Venue (
                       venue_name VARCHAR2(255),
                       venue_address VARCHAR2(255),
                       venue_capacity INTEGER,
                       event_id INTEGER,
                       PRIMARY KEY (venue_name, venue_address, event_id),
                       FOREIGN KEY (event_id) REFERENCES EVENT
                           ON DELETE CASCADE
);

CREATE TABLE Vendor (
                        vendor_name VARCHAR2(255),
                        vendor_location VARCHAR2(255),
                        vendor_hours INTEGER,
                        PRIMARY KEY (vendor_name)
);

CREATE TABLE Food_Vendor (
                             vendor_name VARCHAR2(255),
                             alcohol_number INTEGER,
                             style VARCHAR2(255),
                             PRIMARY KEY (vendor_name),
                             FOREIGN KEY (vendor_name) REFERENCES Vendor(vendor_name)
);

CREATE TABLE Merchandise_Vendor (
                                    vendor_name VARCHAR2(255),
                                    brands_carried VARCHAR2(255),
                                    PRIMARY KEY (vendor_name),
                                    FOREIGN KEY (vendor_name) REFERENCES Vendor(vendor_name)
);

-- Your INSERT statements remain the same
INSERT INTO Guest (guest_name, email, ticket_number, phone_number)
VALUES ('Leo Wang', 'leo.wang@gmail.com', 1001, 123456789);
INSERT INTO Guest (guest_name, email, ticket_number, phone_number)
VALUES ('Michael Cui', 'michael.cui@gmail.com', 1002, 123456789);
INSERT INTO Guest (guest_name, email, ticket_number, phone_number)
VALUES('Kaiser Ninomiya', 'kaiser.ninomiya@gmail.com', 1003, 123456789);
INSERT INTO Guest (guest_name, email, ticket_number, phone_number)
VALUES('Jason Zhu', 'jason.zhu@gmail.com', 1004, 123456789);
INSERT INTO Guest (guest_name, email, ticket_number, phone_number)
VALUES('Stephen Qiao', 'stephen.qiao@gmail.com', 1005, 123456789);

INSERT INTO Staff (staff_id, name, email, phone_number, staff_role)
VALUES (1, 'Leo Wang', 'leo.wang@gmail.com', 123456789, 'Manager');
INSERT INTO Staff (staff_id, name, email, phone_number, staff_role)
VALUES(2, 'Michael Cui', 'michael.cui@gmail.com', 123456789, 'Supervisor');
INSERT INTO Staff (staff_id, name, email, phone_number, staff_role)
VALUES(3, 'Kaiser Ninomiya', 'kaiser.ninomiya@gmail.com', 123456789, 'Technician');
INSERT INTO Staff (staff_id, name, email, phone_number, staff_role)
VALUES(4, 'Jason Zhu', 'jason.zhu@gmail.com', 123456789, 'Coordinator');
INSERT INTO Staff (staff_id, name, email, phone_number, staff_role)
VALUES(5, 'Stephen Qiao', 'stephen.qiao@gmail.com', 123456789, 'Support Staff');

INSERT INTO Sponsor (sponsor_name, amount, request, phone_number, email)
VALUES('Facebook', 10000, 'Display advertisement at Main Stage', 123456789, 'facebook@gmail.com');
INSERT INTO Sponsor (sponsor_name, amount, request, phone_number, email)
VALUES('Microsoft', 5000, 'Advertisement', 123456789, 'microsoft@gmail.com');
INSERT INTO Sponsor (sponsor_name, amount, request, phone_number, email)
VALUES('Nike', 7000, 'Pop-up shop', 123456789, 'nike@gmail.com');
INSERT INTO Sponsor (sponsor_name, amount, request, phone_number, email)
VALUES('Apple', 12000, 'Video sponsor', 123456789, 'apple@gmail.com');
INSERT INTO Sponsor (sponsor_name, amount, request, phone_number, email)
VALUES('Alibaba', 8000, 'Advertisement', 123456789, 'alibaba@gmail.com');

INSERT INTO Performer (performer_name, agent, phone_number, email)
VALUES('NBA Youngboy', 'Rich Paul', 123456789, 'youngboyneverbrokeagain@gmail.com');
INSERT INTO Performer (performer_name, agent, phone_number, email)
VALUES('Drake', 'Chubbs', 123456789, 'aubreygraham@gmail.com');
INSERT INTO Performer (performer_name, agent, phone_number, email)
VALUES('Travis Scott', 'Kylie Jenner', 123456789, 'tscott@gmail.com');
INSERT INTO Performer (performer_name, agent, phone_number, email)
VALUES('Kanye West', 'Pete Davidson', 123456789, 'ye@gmail.com');
INSERT INTO Performer (performer_name, agent, phone_number, email)
VALUES('Taylor Swift', 'Katy Perry', 123456789, 'taylorswift@gmail.com');

INSERT INTO Event (event_time, event_id, event_name, event_description)
VALUES('2023-11-01 08:00:00', 1, 'NBA Youngboy Concert', 'Concert for rapper NBA Youngboy ');
INSERT INTO Event (event_time, event_id, event_name, event_description)
VALUES('2023-11-02 18:00:00', 2, 'NBA game', 'Golden State Warriors vs. Toronto Raptors');
INSERT INTO Event (event_time, event_id, event_name, event_description)
VALUES('2023-11-03 20:00:00', 3, 'NHL game', 'Toronto Maple Leafs vs. Calgary Flames');
INSERT INTO Event (event_time, event_id, event_name, event_description)
VALUES('2023-11-04 14:00:00', 4, 'Drake Concert', 'live performance by the 6ix god');
INSERT INTO Event (event_time, event_id, event_name, event_description)
VALUES('2023-11-05 12:00:00', 5, 'WNBA game', 'New York Liberty vs. Las Vegas Aces');

INSERT INTO Department (dept_name, dept_description, event_id)
VALUES('Tech', 'Controls event technology', 1);
INSERT INTO Department (dept_name, dept_description, event_id)
VALUES('Food', 'Manages food sellers at event', 2);
INSERT INTO Department (dept_name, dept_description, event_id)
VALUES('Lights', 'Controls Lighting at event', 3);
INSERT INTO Department (dept_name, dept_description, event_id)
VALUES('Audio', 'Controls event audio', 4);
INSERT INTO Department (dept_name, dept_description, event_id)
VALUES('Bookings', 'Manages bookings', 5);

INSERT INTO Venue (venue_name, venue_address, venue_capacity, event_id)
VALUES('Apple', '123 Granville Street', 500, 1);
INSERT INTO Venue (venue_name, venue_address, venue_capacity, event_id)
VALUES('McDonald’s', '456 Burrard Avenue', 800, 2);
INSERT INTO Venue (venue_name, venue_address, venue_capacity, event_id)
VALUES('Nike', '789 University Blvd', 600, 3);
INSERT INTO Venue (venue_name, venue_address, venue_capacity, event_id)
VALUES('Auto Group', '101 Student Union Blvd', 1000, 4);
INSERT INTO Venue (venue_name, venue_address, venue_capacity, event_id)
VALUES('Merch Store', '202 Lougheed Circle', 1500, 5);

INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES('VenderA', 'North Side', 8);
INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES('VenderB', 'East Side', 6);
INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES('VenderC', 'West Side', 7);
INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES('VenderD', 'South Side', 5);
INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES('VenderE', 'Central Area', 10);
INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES('VenderF', 'Central Area', 10);
INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES('VenderG', 'Central Area', 10);
INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES('VenderH', 'Central Area', 10);
INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES('VenderI', 'Central Area', 10);
INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES('VenderJ', 'Central Area', 10);

INSERT INTO Food_Vendor (vendor_name, alcohol_number, style)
VALUES('VenderA', 1001, 'Italian');
INSERT INTO Food_Vendor (vendor_name, alcohol_number, style)
VALUES('VenderB', 1002, 'Chinese');
INSERT INTO Food_Vendor (vendor_name, alcohol_number, style)
VALUES('VenderC', 1003, 'Mexican');
INSERT INTO Food_Vendor (vendor_name, alcohol_number, style)
VALUES('VenderD', 1004, 'Indian');
INSERT INTO Food_Vendor (vendor_name, alcohol_number, style)
VALUES('VenderE', 1005, 'French');

INSERT INTO Merchandise_Vendor (vendor_name, brands_carried)
VALUES('VenderF', 'Nike');
INSERT INTO Merchandise_Vendor (vendor_name, brands_carried)
VALUES('VenderG', 'Lululemon');
INSERT INTO Merchandise_Vendor (vendor_name, brands_carried)
VALUES('VenderH', 'Arcteryx');
INSERT INTO Merchandise_Vendor (vendor_name, brands_carried)
VALUES('VenderI', 'Adidas');
INSERT INTO Merchandise_Vendor (vendor_name, brands_carried)
VALUES('VenderJ', 'Prada');
-- Your INSERT statements remain the same

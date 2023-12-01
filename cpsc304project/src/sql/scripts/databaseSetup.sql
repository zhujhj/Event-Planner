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
                       capacity INTEGER,
                       event_id INTEGER,
                       PRIMARY KEY (venue_name, venue_address, event_id),
                       FOREIGN KEY (event_id) REFERENCES Event(event_id)
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
VALUES
    ('Leo Wang', 'leo.wang@gmail.com', 1001, 123456789),
    ('Michael Cui', 'michael.cui@gmail.com', 1002, 123456789),
    ('Kaiser Ninomiya', 'kaiser.ninomiya@gmail.com', 1003, 123456789),
    ('Jason Zhu', 'jason.zhu@gmail.com', 1004, 123456789),
    ('Stephen Qiao', 'stephen.qiao@gmail.com', 1005, 123456789);

INSERT INTO Staff (staff_id, name, email, phone_number, staff_role)
VALUES
    (1, 'Leo Wang', 'leo.wang@gmail.com', 123456789, 'Manager'),
    (2, 'Michael Cui', 'michael.cui@gmail.com', 123456789, 'Supervisor'),
    (3, 'Kaiser Ninomiya', 'kaiser.ninomiya@gmail.com', 123456789, 'Technician'),
    (4, 'Jason Zhu', 'jason.zhu@gmail.com', 123456789, 'Coordinator'),
    (5, 'Stephen Qiao', 'stephen.qiao@gmail.com', 123456789, 'Support Staff');

INSERT INTO Sponsor (sponsor_name, amount, request, phone_number, email)
VALUES
    ('Facebook', 10000, 'Display advertisement at Main Stage', 123456789, 'facebook@gmail.com'),
    ('Microsoft', 5000, 'Advertisement', 123456789, 'microsoft@gmail.com'),
    ('Nike', 7000, 'Pop-up shop', 123456789, 'nike@gmail.com'),
    ('Apple', 12000, 'Video sponsor', 123456789, 'apple@gmail.com'),
    ('Alibaba', 8000, 'Advertisement', 123456789, 'alibaba@gmail.com');

INSERT INTO Performer (performer_name, agent, phone_number, email)
VALUES
    ('NBA Youngboy', 'Rich Paul', 123456789, 'youngboyneverbrokeagain@gmail.com'),
    ('Drake', 'Chubbs', 123456789, 'aubreygraham@gmail.com'),
    ('Travis Scott', 'Kylie Jenner', 123456789, 'tscott@gmail.com'),
    ('Kanye West', 'Pete Davidson', 123456789, 'ye@gmail.com'),
    ('Taylor Swift', 'Katy Perry', 123456789, 'taylorswift@gmail.com');

INSERT INTO Event (event_time, event_id, event_name, event_description)
VALUES
    ('2023-11-01 08:00:00', 1, 'NBA Youngboy Concert', 'Concert for rapper NBA Youngboy '),
    ('2023-11-02 18:00:00', 2, 'NBA game', 'Golden State Warriors vs. Toronto Raptors'),
    ('2023-11-03 20:00:00', 3, 'NHL game', 'Toronto Maple Leafs vs. Calgary Flames'),
    ('2023-11-04 14:00:00', 4, 'Drake Concert', 'live performance by the 6ix god'),
    ('2023-11-05 12:00:00', 5, 'WNBA game', 'New York Liberty vs. Las Vegas Aces');

INSERT INTO Department (dept_name, dept_description, event_id)
VALUES
    ('Tech', 'Controls event technology', 1),
    ('Food', 'Manages food sellers at event', 2),
    ('Lights', 'Controls Lighting at event', 3),
    ('Audio', 'Controls event audio', 4),
    (‘Bookings’, 'Manages bookings', 5);


INSERT INTO Venue (venue_name, venue_address, capacity, event_id)
VALUES
    ('Apple', '123 Granville Street', 500, 1),
    ('McDonald’s', '456 Burrard Avenue', 800, 2),
    ('Nike', '789 University Blvd', 600, 3),
    ('Auto Group', '101 Student Union Blvd', 1000, 4),
    ('Merch Store', '202 Lougheed Circle', 1500, 5);

INSERT INTO Vendor (vendor_name, vendor_location, vendor_hours)
VALUES
    ('VenderA', 'North Side', 8),
    ('VenderB', 'East Side', 6),
    ('VenderC', 'West Side', 7),
    ('VenderD', 'South Side', 5),
    ('VenderE', 'Central Area', 10);
    ('VenderF', 'Central Area', 10);
    ('VenderG', 'Central Area', 10);
    ('VenderH', 'Central Area', 10);
    ('VenderI', 'Central Area', 10);
    ('VenderJ', 'Central Area', 10);

INSERT INTO Food_Vendor (vendor_name, alcohol_number, style)
VALUES
    ('VenderA', 1001, 'Italian'),
    ('VenderB', 1002, 'Chinese'),
    ('VenderC', 1003, 'Mexican'),
    ('VenderD', 1004, 'Indian'),
    ('VenderE', 1005, 'French');

INSERT INTO Merchandise_Vendor (vendor_name, brands_carried)
VALUES
    ('VenderF', 'Nike'),
    ('VenderG', 'Lululemon'),
    ('VenderH', 'Arcteryx'),
    ('VenderI', 'Adidas'),
    ('VenderJ', 'Prada');

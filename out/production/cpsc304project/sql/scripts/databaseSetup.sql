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

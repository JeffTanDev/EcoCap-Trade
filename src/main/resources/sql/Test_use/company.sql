create database test;
use test;
CREATE TABLE company (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    c_name VARCHAR(255) NOT NULL,
    c_location VARCHAR(255),
    c_type VARCHAR(255),
    emission_quota DECIMAL(10, 2),
    UNIQUE KEY unique_name (c_name)
);
INSERT INTO company (c_name, c_location, c_type, emission_quota) VALUES
('TechCorp', 'San Francisco, CA', 'Technology', 1500.50),
('GreenEnergy', 'Austin, TX', 'Energy', 2000.00),
('AutoMotiveX', 'Detroit, MI', 'Automobile', 3500.75),
('Foodies', 'New York, NY', 'Food & Beverage', 1200.00),
('EcoTools', 'Los Angeles, CA', 'Manufacturing', 1800.30),
('AquaLife', 'Miami, FL', 'Aquaculture', 2500.00),
('SolarGen', 'Phoenix, AZ', 'Renewable Energy', 3000.00),
('AgriWorks', 'Dallas, TX', 'Agriculture', 900.50),
('BioPharma', 'Boston, MA', 'Pharmaceutical', 2500.00),
('NextGenAuto', 'Chicago, IL', 'Automobile', 2750.00);


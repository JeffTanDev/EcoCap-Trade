CREATE TABLE company_user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50),
    password VARCHAR(50),
    direct_e_quota DECIMAL(10, 2),
    indirect_ee_quota DECIMAL(10, 2),
    indirect_e_quota DECIMAL(10, 2),
    c_name VARCHAR(100),
    c_location VARCHAR(100),
    c_registration VARCHAR(50),
    c_type VARCHAR(50),
    link_man VARCHAR(50),
    email VARCHAR(100)
);


INSERT INTO company_user (user_name, password, direct_e_quota, indirect_ee_quota, indirect_e_quota, c_name, c_location, c_registration, c_type, link_man, email)
VALUES
('jdoe', 'pass123', 100.50, 200.75, 150.30, 'ABC Corp', 'New York, NY', 'ABC123', 'Tech', 'John Doe', 'jdoe@example.com'),
('asmith', 'password456', 300.00, 120.50, 170.80, 'XYZ Inc', 'San Francisco, CA', 'XYZ456', 'Finance', 'Alice Smith', 'asmith@example.com'),
('bwilliams', 'mypassword789', 250.75, 210.25, 130.60, 'LMN Ltd', 'Austin, TX', 'LMN789', 'Manufacturing', 'Bob Williams', 'bwilliams@example.com'),
('cjohnson', 'secret123', 400.20, 300.10, 290.50, 'OPQ Co', 'Chicago, IL', 'OPQ101', 'Healthcare', 'Carol Johnson', 'cjohnson@example.com'),
('dmiller', 'pass321', 150.00, 180.75, 160.40, 'RST Services', 'Seattle, WA', 'RST202', 'Consulting', 'Dan Miller', 'dmiller@example.com');
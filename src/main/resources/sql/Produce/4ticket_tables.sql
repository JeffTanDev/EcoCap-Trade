CREATE TABLE Ticket (
    Ticket_ID INT PRIMARY KEY AUTO_INCREMENT,
    Ticket_Start DATE,
    Ticket_Close DATE,
    Resolve VARCHAR(50),
    Admin_ID_DUO INT,
    Assist_Date DATE,
    FOREIGN KEY (Admin_ID_DUO) REFERENCES Adminemployee(Admin_ID)
);

-- 创建 Information 表
CREATE TABLE Information (
    Ticket_ID INT,
    Info_Summary TEXT,
    Info_Type VARCHAR(50),
    FOREIGN KEY (Ticket_ID) REFERENCES Ticket(Ticket_ID)
);

-- 创建 Bill 表
CREATE TABLE Bill (
    Ticket_ID INT,
    Bill_Summary TEXT,
    Bill_Type VARCHAR(50),
    FOREIGN KEY (Ticket_ID) REFERENCES Ticket(Ticket_ID)
);
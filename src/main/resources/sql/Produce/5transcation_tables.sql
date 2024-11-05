-- 创建 Transaction 表
CREATE TABLE Transaction (
    Trans_ID INT PRIMARY KEY AUTO_INCREMENT,
    Price DECIMAL(10, 2),
    Trans_Status VARCHAR(50),
    Payment_Method VARCHAR(50),
    User_ID INT,
    Date DATE,
    Ticket_ID_DUO INT,
    E_Type VARCHAR(50),
    FOREIGN KEY (User_ID) REFERENCES Company_User(UserID),
    FOREIGN KEY (Ticket_ID_DUO) REFERENCES Ticket(Ticket_ID)
);

-- 创建 Daily_Release 表
CREATE TABLE Daily_Release (
    Product_ID INT PRIMARY KEY AUTO_INCREMENT,
    Initial_Amount DECIMAL(10, 2),
    Available_Amount DECIMAL(10, 2)
);

-- 创建 Market_SellToClient 表
CREATE TABLE Market_SellToClient (
    Deal_ID INT PRIMARY KEY AUTO_INCREMENT,
    Product_ID INT,
    Amount DECIMAL(10, 2),
    FOREIGN KEY (Product_ID) REFERENCES Daily_Release(Product_ID)
);

-- 创建 Market_BuyFromClient 表
CREATE TABLE Market_BuyFromClient (
    Deal_ID INT PRIMARY KEY AUTO_INCREMENT,
    Product_ID INT,
    Amount DECIMAL(10, 2),
    FOREIGN KEY (Product_ID) REFERENCES Daily_Release(Product_ID)
);

-- 创建 Deal_Buy 表
CREATE TABLE Deal_Buy (
    Deal_ID INT,
    Trans_ID INT,
    User_ID INT,
    Date DATE,
    PRIMARY KEY (Deal_ID, Trans_ID, User_ID),
    FOREIGN KEY (Deal_ID) REFERENCES Market_BuyFromClient(Deal_ID),
    FOREIGN KEY (Trans_ID) REFERENCES Transaction(Trans_ID),
    FOREIGN KEY (User_ID) REFERENCES Company_User(UserID)
);

-- 创建 Deal_Sell 表
CREATE TABLE Deal_Sell (
    Deal_ID INT,
    Trans_ID INT,
    User_ID INT,
    Date DATE,
    PRIMARY KEY (Deal_ID, Trans_ID, User_ID),
    FOREIGN KEY (Deal_ID) REFERENCES Market_SellToClient(Deal_ID),
    FOREIGN KEY (Trans_ID) REFERENCES Transaction(Trans_ID),
    FOREIGN KEY (User_ID) REFERENCES Company_User(UserID)
);

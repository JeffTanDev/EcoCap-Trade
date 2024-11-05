-- 创建 Company_User 表
CREATE TABLE Company_User (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    UserName VARCHAR(50),
    Direct_E_Quota DECIMAL(10, 2),
    Indirect_EE_Quota DECIMAL(10, 2),
    Indirect_E_Quota DECIMAL(10, 2),
    C_Name VARCHAR(100),
    C_Location VARCHAR(100),
    C_Registration VARCHAR(50),
    C_Type VARCHAR(50),
    LinkMan VARCHAR(50),
    Email VARCHAR(100)
);

-- 创建 User_Emission 表
CREATE TABLE User_Emission (
    UserID INT,
    E_Type VARCHAR(50),
    PRIMARY KEY (UserID, E_Type),
    FOREIGN KEY (UserID) REFERENCES Company_User(UserID)
);

-- 创建 Direct_Emission 表
CREATE TABLE Direct_Emission (
    UserID INT,
    Used_Emission DECIMAL(10, 2),
    Remain_Emissions DECIMAL(10, 2),
    E_Type VARCHAR(50),
    PRIMARY KEY (UserID, E_Type),
    FOREIGN KEY (UserID, E_Type) REFERENCES User_Emission(UserID, E_Type)
);

-- 创建 Indirect_Energy_Emission 表
CREATE TABLE Indirect_Energy_Emission (
    UserID INT,
    Used_Emission DECIMAL(10, 2),
    Remain_Emissions DECIMAL(10, 2),
    E_Type VARCHAR(50),
    PRIMARY KEY (UserID, E_Type),
    FOREIGN KEY (UserID, E_Type) REFERENCES User_Emission(UserID, E_Type)
);

-- 创建 Indirect_Emission 表
CREATE TABLE Indirect_Emission (
    UserID INT,
    Used_Emission DECIMAL(10, 2),
    Remain_Emissions DECIMAL(10, 2),
    E_Type VARCHAR(50),
    PRIMARY KEY (UserID, E_Type),
    FOREIGN KEY (UserID, E_Type) REFERENCES User_Emission(UserID, E_Type)
);

-- 创建 Emission_History 表
CREATE TABLE Emission_History (
    History_ID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    E_Type VARCHAR(50),
    Date DATE,
    Direct_Emi DECIMAL(10, 2),
    Indirect_Emi DECIMAL(10, 2),
    Indirect_Ener_Emi DECIMAL(10, 2),
    FOREIGN KEY (UserID, E_Type) REFERENCES User_Emission(UserID, E_Type)
);

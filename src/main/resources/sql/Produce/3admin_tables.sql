-- 创建 Department 表
CREATE TABLE Department (
    Department_ID INT PRIMARY KEY AUTO_INCREMENT,
    Department_Name VARCHAR(100)
);

-- 创建 Employee_Info 表
CREATE TABLE Employee_Info (
    Employee_ID INT PRIMARY KEY AUTO_INCREMENT,
    Job_Title VARCHAR(50),
    Name VARCHAR(100),
    Salary DECIMAL(10, 2)
);

-- 创建 Admin 表
CREATE TABLE Adminemployee  (
    Admin_ID INT PRIMARY KEY AUTO_INCREMENT,
    Office_Hour VARCHAR(50),
    A_account VARCHAR(50),
    A_PW VARCHAR(50),
    A_RBAC VARCHAR(50),
    Department_ID_DUO INT,
    Employee_ID_DUO INT,
    FOREIGN KEY (Department_ID_DUO) REFERENCES Department(Department_ID),
    FOREIGN KEY (Employee_ID_DUO) REFERENCES Employee_Info(Employee_ID)
);

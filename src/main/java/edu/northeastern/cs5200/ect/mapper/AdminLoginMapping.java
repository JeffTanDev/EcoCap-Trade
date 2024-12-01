package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.AdminEmployee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminLoginMapping {
    @Select("SELECT * FROM adminemployee WHERE A_account=#{username} AND A_PW=#{password}")
    @Results({
        @Result(property = "adminId", column = "AdminID"),
        @Result(property = "officeHour", column = "Office_Hour"),
        @Result(property = "aAccount", column = "A_account"),
        @Result(property = "aPw", column = "A_PW"),
        @Result(property = "aRbac", column = "A_RBAC"),
        @Result(property = "departmentIdDuo", column = "DepartmentID_duo"),
        @Result(property = "employeeIdDuo", column = "EmployeeID_duo")
    })
    AdminEmployee auth(String username, String password);
} 
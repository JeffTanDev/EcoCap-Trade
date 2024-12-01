package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProfileMapper {
    @Select("SELECT * FROM Company_User WHERE UserName = #{username}")
    @Results({
        @Result(property = "userId", column = "UserID"),
        @Result(property = "userName", column = "UserName"),
        @Result(property = "directEQuota", column = "Direct_E_Quota"),
        @Result(property = "indirectEEQuota", column = "Indirect_EE_Quota"),
        @Result(property = "indirectEQuota", column = "Indirect_E_Quota"),
        @Result(property = "usedDE", column = "Used_DE"),
        @Result(property = "usedIEE", column = "Used_IEE"),
        @Result(property = "usedIE", column = "Used_IE"),
        @Result(property = "cName", column = "C_Name"),
        @Result(property = "cLocation", column = "C_Location"),
        @Result(property = "cRegistration", column = "C_Registration"),
        @Result(property = "cType", column = "C_Type"),
        @Result(property = "linkMan", column = "LinkMan"),
        @Result(property = "email", column = "Email")
    })
    CompanyUser findByUsername(String username);
} 
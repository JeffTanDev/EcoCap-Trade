package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

@Mapper
public interface LoginMapping {
    @Select("SELECT * FROM Company_User WHERE UserName=#{username} AND Password=#{password}")
    @Results({
        @Result(property = "userId", column = "UserID"),
        @Result(property = "userName", column = "UserName"),
        @Result(property = "password", column = "Password"),
        @Result(property = "directEQuota", column = "Direct_E_Quota"),
        @Result(property = "indirectEeQuota", column = "Indirect_EE_Quota"),
        @Result(property = "indirectEQuota", column = "Indirect_E_Quota"),
        @Result(property = "companyName", column = "C_Name"),
        @Result(property = "companyLocation", column = "C_Location"),
        @Result(property = "companyRegistration", column = "C_Registration"),
        @Result(property = "companyType", column = "C_Type"),
        @Result(property = "linkMan", column = "LinkMan"),
        @Result(property = "email", column = "Email")
    })
    CompanyUser auth(String username, String password);
}

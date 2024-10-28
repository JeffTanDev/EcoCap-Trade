package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapping {
    @Select("SELECT * FROM company_user WHERE user_name=#{username} AND password=#{password}")
    public CompanyUser auth(String username, String password);
}

package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM Company_User WHERE c_name=#{name}")
    CompanyUser get(String name);
}

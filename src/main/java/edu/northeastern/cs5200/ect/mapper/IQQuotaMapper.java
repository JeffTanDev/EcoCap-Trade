package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import org.apache.ibatis.annotations.*;
import java.util.Date;

@Mapper
public interface IQQuotaMapper {
    @Select("SELECT * FROM Company_User WHERE UserName = #{username}")
    CompanyUser getCompanyUser(String username);

    @Update("UPDATE Company_User SET Used_IE = Used_IE + #{amount} WHERE UserName = #{username}")
    boolean updateUsedQuota(@Param("username") String username, @Param("amount") Double amount);

    @Insert("INSERT INTO Transaction (Price, Trans_Status, Payment_Method, User_ID, Date, Ticket_ID_DUO, E_Type) " +
            "VALUES (#{amount}, 'Completed', 'Credit Card', #{userId}, #{date}, 0, 'Indirect_Emissions')")
    boolean insertTransaction(@Param("amount") Double amount, 
                            @Param("userId") Integer userId, 
                            @Param("date") Date date);
} 
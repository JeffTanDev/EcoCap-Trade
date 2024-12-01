package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.DailyRelease;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import java.util.Date;

@Mapper
public interface QuotaMapper {
    @Select("SELECT * FROM Company_User WHERE UserName = #{username}")
    @Results({
        @Result(property = "productId", column = "UserID"),
        @Result(property = "productName", column = "C_Name"),
        @Result(property = "initialAmount", column = "Direct_E_Quota"),
        @Result(property = "availableAmount", column = "Used_DE")
    })
    DailyRelease getDEQQuota(String username);

    @Update("UPDATE Company_User SET Used_DE = Used_DE + #{amount} WHERE UserName = #{username}")
    boolean updateUsedDEQ(@Param("username") String username, @Param("amount") Double amount);

    @Insert("INSERT INTO Transaction (Price, Trans_Status, Payment_Method, User_ID, Date, Ticket_ID_DUO, E_Type) " +
            "VALUES (#{amount}, 'Completed', 'Credit Card', #{userId}, #{date}, 0, 'Direct_Emissions')")
    boolean insertTransaction(@Param("amount") Double amount, 
                            @Param("userId") Integer userId, 
                            @Param("date") Date date);

    @Select("SELECT * FROM Daily_Release WHERE Product_Name = 'Direct_Emissions'")
    @Results({
        @Result(property = "productId", column = "Product_ID"),
        @Result(property = "productName", column = "Product_Name"),
        @Result(property = "initialAmount", column = "Initial_Amount"),
        @Result(property = "availableAmount", column = "Available_Amount")
    })
    DailyRelease getDirectEmissionsProduct();

    @Update("UPDATE Company_User SET Direct_E_Quota = Direct_E_Quota + #{quantity} WHERE UserName = #{username}")
    boolean updateDirectEQuota(@Param("username") String username, @Param("quantity") Double quantity);

    @Update("UPDATE Daily_Release SET Available_Amount = Available_Amount - #{quantity} WHERE Product_Name = 'Direct_Emissions'")
    boolean updateAvailableAmount(@Param("quantity") Double quantity);
} 
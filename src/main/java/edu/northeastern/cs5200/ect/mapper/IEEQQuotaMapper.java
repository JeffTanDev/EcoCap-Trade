package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.DailyRelease;
import org.apache.ibatis.annotations.*;
import java.util.Date;

@Mapper
public interface IEEQQuotaMapper {
    @Select("SELECT * FROM Company_User WHERE UserName = #{username}")
    CompanyUser getCompanyUser(String username);

    @Update("UPDATE Company_User SET Used_IEE = Used_IEE + #{amount} WHERE UserName = #{username}")
    boolean updateUsedQuota(@Param("username") String username, @Param("amount") Double amount);

    @Update("UPDATE Company_User SET Indirect_EE_Quota = Indirect_EE_Quota + #{quantity} WHERE UserName = #{username}")
    boolean updateIndirectEnergyEmissionsQuota(@Param("username") String username, @Param("quantity") Double quantity);

    @Insert("INSERT INTO Transaction (Price, Trans_Status, Payment_Method, User_ID, Date, Ticket_ID_DUO, E_Type) " +
            "VALUES (#{amount}, 'Completed', 'Credit Card', #{userId}, #{date}, 0, 'Indirect_Energy_Emissions')")
    boolean insertTransaction(@Param("amount") Double amount, 
                            @Param("userId") Integer userId, 
                            @Param("date") Date date);

    @Select("SELECT * FROM Daily_Release WHERE Product_Name = 'Indirect_Energy_Emissions'")
    DailyRelease getIndirectEnergyEmissionsProduct();

    @Select("SELECT * FROM Daily_Release WHERE Product_ID = #{productId}")
    DailyRelease getProductDetails(@Param("productId") Integer productId);

    @Update("UPDATE Daily_Release SET Available_Amount = Available_Amount - #{quantity} WHERE Product_Name = #{product}")
    boolean updateQuotaForPurchase(@Param("username") String username, @Param("product") String product, @Param("quantity") Double quantity);

} 
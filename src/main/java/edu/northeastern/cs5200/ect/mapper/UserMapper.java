package edu.northeastern.cs5200.ect.mapper;
import edu.northeastern.cs5200.ect.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 查询公司信息
     * @return
     */
    @Select("SELECT * FROM company WHERE c_name=#{name}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "cName", column = "c_name"),
//            @Result(property = "cLocation", column = "c_location"),
//            @Result(property = "cType", column = "c_type"),
//            @Result(property = "emissionQuota", column = "emission_quota")
//    })
    public User get(String name);
}

package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {
    @Select("SELECT * FROM Transaction WHERE User_ID = #{userId}")
    List<Transaction> findTransactionsByUserId(int userId);
} 
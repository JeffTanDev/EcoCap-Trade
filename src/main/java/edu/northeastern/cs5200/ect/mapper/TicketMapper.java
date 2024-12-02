package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketMapper {


    @Select("SELECT * FROM Ticket")
    List<Ticket> getAllTickets();


    @Select("SELECT * FROM Ticket WHERE Ticket_id = #{ticketId}")
    Ticket getTicketById(@Param("ticketId") Integer ticketId);


    @Insert("INSERT INTO Ticket (ticket_start, ticket_close, ticket_type, resolve, admin_id_duo, assist_date, user_id, trans_id) " +
            "VALUES (#{ticketStart}, #{ticketClose}, #{ticketType}, #{resolve}, #{adminIdDuo}, #{assistDate}, #{userId}, #{transId})")
    @Options(useGeneratedKeys = true, keyProperty = "ticketId")
    void insertTicket(Ticket ticket);


    @Update("UPDATE Ticket SET Resolve = #{resolve}, Admin_ID_DUO = #{adminIdDuo}, Assist_Date = NOW() WHERE Ticket_ID = #{ticketId}")
    int updateTicketStatus(@Param("ticketId") Integer ticketId, @Param("resolve") String resolve, @Param("adminIdDuo") Integer adminIdDuo);


    @Delete("DELETE FROM Ticket WHERE Ticket_ID = #{ticketId}")
    int deleteTicket(@Param("ticketId") Integer ticketId);


    @Select("SELECT * FROM Ticket WHERE Resolve = #{Resolved}")
    List<Ticket> getTicketsByResolveType(@Param("resolveType") String resolveType); //


    @Select("SELECT * FROM Ticket WHERE Ticket_type = #{ticketType}")
    List<Ticket> getTicketsByType(@Param("ticketType") String ticketType);

}
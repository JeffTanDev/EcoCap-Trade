package edu.northeastern.cs5200.ect.mapper;

import edu.northeastern.cs5200.ect.pojo.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketMapper {


    @Select("SELECT * FROM ticket")
    List<Ticket> getAllTickets();


    @Select("SELECT * FROM ticket WHERE ticket_id = #{ticketId}")
    Ticket getTicketById(@Param("ticketId") Integer ticketId);


    @Insert("INSERT INTO ticket (ticket_start, ticket_close, ticket_type, resolve, admin_id_duo, assist_date, user_id, trans_id) " +
            "VALUES (#{ticketStart}, #{ticketClose}, #{ticketType}, #{resolve}, #{adminIdDuo}, #{assistDate}, #{userId}, #{transId})")
    @Options(useGeneratedKeys = true, keyProperty = "ticketId")
    void insertTicket(Ticket ticket);


    @Update("UPDATE ticket SET resolve = #{resolve}, admin_id_duo = #{adminIdDuo}, assist_date = NOW() WHERE ticket_id = #{ticketId}")
    int updateTicketStatus(@Param("ticketId") Integer ticketId, @Param("resolve") String resolve, @Param("adminIdDuo") Integer adminIdDuo);


    @Delete("DELETE FROM ticket WHERE ticket_id = #{ticketId}")
    int deleteTicket(@Param("ticketId") Integer ticketId);


    @Select("SELECT * FROM ticket WHERE resolve = #{Resolved}")
    List<Ticket> getTicketsByResolveType(@Param("resolveType") String resolveType); //


    @Select("SELECT * FROM ticket WHERE ticket_type = #{ticketType}")
    List<Ticket> getTicketsByType(@Param("ticketType") String ticketType);

}
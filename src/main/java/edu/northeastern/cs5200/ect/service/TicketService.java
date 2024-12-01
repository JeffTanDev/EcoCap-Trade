package edu.northeastern.cs5200.ect.service;

import edu.northeastern.cs5200.ect.pojo.Ticket;
import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();   
    List<Ticket> getTicketsByType(String ticketType);           
    Ticket getTicketById(Integer id);        
    boolean updateTicketStatus(int ticketId, String resolve, Integer adminIdDuo);
    boolean deleteTicket(int ticketId);
}
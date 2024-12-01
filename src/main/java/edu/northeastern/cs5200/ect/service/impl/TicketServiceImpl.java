package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.mapper.TicketMapper;
import edu.northeastern.cs5200.ect.pojo.Ticket;
import edu.northeastern.cs5200.ect.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketMapper ticketMapper;

    // Constructor-based dependency injection
    public TicketServiceImpl(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    @Override
    public List<Ticket> getAllTickets() {
        // Fetch all tickets using the mapper
        return ticketMapper.getAllTickets();
    }

    @Override
    public Ticket getTicketById(Integer id) {
        // Fetch a ticket by ID using the mapper
        return ticketMapper.getTicketById(id);
    }

    @Override
    public boolean updateTicketStatus(int ticketId, String resolve, Integer adminIdDuo) {
        // Update ticket status using the mapper
        int rowsAffected = ticketMapper.updateTicketStatus(ticketId, resolve, adminIdDuo);
        return rowsAffected > 0; // Return true if the update was successful
    }

    @Override
    public boolean deleteTicket(int ticketId) {
        int rowsAffected = ticketMapper.deleteTicket(ticketId);
        return rowsAffected > 0; 
    }


    @Override
    public List<Ticket> getTicketsByType(String ticketType) {
        return ticketMapper.getTicketsByType(ticketType);
    }
}
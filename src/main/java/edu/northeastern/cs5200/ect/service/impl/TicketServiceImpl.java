package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.mapper.TicketMapper;
import edu.northeastern.cs5200.ect.pojo.Ticket;
import edu.northeastern.cs5200.ect.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

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
    @Transactional
    public boolean deleteTicket(Integer ticketId) {
        return ticketMapper.deleteTicket(ticketId) > 0;
    }

    @Override
    public List<Ticket> getTicketsByType(String ticketType) {
        return ticketMapper.getTicketsByType(ticketType);
    }
}
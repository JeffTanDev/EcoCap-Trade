package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.Ticket;
import edu.northeastern.cs5200.ect.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    // Constructor-based dependency injection
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Get all tickets
    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        System.out.println(tickets);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    // Get a ticket by ID
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update ticket status
    @PutMapping("/{ticketId}/status")
    public ResponseEntity<Void> updateTicketStatus(@PathVariable int ticketId, @RequestBody Ticket updatedTicket) {
        boolean isUpdated = ticketService.updateTicketStatus(ticketId, updatedTicket.getResolve(), updatedTicket.getAdminIdDuo());
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int ticketId) {
        boolean isDeleted = ticketService.deleteTicket(ticketId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/type")
    public ResponseEntity<List<Ticket>> filterTicketsByType(@RequestParam String ticketType) {
        System.out.println("call 了");
        List<Ticket> tickets = ticketService.getTicketsByType(ticketType);
        
        if (tickets != null && !tickets.isEmpty()) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

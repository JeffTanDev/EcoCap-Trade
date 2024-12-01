document.addEventListener('DOMContentLoaded', function() {
    // Load initial mock data
    loadMockTickets();

    // Add event listeners to filter buttons
    document.querySelectorAll('.filter-btn').forEach(button => {
        button.addEventListener('click', function() {
            // Remove active class from all buttons
            document.querySelectorAll('.filter-btn').forEach(btn => {
                btn.classList.remove('active');
            });
            // Add active class to clicked button
            this.classList.add('active');
            // Load filtered tickets
            loadMockTickets(this.dataset.type);
        });
    });

    // Search functionality
    const searchInput = document.querySelector('.search-box input');
    searchInput.addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        filterTickets(searchTerm);
    });
});

// Mock data for testing
const mockTickets = [
    {
        id: 1,
        type: 'info',
        company: 'Tech Corp',
        subject: 'Information Update Request',
        status: 'pending',
        date: '2024-03-15'
    },
    {
        id: 2,
        type: 'transition',
        company: 'Green Energy Ltd',
        subject: 'Carbon Credit Transfer',
        status: 'approved',
        date: '2024-03-14'
    },
    {
        id: 3,
        type: 'info',
        company: 'Eco Solutions',
        subject: 'Documentation Request',
        status: 'rejected',
        date: '2024-03-13'
    }
    // Add more mock tickets as needed
];

function loadMockTickets(type = 'all') {
    const ticketsList = document.getElementById('ticketsList');
    ticketsList.innerHTML = '';

    let filteredTickets = mockTickets;
    if (type !== 'all') {
        filteredTickets = mockTickets.filter(ticket => ticket.type === type);
    }

    filteredTickets.forEach(ticket => {
        const row = createTicketRow(ticket);
        ticketsList.appendChild(row);
    });
}

function createTicketRow(ticket) {
    const row = document.createElement('tr');
    row.innerHTML = `
        <td>#${ticket.id}</td>
        <td>${capitalizeFirstLetter(ticket.type)}</td>
        <td>${ticket.company}</td>
        <td>${ticket.subject}</td>
        <td><span class="status-badge status-${ticket.status}">${capitalizeFirstLetter(ticket.status)}</span></td>
        <td>${formatDate(ticket.date)}</td>
        <td>
            <button class="action-btn view-btn" onclick="viewTicket(${ticket.id})">View</button>
            <button class="action-btn approve-btn" onclick="approveTicket(${ticket.id})">Approve</button>
            <button class="action-btn reject-btn" onclick="rejectTicket(${ticket.id})">Reject</button>
        </td>
    `;
    return row;
}

function filterTickets(searchTerm) {
    const filteredTickets = mockTickets.filter(ticket => 
        ticket.company.toLowerCase().includes(searchTerm) ||
        ticket.subject.toLowerCase().includes(searchTerm) ||
        ticket.type.toLowerCase().includes(searchTerm)
    );

    const ticketsList = document.getElementById('ticketsList');
    ticketsList.innerHTML = '';
    filteredTickets.forEach(ticket => {
        const row = createTicketRow(ticket);
        ticketsList.appendChild(row);
    });
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function formatDate(dateString) {
    return new Date(dateString).toLocaleDateString();
}

// Ticket action handlers
function viewTicket(id) {
    alert(`Viewing ticket ${id}`);
    // Implement view ticket logic
}

function approveTicket(id) {
    alert(`Approving ticket ${id}`);
    // Implement approve ticket logic
}

function rejectTicket(id) {
    alert(`Rejecting ticket ${id}`);
    // Implement reject ticket logic
}

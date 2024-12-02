document.addEventListener('DOMContentLoaded', function() {
    fetchTickets();
    setupFilterButtons();
});

async function fetchTickets() {
    try {
        const response = await fetch('/api/tickets/all'); // Adjust endpoint as needed
        
        // Check if the request was successful
        if (!response.ok) {
            throw new Error(`Error: ${response.status} ${response.statusText}`);
        }
        const tickets = await response.json();

        displayTickets(tickets);
    } catch (error) {
        console.error('Failed to fetch tickets:', error);
        showError('Unable to load tickets. Please try again later.');
    }
}

function displayTickets(tickets) {
    const ticketsList = document.getElementById('ticketsList'); // Table body element
    ticketsList.innerHTML = ''; // Clear existing rows

    tickets.forEach(ticket => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>#${ticket.ticketId}</td>
            <td>${ticket.ticketType}</td>
            <td>${ticket.info}</td>
            <td>${new Date(ticket.ticketStart).toLocaleString()}</td>
            <td>
                <span class="status-badge status-${ticket.resolve ? ticket.resolve.toLowerCase() : 'pending'}">
                    ${ticket.resolve || 'PENDING'}
                </span>
            </td>
            <td>${ticket.userId}</td>
            <td>
                <button onclick="viewTicket(${ticket.ticketId})" class="action-btn view-btn">View</button>
                ${!ticket.resolve ? `
                    <button onclick="handleTicket(${ticket.ticketId}, 'APPROVED')" class="action-btn approve-btn">Approve</button>
                    <button onclick="handleTicket(${ticket.ticketId}, 'REJECTED')" class="action-btn reject-btn">Reject</button>
                ` : ''}
            </td>
        `;
        ticketsList.appendChild(row);
    });
}

function showTicketModal(ticket) {
    const content = document.getElementById('ticketModalContent');
    
    content.innerHTML = `
        <h2>Ticket Details</h2>
        <p><strong>ID:</strong> #${ticket.ticketId}</p>
        <p><strong>Type:</strong> ${ticket.ticketType}</p>
        <p><strong>Information:</strong> ${ticket.info}</p>
        <p><strong>Start Date:</strong> ${formatDate(ticket.ticketStart)}</p>
        <p><strong>Status:</strong> ${ticket.resolve || 'PENDING'}</p>
        <p><strong>User ID:</strong> ${ticket.userId}</p>
        ${ticket.transId ? `<p><strong>Transaction ID:</strong> ${ticket.transId}</p>` : ''}
        ${ticket.adminIdDuo ? `<p><strong>Admin ID:</strong> ${ticket.adminIdDuo}</p>` : ''}
        ${ticket.ticketClose ? `<p><strong>Close Date:</strong> ${formatDate(ticket.ticketClose)}</p>` : ''}
        ${ticket.resolve === 'Resolved' ? `
            <button onclick="deleteTicket(${ticket.ticketId})" class="action-btn delete-btn">Delete</button>
        ` : ''}
    `;
    
    // 使用Bootstrap的模态框方法
    $("#ticketModal").modal("show");
}

function filterTicketsByType(ticketType) {
    fetch(`/api/tickets/type?ticketType=${ticketType}`)
        .then(response => response.json())
        .then(data => {
            displayTickets(data);
        })
        .catch(error => {
            console.error('Error fetching filtered tickets:', error);
            showError('Unable to filter tickets. Please try again later.');
        });
}

function updateTicketList(tickets) {
    const ticketListElement = document.getElementById('ticketsList');
    ticketListElement.innerHTML = ''; 

    tickets.forEach(ticket => {
        const ticketItem = document.createElement('tr');
        ticketItem.innerHTML = `
            <td>#${ticket.ticketId}</td>
            <td>${ticket.ticketType}</td>
            <td>${ticket.info}</td>
            <td>${ticket.resolve}</td>
            <td>
                <button onclick="viewTicket(${ticket.ticketId})" class="action-btn view-btn">View</button>
                ${!ticket.resolve ? `
                    <button onclick="handleTicket(${ticket.ticketId}, 'APPROVED')" class="action-btn approve-btn">Approve</button>
                    <button onclick="handleTicket(${ticket.ticketId}, 'REJECTED')" class="action-btn reject-btn">Reject</button>
                ` : ''}
            </td>
        `;
        ticketListElement.appendChild(ticketItem);
    });
}


async function handleTicket(ticketId, status) {
    try {
        // Attempt backend update regardless of mock status
        const response = await fetch(`/api/tickets/${ticketId}/status`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ 
                resolve: status,
                adminIdDuo: 101, 
                assistDate: new Date().toISOString()
            })
        });

        if (!response.ok) throw new Error('Failed to update ticket');

        fetchTickets(); 

        // Update the local state for the mock ticket
        if (ticket?.isMock) {
            ticket.resolve = status;
            ticket.adminIdDuo = 101;
            ticket.ticketClose = new Date().toISOString();
        }

        fetchTickets(); // Refresh the list
    } catch (error) {
        console.error('Error:', error);
        showError('Unable to update ticket. Please try again later.');
    }
}

async function viewTicket(ticketId) {
    try {
        const response = await fetch(`/api/tickets/${ticketId}`); // Adjust endpoint as needed
        if (!response.ok) {
            throw new Error(`Error: ${response.status} ${response.statusText}`);
        }

        const ticket = await response.json();
        showTicketModal(ticket); // Show ticket details in a modal or other UI element
    } catch (error) {
        console.error('Failed to fetch ticket details:', error);
        showError('Unable to load ticket details. Please try again later.');
    }
}

// Helper functions
function formatDate(dateString) {
    return new Date(dateString).toLocaleString();
}

function setupFilterButtons() {
    const filterButtons = document.querySelectorAll('.filter-btn');
    filterButtons.forEach(button => {
        button.addEventListener('click', () => {
            const type = button.dataset.type;
            console.log(`Button clicked: ${type}`);
            if (type === "All") {
                fetchTickets(); 
            } else {
                filterTicketsByType(type); 
            }
            filterButtons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
        });
    });
}

function searchTickets() {
    const searchInput = document.getElementById('searchBox').value.toLowerCase();
    const rows = document.querySelectorAll('#ticketsList tr');

    rows.forEach(row => {
        const rowData = row.textContent.toLowerCase();
        if (rowData.includes(searchInput)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

function showError(message) {
    // Implement your error display logic here
    alert(message);
}

// 修改关闭模态框的方法
function closeModal() {
    $("#ticketModal").modal("hide");
}

// 更新模态框关闭事件监听
document.querySelector('.close').onclick = function() {
    closeModal();
}

window.onclick = function(event) {
    const modal = document.getElementById('ticketModal');
    if (event.target === modal) {
        closeModal();
    }
}

async function deleteTicket(ticketId) {
    try {
        const response = await fetch(`/api/tickets/${ticketId}`, {
            method: 'DELETE',
        });

        if (!response.ok) throw new Error('Failed to delete ticket');

        fetchTickets();
    } catch (error) {
        console.error('Error:', error);
        showError('Unable to delete ticket. Please try again later.');
    }
}

async function adminLogout() {
    try {
        const response = await fetch('/api/admin-logout', {
            method: 'POST',
            credentials: 'include'
        });
        
        if (!response.ok) {
            throw new Error('Logout failed');
        }
        
        window.location.href = '/'; // 重定向到首页
    } catch (error) {
        console.error('Logout error:', error);
        showError('Logout failed. Please try again.');
    }
}

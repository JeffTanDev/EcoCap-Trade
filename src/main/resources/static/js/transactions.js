document.addEventListener('DOMContentLoaded', function() {
    const transactions = JSON.parse(sessionStorage.getItem('transactions'));
    const tableBody = document.getElementById('transactionTable').getElementsByTagName('tbody')[0];
    
    if (transactions) {
        transactions.forEach(transaction => {
            const row = tableBody.insertRow();
            row.insertCell(0).textContent = transaction.transId;
            row.insertCell(1).textContent = transaction.date;
            row.insertCell(2).textContent = transaction.price;
            row.insertCell(3).textContent = transaction.eType;
        });
    } else {
        tableBody.innerHTML = '<tr><td colspan="4">No transactions found.</td></tr>';
    }
}); 
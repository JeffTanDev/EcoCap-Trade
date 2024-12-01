document.addEventListener('DOMContentLoaded', function() {
    console.log('Fetching profile data...');
    
    fetch('/api/profile', {
        method: 'GET',
        credentials: 'include'
    })
    .then(response => {
        console.log('Response status:', response.status);
        return response.json();
    })
    .then(response => {
        console.log('Received data:', response);
        
        if (response.status === 200 && response.data) {
            const user = response.data;
            console.log('User data:', user);
            
            document.getElementById('userID').textContent = user.userId || 'N/A';
            document.getElementById('userName').textContent = user.userName || 'N/A';
            document.getElementById('directQuota').textContent = user.directEQuota || 'N/A';
            document.getElementById('indirectEEQuota').textContent = user.indirectEEQuota || 'N/A';
            document.getElementById('indirectQuota').textContent = user.indirectEQuota || 'N/A';
            document.getElementById('companyName').textContent = user.cname || 'N/A';
            document.getElementById('companyLocation').textContent = user.clocation || 'N/A';
            document.getElementById('companyRegistration').textContent = user.cregistration || 'N/A';
            document.getElementById('companyType').textContent = user.ctype || 'N/A';
            document.getElementById('linkMan').textContent = user.linkMan || 'N/A';
            document.getElementById('email').textContent = user.email || 'N/A';
        } else {
            console.error('Error in response:', response);
            alert('Failed to load profile: ' + (response.message || 'Unknown error'));
        }
    })
    .catch(error => {
        console.error('Fetch error:', error);
        alert('Failed to load profile: ' + error.message);
    });
});

function logout() {
    fetch('/api/logout', { 
        method: 'POST',
        credentials: 'include'  // 确保包含cookies和session信息
    })
    .then(response => response.json())
    .then(data => {
        if (data.status === 200) {
            window.location.href = '/login';  // 登出成功后重定向到登录页
        } else {
            console.error('Logout failed:', data.message);
            alert('Logout failed: ' + data.message);
        }
    })
    .catch(error => {
        console.error('Error during logout:', error);
        alert('Logout failed: Network error');
    });
}

function buyQuota(type) {
    window.location.href = `${type}_Buy`;
    // Implement buy quota logic here
}

function sellQuota(type) {
    window.location.href = `${type}_Sell`;
    // Implement sell quota logic here
}

function seeTransaction() {
    axios.post('/api/user-transactions')
        .then(response => {
            const transactions = response.data;
            // 将交易数据存储在sessionStorage中，以便在新的页面中使用
            sessionStorage.setItem('transactions', JSON.stringify(transactions));
            window.location.href = '/profile/transactions'; // Redirect to the transaction page
        })
        .catch(error => {
            console.error('Error fetching transactions:', error);
            alert('Failed to load transactions.');
        });
}

function askForHelp() {
    alert('Contact support at support@example.com'); // Display a help message
}

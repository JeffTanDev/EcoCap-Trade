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
    fetch('/api/logout', { method: 'POST' })
        .then(response => response.json())
        .then(data => {
            if (data.code === 200) {
                window.location.href = '/login'; // Redirect to login page
            } else {
                alert('Logout failed: ' + data.msg);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Logout failed');
        });
}

function buyQuota(type) {
    alert('Buying quota: ' + type);
    // Implement buy quota logic here
}

function sellQuota(type) {
    alert('Selling quota: ' + type);
    // Implement sell quota logic here
}

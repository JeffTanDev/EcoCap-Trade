function adminLogin() {
    const username = document.getElementById('adminUsername').value;
    const password = document.getElementById('adminPassword').value;

    axios.post('/api/admin-login', {
        username: username,
        password: password
    })
    .then(response => {
        if (response.data.status === 200) {
            document.getElementById('admin-error-message').innerText = '';
            window.location.href = '/admin-dashboard'; // Redirect to admin dashboard
        } else {
            document.getElementById('admin-error-message').innerText = 'Invalid username or password.';
        }
    })
    .catch(error => {
        console.error('Error during admin login:', error);
        document.getElementById('admin-error-message').innerText = 'Invalid username or password.';
    });
} 
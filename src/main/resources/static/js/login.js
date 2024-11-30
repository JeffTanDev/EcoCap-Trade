function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (!username || !password) {
        document.getElementById('error-message').innerText = 'Username and password are required';
        return;
    }

    axios.post('/api/login', {
        username: username,
        password: password
    })
    .then(response => {
        console.log('Login response:', response);
        if (response.data.status === 200) {
            document.getElementById('error-message').innerText = '';
            window.location.href = '/profile';
        } else {
            document.getElementById('error-message').innerText = response.data.message;
        }
    })
    .catch(error => {
        console.error('Error during login:', error);
        if (error.response) {
            document.getElementById('error-message').innerText = error.response.data.message || 'Login failed';
        } else {
            document.getElementById('error-message').innerText = 'Network error occurred';
        }
    });
}

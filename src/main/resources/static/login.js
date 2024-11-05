function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    axios.post('/api/login', {
        username: username,
        password: password
    })
        .then(response => {
            // 假设后端返回 200 表示登录成功
            console.log(response);
            if (response.data.status === 200) {
                document.getElementById('error-message').innerText = '';
                window.location.href = '/profile.html'; // 登录成功后跳转到市场
            }
            if (response.data.status === 404){
                document.getElementById('error-message').innerText = 'Invalid username or password.';
            }
        })
        .catch(error => {
            console.error('Error during login:', error);
            document.getElementById('error-message').innerText = 'Invalid username or password.';
        });
}

function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    axios.post('/api/login', {
        username: username,
        password: password
    })
        .then(response => {
            // 假设后端返回 200 表示登录成功
            if (response.status === 200) {
                window.location.href = '/'; // 登录成功后跳转到首页
            }
        })
        .catch(error => {
            console.error('Error during login:', error);
            document.getElementById('error-message').innerText = 'Invalid username or password.';
        });
}

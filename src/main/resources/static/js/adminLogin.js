function adminLogin() {
    const username = document.getElementById('adminUsername').value;
    const password = document.getElementById('adminPassword').value;
    
    if (!username || !password) {
        alert('Please enter both username and password');
        return;
    }

    axios.post('/api/admin-login', {
        username: username,
        password: password
    })
    .then(response => {
        console.log('Response:', response.data);
        if (response.data.status === 200) {
            console.log('Admin login successful');
            window.location.href = '/admin/dashboard'; // 重定向到管理员面板
        } else {
            alert('Login failed: ' + response.data.msg);
        }
    })
    .catch(error => {
        console.error('Login error:', error);
        alert('Login failed: ' + (error.response?.data?.msg || error.message || 'Unknown error'));
    });
}

function adminLogout() {
    axios.post('/api/admin-logout')
    .then(response => {
        if (response.data.code === 200) {
            console.log('Admin logout successful');
            window.location.href = '/admin/login'; // 重定向到登录页
        } else {
            alert('Logout failed: ' + response.data.msg);
        }
    })
    .catch(error => {
        console.error('Logout error:', error);
        alert('Logout failed: ' + (error.response?.data?.msg || 'Unknown error'));
    });
}

// 检查管理员登录状态
function checkAdminLoginStatus() {
    axios.get('/api/current-admin')
    .then(response => {
        if (response.data.code !== 200) {
            // 未登录，重定向到登录页
            window.location.href = '/admin/login';
        }
    })
    .catch(() => {
        window.location.href = '/admin/login';
    });
} 
function redirectToLogin() {
    window.location.href = '/login';
}

function redirectToAdminLogin() {
    window.location.href = '/adminLogin';
}

function fetchCompanyInfo() {
    const companyName = document.getElementById('companyName').value;

    if (!companyName) {
        document.getElementById('result').innerText = 'Please enter a company name.';
        return;
    }

    axios.get(`/company?name=${companyName}`)
        .then(response => {
            console.log(response);
            const companyData = response.data.data;
            console.log(companyData);
            if (response.data.status === 200) {
                displayCompanyInfo(companyData);
            }
            else if (response.data.status === 404) {
                document.getElementById('result').innerText = 'Company not found or API error.';
            }
        })
        .catch(error => {
            console.error('Error fetching company info:', error);
            document.getElementById('result').innerText = 'Company not found or API error.';
        });
}

function displayCompanyInfo(data) {
    const resultDiv = document.getElementById('result');
    resultDiv.innerHTML = `
        <h2>${data.cname}</h2>
        <p>Location: ${data.clocation}</p>
        <p>Company Type: ${data.ctype}</p>
        <p>Registration: ${data.cregistration}</p>
        <p>Contact Person: ${data.linkMan}</p>
        <p>Email: ${data.email}</p>
        <p>Direct Emission Quota: ${data.directEQuota} (Used: ${data.usedDE})</p>
        <p>Indirect Energy Emission Quota: ${data.indirectEEQuota} (Used: ${data.usedIEE})</p>
        <p>Indirect Emission Quota: ${data.indirectEQuota} (Used: ${data.usedIE})</p>
    `;
}

function openAdminLoginModal() {
    document.getElementById('adminLoginModal').style.display = 'block';
}

function closeAdminLoginModal() {
    document.getElementById('adminLoginModal').style.display = 'none';
}

function adminLogin() {
    const username = document.getElementById('adminUsername').value;
    const password = document.getElementById('adminPassword').value;
    
    // Implement your admin login logic here
    // Example: Send a request to the server to validate admin credentials
    axios.post('/admin-login', { username, password })
        .then(response => {
            // Handle successful login
            closeAdminLoginModal();
        })
        .catch(error => {
            // Display error message
            document.getElementById('admin-error-message').innerText = 'Invalid credentials';
        });
}

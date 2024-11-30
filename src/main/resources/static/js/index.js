function redirectToLogin() {
    window.location.href = '/login';
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
        <p>Direct Emission Quota: ${data.directEQuota} (Used: ${data.usedDe})</p>
        <p>Indirect Energy Emission Quota: ${data.indirectEeQuota} (Used: ${data.usedIee})</p>
        <p>Indirect Emission Quota: ${data.indirectEQuota} (Used: ${data.usedIe})</p>
    `;
}

function redirectToLogin() {
    window.location.href = '/login.html';
}

function fetchCompanyInfo() {
    const companyName = document.getElementById('companyName').value;

    if (!companyName) {
        document.getElementById('result').innerText = 'Please enter a company name.';
        return;
    }

    axios.get(`/company?name=${companyName}`)
        .then(response => {
            const companyData = response.data.data;
            console.log(companyData);
            displayCompanyInfo(companyData);
        })
        .catch(error => {
            console.error('Error fetching company info:', error);
            document.getElementById('result').innerText = 'Company not found or API error.';
        });
}

function displayCompanyInfo(data) {
    const resultDiv = document.getElementById('result');
    resultDiv.innerHTML = `
        <h2>${data.cName}</h2>
        <p>Location: ${data.cLocation}</p>
        <p>Company Type: ${data.cType}</p>
        <p>Emission Quota Per Year: ${data.emissionQuota}</p>
    `;
}

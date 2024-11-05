function redirectToLogin() {
    window.location.href = 'login.html';
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
        <h2>${data.cName}</h2>
        <p>Location: ${data.cLocation}</p>
        <p>Company Type: ${data.cType}</p>
        <p>Emission Quota Per Year: ${data.emissionQuota}</p>
    `;
}

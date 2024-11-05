function logout() {
    window.location.href = 'index.html'; // Redirect to index page
}

function buyQuota(type) {
    window.location.href = `${type.toLowerCase()}_Buy.html`;
}

function sellQuota(type) {
    window.location.href = `${type.toLowerCase()}_Sell.html`;
}

// Sample data population (In real applications, this data would be fetched from a backend)
document.getElementById("userID").textContent = "00123";
document.getElementById("userName").textContent = "John Doe";
document.getElementById("directQuota").textContent = "1000 (Used: 300, Remaining: 700)";
document.getElementById("indirectEEQuota").textContent = "800 (Used: 200, Remaining: 600)";
document.getElementById("indirectQuota").textContent = "1200 (Used: 500, Remaining: 700)";
document.getElementById("companyName").textContent = "Tech Innovations Ltd.";
document.getElementById("companyLocation").textContent = "New York";
document.getElementById("companyRegistration").textContent = "123-456-789";
document.getElementById("companyType").textContent = "Manufacturing";
document.getElementById("linkMan").textContent = "Jane Smith";
document.getElementById("email").textContent = "contact@techinnovations.com";

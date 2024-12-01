// 初始化产品列表
document.addEventListener('DOMContentLoaded', function() {
    fetchQuotaData();
});

function fetchQuotaData() {
    fetch('/api/iq-quota', {
        method: 'GET',
        credentials: 'include'
    })
    .then(response => response.json())
    .then(response => {
        if (response.status === 200 && response.data) {
            const user = response.data;
            displayQuota(user);
        } else {
            console.error('Error fetching quota:', response.message);
            alert('Failed to load quota data: ' + response.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to load quota data: ' + error.message);
    });
}

function displayQuota(user) {
    const productList = document.getElementById("productList");
    productList.innerHTML = ''; // 清空现有内容

    const productCard = document.createElement("div");
    productCard.classList.add("col-md-4");
    productCard.innerHTML = `
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${'Indirect Emission Quota'}</h5>
                <p class="card-text">Total Quota: ${user.indirectEQuota || 0}</p>
                <p class="card-text">Remaining: ${user.indirectEQuota - (user.usedIE || 0)}</p>
                <button class="btn btn-primary apple-button" onclick="showProductDetails(${user.userId})">View Details</button>
            </div>
        </div>
    `;
    productList.appendChild(productCard);
}

function showProductDetails(userId) {
    const productList = document.getElementById("productList");
    const quotaCard = productList.querySelector(".card");
    const initialAmount = quotaCard.querySelector(".card-text:nth-child(2)").textContent.split(": ")[1];
    const availableAmount = quotaCard.querySelector(".card-text:nth-child(3)").textContent.split(": ")[1];
    
    document.getElementById("productTitle").textContent = 'Indirect Emission Quota';
    document.getElementById("productDescription").textContent = `Available quota details`;
    document.getElementById("productTodayQuota").textContent = initialAmount;
    document.getElementById("productRemainingQuota").textContent = availableAmount;
    document.getElementById("quantitySlider").max = availableAmount;
    document.getElementById("quantitySlider").value = 0;
    document.getElementById("selectedQuantity").textContent = 0;
    $("#productModal").modal("show");
}

// Function to update quantity display based on slider
function updateQuantityDisplay() {
    const slider = document.getElementById("quantitySlider");
    document.getElementById("selectedQuantity").textContent = slider.value;
}

// Function to handle the purchase action
function sellQuota() {
    const amount = parseFloat(document.getElementById("quantitySlider").value);
    if (!amount || amount <= 0) {
        alert('Please select a valid amount to sell');
        return;
    }

    fetch('/api/iq-sell', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify({ amount: amount })
    })
    .then(response => response.json())
    .then(response => {
        if (response.status === 200) {
            alert('Successfully sold quota');
            // 关闭模态框前先移除aria-hidden
            const modal = document.getElementById('productModal');
            modal.removeAttribute('aria-hidden');
            $("#productModal").modal("hide");
            // 刷新页面数据
            fetchQuotaData();
        } else {
            alert('Failed to sell quota: ' + response.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to process sell request');
    });
}

function returnToProfile() {
    window.location.href = '/profile';
}


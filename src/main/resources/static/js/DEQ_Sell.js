// 初始化产品列表
document.addEventListener('DOMContentLoaded', function() {
    fetchQuotaData();
});

function fetchQuotaData() {
    fetch('/api/deq-quota', {
        method: 'GET',
        credentials: 'include'
    })
    .then(response => response.json())
    .then(response => {
        if (response.status === 200 && response.data) {
            const quota = response.data;
            displayQuota(quota);
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

function displayQuota(quota) {
    const productList = document.getElementById("productList");
    productList.innerHTML = ''; // 清空现有内容

    const productCard = document.createElement("div");
    productCard.classList.add("col-md-4");
    productCard.innerHTML = `
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${'Direct Emission Quota'}</h5>
                <p class="card-text">Total Quota: ${quota.initialAmount || 0}</p>
                <p class="card-text">Remaining: ${quota.availableAmount || 0}</p>
                <button class="btn btn-primary apple-button" onclick="showProductDetails(${quota.productId})">View Details</button>
            </div>
        </div>
    `;
    productList.appendChild(productCard);
}

// Function to show product details in the modal
function showProductDetails(productId) {
    fetch('/api/deq-quota', {
        method: 'GET',
        credentials: 'include'
    })
    .then(response => response.json())
    .then(response => {
        if (response.status === 200 && response.data) {
            const quota = response.data;
            document.getElementById("productTitle").textContent = 'Direct Emission Quota';
            document.getElementById("productDescription").textContent = `Company: ${quota.productName}`;
            document.getElementById("productTodayQuota").textContent = quota.initialAmount || 0;
            document.getElementById("productRemainingQuota").textContent = quota.availableAmount || 0;
            document.getElementById("quantitySlider").max = quota.availableAmount || 0;
            document.getElementById("quantitySlider").value = 0;
            document.getElementById("selectedQuantity").textContent = 0;
            $("#productModal").modal("show");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to load product details');
    });
}

function updateQuantityDisplay() {
    const slider = document.getElementById("quantitySlider");
    document.getElementById("selectedQuantity").textContent = slider.value;
}

function returnToProfile() {
    window.location.href = '/profile';
}

function sellQuota() {
    const amount = parseFloat(document.getElementById("quantitySlider").value);
    if (!amount || amount <= 0) {
        alert('Please select a valid amount to sell');
        return;
    }

    fetch('/api/deq-sell', {
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
            // 关闭模态框
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
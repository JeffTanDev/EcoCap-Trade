const iqProductList = document.getElementById("iqProductList");

if (!iqProductList) {
    console.error('Element with ID "iqProductList" not found.');
} else {
    // 使用axios从后端获取数据
    axios.get('/api/iq-dailyRelease')
        .then(response => {
            console.log('API response:', response.data);
            const product = response.data.data;
            console.log('Product:', product);
            if (product && product.productName === "Indirect_Emission") {
                const productCard = document.createElement("div");
                productCard.classList.add("col-md-4");
                productCard.innerHTML = `
                <div class="card">
                  <div class="card-body">
                    <h5 class="card-title">${product.productName}</h5>
                    <p class="card-text">Available Today: ${product.initialAmount}</p>
                    <p class="card-text">Remaining: ${product.availableAmount}</p>
                    <button class="btn btn-primary apple-button" onclick="showIQProductDetails(${product.productId})">View Details</button>
                  </div>
                </div>
              `;
                iqProductList.appendChild(productCard);
            } else {
                console.warn('No product found or product name mismatch');
            }
        })
        .catch(error => {
            console.error('Error fetching product data:', error);
        });
}

// Function to show product details in the modal
function showIQProductDetails(productId) {
    axios.get(`/api/iq-productDetails/${productId}`)
        .then(response => {
            if (response.status === 200 && response.data) {
                const product = response.data.data;
                if (product) {
                    console.log('Product details:', product);
                    document.getElementById("iqProductTitle").textContent = product.productName;
                    document.getElementById("iqProductDescription").textContent = product.description || "No description available.";
                    document.getElementById("iqProductTodayQuota").textContent = product.initialAmount || 0;
                    document.getElementById("iqProductRemainingQuota").textContent = product.availableAmount || 0;
                    document.getElementById("iqQuantitySlider").max = product.availableAmount || 0;
                    document.getElementById("iqQuantitySlider").value = 0;
                    document.getElementById("iqSelectedQuantity").textContent = 0;
                    $("#iqProductModal").modal("show");
                } else {
                    console.warn('Product details not found');
                    alert('Product details not found');
                }
            } else {
                console.warn('Failed to load product details');
            }
        })
        .catch(error => {
            console.error('Error fetching product details:', error);
            alert('Failed to load product details');
        });
}

// Function to update quantity display based on slider
function updateQuantityDisplay() {
    const quantity = document.getElementById("iqQuantitySlider").value;
    document.getElementById("iqSelectedQuantity").textContent = quantity;
}

// Function to handle the purchase action
function purchaseQuota() {
    const productTitle = document.getElementById("iqProductTitle").textContent;
    const quantity = document.getElementById("iqQuantitySlider").value;

    const data = {
        product: productTitle,
        quantity: quantity
    };

    axios.post('/api/iq-purchase', data)
        .then(response => {
            alert(`Successfully purchased ${quantity} units of ${productTitle}`);
            $("#iqProductModal").modal("hide");
            refreshProductList(); // 刷新产品列表
        })
        .catch(error => {
            alert(`Error purchasing quota: ${error.response ? error.response.data : error.message}`);
        });
}

function refreshProductList() {
    axios.get('/api/iq-dailyRelease')
        .then(response => {
            const product = response.data.data;
            if (product && product.productName === "Indirect_Emission") {
                iqProductList.innerHTML = ''; // 清空当前列表
                const productCard = document.createElement("div");
                productCard.classList.add("col-md-4");
                productCard.innerHTML = `
                <div class="card">
                  <div class="card-body">
                    <h5 class="card-title">${product.productName}</h5>
                    <p class="card-text">Available Today: ${product.initialAmount}</p>
                    <p class="card-text">Remaining: ${product.availableAmount}</p>
                    <button class="btn btn-primary apple-button" onclick="showIQProductDetails(${product.productId})">View Details</button>
                  </div>
                </div>
              `;
                iqProductList.appendChild(productCard);
            }
        })
        .catch(error => {
            console.error('Error fetching product data:', error);
        });
}

function returnToProfile() {
    window.location.href = '/profile'; // Change 'profile.html' to your actual profile page URL
}


// Initialize product list
const productList = document.getElementById("productList");

// 使用axios从后端获取数据
axios.get('/api/dailyRelease')
    .then(response => {
        console.log('API response:', response.data);
        const product = response.data.data;
        console.log('Product:', product);
        if (product && product.productName === "Direct_Emissions") {
            const productCard = document.createElement("div");
            productCard.classList.add("col-md-4");
            productCard.innerHTML = `
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">${product.productName}</h5>
                <p class="card-text">Available Today: ${product.initialAmount}</p>
                <p class="card-text">Remaining: ${product.availableAmount}</p>
                <button class="btn btn-primary apple-button" onclick="showProductDetails(${product.productId})">View Details</button>
              </div>
            </div>
          `;
            productList.appendChild(productCard);
        } else {
            console.warn('No product found or product name mismatch');
        }
    })
    .catch(error => {
        console.error('Error fetching product data:', error);
    });

// Function to show product details in the modal
function showProductDetails(productId) {
    axios.get(`/api/productDetails/${productId}`)
        .then(response => {
            if (response.status === 200 && response.data) {
                const product = response.data.data;
                console.log('Product details:', product);
                document.getElementById("productTitle").textContent = product.productName;
                document.getElementById("productDescription").textContent = product.description || "No description available.";
                document.getElementById("productTodayQuota").textContent = Number(product.initialAmount) || 0;
                document.getElementById("productRemainingQuota").textContent = Number(product.availableAmount) || 0;
                document.getElementById("quantitySlider").max = Number(product.availableAmount) || 0;
                document.getElementById("quantitySlider").value = 0;
                document.getElementById("selectedQuantity").textContent = 0;
                $("#productModal").modal("show");
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
    const quantity = document.getElementById("quantitySlider").value;
    document.getElementById("selectedQuantity").textContent = quantity;
}

// Function to handle the purchase action
function purchaseQuota() {
    const productTitle = document.getElementById("productTitle").textContent;
    const quantity = document.getElementById("quantitySlider").value;

    const data = {
        product: productTitle,
        quantity: quantity
    };

    axios.post('/api/purchase', data)
        .then(response => {
            alert(`Successfully purchased ${quantity} units of ${productTitle}`);
            $("#productModal").modal("hide");
            refreshProductList(); // 刷新产品列表
        })
        .catch(error => {
            alert(`Error purchasing quota: ${error.response ? error.response.data : error.message}`);
        });
}

function refreshProductList() {
    axios.get('/api/dailyRelease')
        .then(response => {
            const product = response.data.data;
            if (product && product.productName === "Direct_Emissions") {
                productList.innerHTML = ''; // 清空当前列表
                const productCard = document.createElement("div");
                productCard.classList.add("col-md-4");
                productCard.innerHTML = `
                <div class="card">
                  <div class="card-body">
                    <h5 class="card-title">${product.productName}</h5>
                    <p class="card-text">Available Today: ${product.initialAmount}</p>
                    <p class="card-text">Remaining: ${product.availableAmount}</p>
                    <button class="btn btn-primary apple-button" onclick="showProductDetails(${product.productId})">View Details</button>
                  </div>
                </div>
              `;
                productList.appendChild(productCard);
            }
        })
        .catch(error => {
            console.error('Error fetching product data:', error);
        });
}

function returnToProfile() {
    window.location.href = '/profile'; // Change 'profile.html' to your actual profile page URL
}


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
    // 假设productId是从API中获取的
    axios.get(`/api/productDetails/${productId}`)
        .then(response => {
            const product = response.data;
            document.getElementById("productTitle").textContent = product.ProductName;
            document.getElementById("productDescription").textContent = product.Description;
            document.getElementById("productTodayQuota").textContent = product.Initial_Amount;
            document.getElementById("productRemainingQuota").textContent = product.Available_Amount;
            document.getElementById("quantitySlider").max = product.Available_Amount;
            document.getElementById("quantitySlider").value = 0;
            document.getElementById("selectedQuantity").textContent = 0;
            $("#productModal").modal("show");
        })
        .catch(error => {
            console.error('Error fetching product details:', error);
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

    // Data to send to the backend
    const data = {
        product: productTitle,
        quantity: quantity
    };

    // Send a POST request to the backend
    axios.post('/api/purchase', data)
        .then(response => {
            alert(`Successfully purchased ${quantity} units of ${productTitle}`);
            $("#productModal").modal("hide");
        })
        .catch(error => {
            alert(`Error purchasing quota: ${error.response ? error.response.data : error.message}`);
        });
}

function returnToProfile() {
    window.location.href = '/profile'; // Change 'profile.html' to your actual profile page URL
}


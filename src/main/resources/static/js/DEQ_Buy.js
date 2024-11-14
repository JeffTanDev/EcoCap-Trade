// Sample product data
const products = [
    { id: 1, title: "Direct Emission Type A", todayQuota: 500, remaining: 300, description: "Description of Type A" },
    { id: 2, title: "Direct Emission Type B", todayQuota: 400, remaining: 250, description: "Description of Type B" },
    { id: 3, title: "Direct Emission Type C", todayQuota: 600, remaining: 400, description: "Description of Type C" },
];

// Initialize product list
const productList = document.getElementById("productList");
products.forEach(product => {
    const productCard = document.createElement("div");
    productCard.classList.add("col-md-4");
    productCard.innerHTML = `
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">${product.title}</h5>
        <p class="card-text">Available Today: ${product.todayQuota}</p>
        <p class="card-text">Remaining: ${product.remaining}</p>
        <button class="btn btn-primary apple-button" onclick="showProductDetails(${product.id})">View Details</button>
      </div>
    </div>
  `;
    productList.appendChild(productCard);
});

// Function to show product details in the modal
function showProductDetails(productId) {
    const product = products.find(p => p.id === productId);
    if (product) {
        document.getElementById("productTitle").textContent = product.title;
        document.getElementById("productDescription").textContent = product.description;
        document.getElementById("productTodayQuota").textContent = product.todayQuota;
        document.getElementById("productRemainingQuota").textContent = product.remaining;
        document.getElementById("quantitySlider").max = product.remaining;
        document.getElementById("quantitySlider").value = 0;
        document.getElementById("selectedQuantity").textContent = 0;
        $("#productModal").modal("show");
    }
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


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function cartChanges(url) {
        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",
            complete: [
                function (response) {
                console.log("In ajax function")
                    $("#products").remove();
                    $("#cart-counter").remove();
                    $("#cart-count-top").remove();
                    $("#subtotal").remove();
                    htmlTable = `<ul id="products" class="products">`;
                    var data = ``;
                    var obj = $.parseJSON(response.responseText);
                    var total = 0;
                    number = obj.length;
                    for (var i = 0; i < obj.length; i++) {
                        var product = obj[i].product;
                        total += obj[i].total;
                        data += `<li class="product">
                        <a href="/product/`+product.id+`">
                        <span class="product-image">
                            <img src="`+product.image+`" alt="Product Photo">
                        </span>
                        </a>
                        <span class="product-details">
                            <a href="/product/`+product.id+`">
                                <h3>`+product.name+`</h3>
                            </a>
                                <span class="qty-price">
                                    <span class="qty">
                                        <button onclick="subtractTotal(`+product.price+`, 'qty-`+product.id+`')" class="minus-button" id="minus-button-`+product.id+`">-</button>
                                        <input data-onload="disableInput('qty-`+product.id+`')" type="number" id="qty-`+product.id+`" class="qty-input" step="1" min="1" max="10" name="qty-input" value="`+obj[i].quantity+`" pattern="[0-9]*" title="Quantity" inputmode="numeric">
                                        <button  onclick="addTotal(`+product.price+`, 'qty-`+product.id+`')" class="plus-button" id="plus-button-`+product.id+`">+</button>
                                    </span>
                                    <span class="price">`+product.price+`$</span>
                                </span>
                            </span>
                        </a>
                        <button type="button" onclick="cartChanges('/deleteCartProduct/`+product.id+`')" class="remove-button"><span class="remove-icon">X</span></button>
                    </li>`;
                    }
                    htmlTable += data + `</ul>`;
                    counter = `<span id="cart-counter" class="bag-count">`+ number +`</span>`;
                    counter2 = `<span id="cart-count-top" class="count">`+ number +`</span>`;
                    subtotal = `<span id="subtotal" class="amount">`+ total +`$</span>`
                    $("#pr").append(htmlTable);
                    $("#main-nav a").append(counter);
                    $("#cart-count-h2").append(counter2);
                    $("#subtotal-container").append(subtotal);
                }
            ]
        });
    }
</script>
<aside id="sidebar-cart">
    <main>
        <a href="" class="close-button"><span class="close-icon">X</span></a>
        <h2 id="cart-count-h2">Shopping Bag <span id="cart-count-top" class="count">${cartProducts.size()}</span></h2>
        <div id="pr">
            <ul id="products" class="products">
                <c:forEach items="${cartProducts}" var="product">
                    <li class="product">
                        <a href="/product/${product.product.id}">
                        <span class="product-image">
                            <img src="${product.product.image}" alt="Product Photo">
                        </span>
                        </a>
                        <span class="product-details">
                            <a href="/product/${product.product.id}">
                                <h3>${product.product.name}</h3>
                            </a>
                                <span class="qty-price">
                                    <span class="qty">
                                        <button onclick="subtractTotal(${product.product.price}, 'qty-${product.product.id}')" class="minus-button" id="minus-button-${product.product.id}">-</button>
                                        <input data-onload="disableInput('qty-${product.product.id}')" type="number" id="qty-${product.product.id}" class="qty-input" step="1" min="1"
                                               max="10" name="qty-input" value="${product.quantity}" pattern="[0-9]*" title="Quantity" inputmode="numeric">
                                        <button onclick="addTotal(${product.product.price}, 'qty-${product.product.id}')" class="plus-button" id="plus-button-${product.product.id}">+</button>
                                    </span>
                                    <span class="price">${product.product.price}$</span>
                                </span>
                            </span>
                        </a>
                        <button type="button" onclick="cartChanges('/deleteCartProduct/${product.product.id}')" class="remove-button"><span class="remove-icon">X</span></button>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="totals">
            <div id="subtotal-container" class="subtotal">
                <span class="label">Subtotal:</span> <span id="subtotal" class="amount">${total}$</span>
            </div>
        </div>
        <div class="action-buttons">
            <a class="checkout-button" href="/checkout">Checkout</a>
        </div>
    </main>
</aside>
<div id="sidebar-cart-curtain"></div>
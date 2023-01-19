<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Smart shop | Product actiions</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <meta
            name="description"
            content="A project to train with Spring and React frameworks"
    />

    <meta property="og:title" content="Smartshop"/>
    <meta property="og:type" content="website"/>
    <meta
            property="og:description"
            content="A project to try to create qualitative site."
    />
    <link rel="icon"
          href="https://ru.seaicons.com/wp-content/uploads/2015/10/Flat-TV-icon.png">

    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/add-product.css">
</head>
<body>
<jsp:include page="admin-header.jsp"/>
<main class="main">
    <form action="/admin/addProduct" method="post">
        <div class="big-group">
            <div class="input-group">
                <p>Name</p>
                <input name="name" value="${product.name}" type="text" required>
            </div>
            <div class="input-group">
                <p>Category</p>
                <select name="category" class="categories" id="categories">
                    <option value="Phones" selected>Phones</option>
                    <option value="Laptops">Laptops</option>
                    <option value="Watches">Watches</option>
                </select>
            </div>
            <div class="input-group">
                <p>Brand</p>
                <input name="brand" value="${product.brand}" type="text" required>
            </div>
        </div>
        <div class="big-group">
            <div class="input-group">
                <p>Price</p>
                <input name="price" value="${product.price}" type="number" required>
            </div>
            <div class="input-group">
                <p>Payment</p>
                <input name="payment" value="${product.payment}" type="text" required>
            </div>
            <div class="input-group">
                <p>Warranty</p>
                <input name="warranty" value="${product.warranty}" type="number" required>
            </div>
        </div>
        <div class="big-group">
            <div class="input-group">
                <label class="container1">
                    <c:if test="${product.available}">
                        <input name="available" type="checkbox" checked>
                    </c:if>
                    <c:if test="${!product.available}">
                        <input name="available" type="checkbox">
                    </c:if>
                    <span class="checkmark"></span>
                    Is available?
                </label>
            </div>
        </div>
        <div class="big-group">
            <div class="input-group">
                <p>Gifts</p>
                <c:if test="${!editMode}">
                    <input name="gifts" value="none" type="text">
                </c:if>
                <c:if test="${editMode}">
                    <input value="${product.gifts}" name="gifts" type="text">
                </c:if>
            </div>
            <div class="input-group">
                <p>Discount (%)</p>
                <c:if test="${!editMode}">
                    <input name="discount" value="0" type="number">
                </c:if>
                <c:if test="${editMode}">
                    <input value="${product.discount}" name="discount" type="number">
                </c:if>
            </div>
        </div>
        <div class="big-group">
            <div class="input-group">
                <p>Image link</p>
                <input value="${product.image}" name="link" type="text" style="width: 34vw" required>
            </div>
        </div>
        <button type="button" onclick="history.back()" class="history-back">Back</button>
        <c:if test="${!editMode}">
            <button type="submit" class="add-product-button">Continue</button>
        </c:if>
        <c:if test="${editMode}">
            <button type="submit" name="edit" class="add-product-button" formmethod="post" formaction="/admin/products">Save</button>
        </c:if>
    </form>
</main>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Smart shop | Technics</title>
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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/admin.css">
</head>
<body>
<jsp:include page="admin-header.jsp"/>
<main class="main">
    <form action="/admin/products" method="post">
        <div class="search-group">
            <button type="button" onclick="location.href='/admin/addProduct'" class="add-product">Add product</button>
            <input type="search" name="searchKey" class="search-input" placeholder="Find a product by id or name...">
            <input type="submit" class="search-button" value="Search">
        </div>
        <table class="faqs">
            <tr>
                <th style="width: 5%;">Id</th>
                <th style="width: 15%;">Name</th>
                <th style="width: 5%;">Category</th>
                <th style="width: 5%;">Brand</th>
                <th style="width: 10%;">Price</th>
                <th style="width: 5%;">Rating</th>
                <th style="width: 5%;">Available</th>
                <th style="width: 15%;">Payment</th>
                <th style="width: 15%;">Gifts</th>
                <th style="width: 5%;">Discount(%)</th>
                <th style="width: 5%;">Warranty(Months)</th>
                <th style="width: 5%;">Edit</th>
                <th style="width: 5%;">Delete</th>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.category.name}</td>
                    <td>${product.brand}</td>
                    <td>${product.price}</td>
                    <td>${product.rating}</td>
                    <td>${product.available}</td>
                    <td>${product.payment}</td>
                    <td>${product.gifts}</td>
                    <td>${product.discount}</td>
                    <td>${product.warranty}</td>
                    <td><a href="/admin/editProduct/${product.id}" class="btn btn-warning">Edit</a></td>
                    <td><a href="/admin/products/${currentPage}/delete/${product.id}" class="btn btn-danger"
                           onclick="if (!confirm('Are you sure you want to delete the message?')) return false;">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div class="pages">
        <c:if test="${totalPages > 1}">
            <p style="margin-right: 20px">Total Items: ${totalItems}</p>
            <c:if test="${currentPage > 1}">
                <a href="/admin/products/1" class="page-margin-words">First</a>
            </c:if>
            <c:if test="${currentPage <= 1}">
                <span class="page-margin-words">First</span>
            </c:if>

            <c:if test="${currentPage > 1}">
                <a href="/admin/products/${currentPage - 1}" class="page-margin">Previous</a>
            </c:if>
            <c:if test="${currentPage <= 1}">
                <span class="page-margin">Previous</span>
            </c:if>

            <c:forEach var = "i" begin = "1" end = "${totalPages}">
                <c:if test="${currentPage != i}">
                    <a href="/admin/products/${i}" class="page-margin">${i}</a>
                </c:if>
                <c:if test="${currentPage == i}">
                    <span class="page-margin">${i}</span>
                </c:if>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a href="/admin/products/${currentPage + 1}" class="page-margin-words">Next</a>
            </c:if>
            <c:if test="${currentPage >= totalPages}">
                <span class="page-margin-words">Next</span>
            </c:if>

            <c:if test="${currentPage < totalPages}">
                <a href="/admin/products/${totalPages}">Last</a>
            </c:if>
            <c:if test="${currentPage >= totalPages}">
                <span>Last</span>
            </c:if>
        </c:if>
    </div>
</main>
</body>
</html>
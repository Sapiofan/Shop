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

    <link rel="stylesheet" href="/css/general.css">
    <link rel="stylesheet" href="/css/admin.css">
</head>
<body>
<jsp:include page="admin-header.jsp"/>
<main class="main">
    <form action="/admin/reviews" method="post">
        <div class="search-group" style="width: 30%">
            <input type="search" name="search" class="search-input" placeholder="Find a review by user email or product id...">
            <input type="submit" class="search-button" value="Search">
        </div>
        <table class="faqs">
            <tr>
                <th style="width: 10%;">Name</th>
                <th style="width: 5%;">Rate</th>
                <th style="width: 15%;">Date</th>
                <th style="width: 10%;">Recommended</th>
                <th style="width: 55%;">Review</th>
                <th style="width: 5%;">Delete</th>
            </tr>
            <c:forEach items="${reviews}" var="review">
                <tr>
                    <td>${review.user.name}</td>
                    <td>${review.rating}</td>
                    <td>${review.date}</td>
                    <td>${review.recommended}</td>
                    <td>${review.review}</td>
                    <td><a href="/admin/reviews/${currentPage}/delete/${review.id}" class="btn btn-danger"
                           onclick="if (!confirm('Are you sure you want to delete the review?')) return false;">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div class="pages">
        <c:if test="${totalPages > 1}">
            <p style="margin-right: 20px">Total Items: ${totalItems}</p>
            <c:if test="${currentPage > 1}">
                <a href="/admin/reviews/1" class="page-margin-words">First</a>
            </c:if>
            <c:if test="${currentPage <= 1}">
                <span class="page-margin-words">First</span>
            </c:if>

            <c:if test="${currentPage > 1}">
                <a href="/admin/reviews/${currentPage - 1}" class="page-margin">Previous</a>
            </c:if>
            <c:if test="${currentPage <= 1}">
                <span class="page-margin">Previous</span>
            </c:if>

            <c:forEach var = "i" begin = "1" end = "${totalPages}">
                <c:if test="${currentPage != i}">
                    <a href="/admin/reviews/${i}" class="page-margin">${i}</a>
                </c:if>
                <c:if test="${currentPage == i}">
                    <span class="page-margin">${i}</span>
                </c:if>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a href="/admin/reviews/${currentPage + 1}" class="page-margin-words">Next</a>
            </c:if>
            <c:if test="${currentPage >= totalPages}">
                <span class="page-margin-words">Next</span>
            </c:if>

            <c:if test="${currentPage < totalPages}">
                <a href="/admin/reviews/${totalPages}">Last</a>
            </c:if>
            <c:if test="${currentPage >= totalPages}">
                <span>Last</span>
            </c:if>
        </c:if>
    </div>
</main>
</body>
</html>
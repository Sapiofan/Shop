<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

    <link rel="stylesheet" href="<c:url value="/css/general.css" />">
    <link rel="stylesheet" href="<c:url value="/css/admin.css" />">
    <link rel="stylesheet" href="<c:url value="/css/add-product.css" />">
</head>
<body>
<jsp:include page="admin-header.jsp"/>
<main class="main">
    <form action="/admin/products" method="post">
        <c:if test="${category == 'Phones'}">
            <c:forEach items="${keys}" var="key">
                <div class="input-group">
                    <p>${key}</p>
                    <select name="${key}" class="categories">
                        <c:forEach items="${phoneFilters[key]}" var="item">
                            <c:if test='${fn:containsIgnoreCase(item, "present")}'>
                                <option value="">Present?</option>
                            </c:if>
                            <option value="${item}">${item}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:forEach>
            <div class="big-group">
                <div class="input-group">
                    <p>Main Camera</p>
                    <input name="mainC" type="text" placeholder="48 eg" required>
                </div>
                <div class="input-group">
                    <p>Front Camera</p>
                    <input name="frontC" type="text" placeholder="48 eg" required>
                </div>
                <div class="input-group">
                    <p>Battery</p>
                    <input name="battery" type="text" placeholder="3000 eg" required>
                </div>
            </div>
        </c:if>

        <c:if test="${category == 'Laptops'}">
            <c:forEach items="${keys}" var="key">
                <div class="input-group">
                    <p>${key}</p>
                    <select name="${key}" class="categories">
                        <c:forEach items="${laptopFilters[key]}" var="item">
                            <c:if test='${fn:containsIgnoreCase(item, "present")}'>
                                <option value="">Present?</option>
                            </c:if>
                            <option value="${item}">${item}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:forEach>
            <div class="big-group">
                <div class="input-group">
                    <p>Weight</p>
                    <input name="weight" type="text" placeholder="1.7 kg e.g" required>
                </div>
            </div>
        </c:if>

        <c:if test="${category == 'Watches'}">
            <c:forEach items="${keys}" var="key">
                <div class="input-group">
                    <p>${key}</p>
                    <select name="${key}" class="categories">
                        <c:forEach items="${watchFilters[key]}" var="item">
                            <c:if test='${fn:containsIgnoreCase(item, "present")}'>
                                <option value="${item} doesn't present">Not present</option>
                            </c:if>
                            <option value="${item}">${item}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:forEach>
            <div class="big-group">
                <div class="input-group">
                    <p>Purpose</p>
                    <input name="purpose" type="text" placeholder="For business, for swimming eg" required>
                </div>
            </div>
        </c:if>

        <button onclick="history.back()" class="history-back">Back</button>
        <c:if test="${category == 'Phones'}">
            <button type="submit" name="phone" class="add-product-button">Save</button>
        </c:if>
        <c:if test="${category == 'Laptops'}">
            <button type="submit" name="laptop" class="add-product-button">Save</button>
        </c:if>
        <c:if test="${category == 'Watches'}">
            <button type="submit" name="watch" class="add-product-button">Save</button>
        </c:if>
    </form>
</main>
</body>
</html>
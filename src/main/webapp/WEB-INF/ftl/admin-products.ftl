<#import "spring.ftl" as spring>
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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <#assign css = "./../../static/css">

    <#if deletePath??>
        <#assign css = "./../../../../static/css">
    </#if>

    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/general.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/admin.css'/>"/>
</head>
<body>
<#include "admin-header.ftl">
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
            <#list products as product>
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.category.name}</td>
                    <td>${product.brand}</td>
                    <td>${product.price}</td>
                    <td>${product.rating}</td>
                    <td>${product.available?string('yes', 'no')}</td>
                    <td>${product.payment}</td>
                    <td>${product.gifts}</td>
                    <td>${product.discount}</td>
                    <td>${product.warranty}</td>
                    <td><a href="/admin/editProduct/${product.id}" class="btn btn-warning">Edit</a></td>
                    <td><a href="/admin/products/${currentPage}/delete/${product.id}" class="btn btn-danger"
                           onclick="if (!confirm('Are you sure you want to delete the message?')) return false;">Delete</a>
                    </td>
                </tr>
            </#list>
        </table>
    </form>
    <div class="pages">
        <#if totalPages?has_content && totalPages &gt; 1>
            <p style="margin-right: 20px">Total Items: ${totalItems}</p>
            <#if currentPage &gt; 1>
                <a href="/admin/products/1" class="page-margin-words">First</a>
            </#if>
            <#if currentPage <= 1>
                <span class="page-margin-words">First</span>
            </#if>

            <#if currentPage &gt; 1>
                <a href="/admin/products/${currentPage - 1}" class="page-margin">Previous</a>
            </#if>
            <#if currentPage <= 1>
                <span class="page-margin">Previous</span>
            </#if>

            <#assign x=totalPages>
            <#list 1..x as i>
                <#if currentPage != i>
                    <a href="/admin/products/${i}" class="page-margin">${i}</a>
                </#if>
                <#if currentPage == i>
                    <span class="page-margin">${i}</span>
                </#if>
            </#list>

            <#if currentPage < totalPages>
                <a href="/admin/products/${currentPage + 1}" class="page-margin-words">Next</a>
            </#if>
            <#if currentPage &gt;= totalPages>
                <span class="page-margin-words">Next</span>
            </#if>

            <#if currentPage < totalPages>
                <a href="/admin/products/${totalPages}">Last</a>
            </#if>
            <#if currentPage &gt;= totalPages>
                <span>Last</span>
            </#if>
        </#if>
    </div>
</main>
</body>
</html>
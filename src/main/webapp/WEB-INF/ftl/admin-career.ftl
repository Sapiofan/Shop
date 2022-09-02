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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <#assign css = "./../../static/css">

    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/general.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/admin.css'/>"/>
</head>
<body>
<#include "admin-header.ftl">
<main class="main">
    <form action="#" method="post">
        <table class="faqs">
            <tr>
                <th style="width: 10%;">First name</th>
                <th style="width: 10%;">Last name</th>
                <th style="width: 15%;">Email</th>
                <th style="width: 15%;">Phone</th>
                <th style="width: 15%;">Position</th>
                <th style="width: 20%;">Link to resume</th>
                <th style="width: 10%;">Date</th>
                <th style="width: 5%;">Delete</th>
            </tr>
            <#list careers as career>
                <tr>
                    <td>${career.first_name}</td>
                    <td>${career.last_name}</td>
                    <td>${career.email}</td>
                    <td>${career.phone}</td>
                    <td>${career.position}</td>
                    <td>${career.link}</td>
                    <td>${career.sent!""}</td>
                    <td><a href="/admin/career/${currentPage}/delete/${career.id}" class="btn btn-danger"
                           onclick="if (!confirm('Are you sure you want to delete the record?')) return false;">Delete</a></td>
                </tr>
            </#list>
        </table>
    </form>
    <div class="pages">
        <#if totalPages &gt; 1>
            <p style="margin-right: 20px">Total Items: ${totalItems}</p>
            <#if currentPage &gt; 1>
                <a href="/admin/career/1" class="page-margin-words">First</a>
            </#if>
            <#if currentPage <= 1>
                <span class="page-margin-words">First</span>
            </#if>

            <#if currentPage &gt; 1>
                <a href="/admin/career/${currentPage - 1}" class="page-margin">Previous</a>
            </#if>
            <#if currentPage <= 1>
                <span class="page-margin">Previous</span>
            </#if>

            <#assign x=totalPages>
            <#list 1..x as i>
                <#if currentPage != i>
                    <a href="/admin/career/${i}" class="page-margin">${i}</a>
                </#if>
                <#if currentPage == i>
                    <span class="page-margin">${i}</span>
                </#if>
            </#list>

            <#if currentPage < totalPages>
                <a href="/admin/career/${currentPage + 1}" class="page-margin-words">Next</a>
            </#if>
            <#if currentPage &gt;= totalPages>
                <span class="page-margin-words">Next</span>
            </#if>

            <#if currentPage < totalPages>
                <a href="/admin/career/${totalPages}">Last</a>
            </#if>
            <#if currentPage &gt;= totalPages>
                <span>Last</span>
            </#if>
        </#if>
    </div>
</main>
</body>
</html>
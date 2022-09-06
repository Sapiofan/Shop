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

    <meta property="og:title" content="Robot Genius"/>
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
    <table class="faqs">
        <tr>
            <th style="width: 10%;">First name</th>
            <th style="width: 10%;">Last name</th>
            <th style="width: 10%;">Email</th>
            <th style="width: 15%;">Subject</th>
            <th style="width: 40%;">Message</th>
            <th style="width: 10%;">Date</th>
            <th style="width: 5%;">Delete</th>
        </tr>
        <#list messages as message>
            <tr>
                <td>${message.contact.first_name}</td>
                <td>${message.contact.last_name}</td>
                <td>${message.contact.email}</td>
                <td style="text-align: left;">${message.subject}</td>
                <td style="text-align: left;">${message.message}</td>
                <td>${message.sent!""}</td>
                <td><a href="/admin/feedback/${currentPage}/delete/${message.id}" class="btn btn-danger"
                       onclick="if (!confirm('Are you sure you want to delete the message?')) return false;">Delete</a>
                </td>
            </tr>
        </#list>
    </table>
    <div class="pages">
        <#if totalPages &gt; 1>
            <p style="margin-right: 20px">Total Items: ${totalItems}</p>
            <#if currentPage &gt; 1>
                <a href="/admin/feedback/1" class="page-margin-words">First</a>
            </#if>
            <#if currentPage <= 1>
                <span class="page-margin-words">First</span>
            </#if>

            <#if currentPage &gt; 1>
                <a href="/admin/feedback/${currentPage - 1}" class="page-margin">Previous</a>
            </#if>
            <#if currentPage <= 1>
                <span class="page-margin">Previous</span>
            </#if>

            <#assign x=totalPages>
            <#list 1..x as i>
                <#if currentPage != i>
                    <a href="/admin/feedback/${i}" class="page-margin">${i}</a>
                </#if>
                <#if currentPage == i>
                    <span class="page-margin">${i}</span>
                </#if>
            </#list>

            <#if currentPage < totalPages>
                <a href="/admin/feedback/${currentPage + 1}" class="page-margin-words">Next</a>
            </#if>
            <#if currentPage &gt;= totalPages>
                <span class="page-margin-words">Next</span>
            </#if>

            <#if currentPage < totalPages>
                <a href="/admin/feedback/${totalPages}">Last</a>
            </#if>
            <#if currentPage &gt;= totalPages>
                <span>Last</span>
            </#if>
        </#if>
    </div>
</main>
</body>
</html>
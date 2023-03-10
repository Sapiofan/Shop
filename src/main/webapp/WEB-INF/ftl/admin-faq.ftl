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
            content="A project try to to create qualitative site."
    />
    <link rel="icon"
          href="https://ru.seaicons.com/wp-content/uploads/2015/10/Flat-TV-icon.png">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


    <#if editMode>
        <#assign css = "./../../static/css">
    </#if>
    <#if !editMode>
        <#assign css = "./../static/css">
    </#if>
    <#if deletePath??>
        <#assign css = "./../../../static/css">
    </#if>

    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/general.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/admin.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/faq.css'/>"/>
</head>
<body>
<#include "admin-header.ftl">
<main class="main">
    <form action="/admin/faq" method="post" class="faq-section">
        <#if editMode>
            <div class="question-adding editing">
                <input type="hidden" name="id" value="${id}">
                <textarea rows="4" name="editQuestion">${question}</textarea><br>
                <textarea rows="6" name="editAnswer">${answer}</textarea><br>
                <button type="submit" name="edit" class="save-button add-button">Edit answer</button>
            </div>
        </#if>
        <table class="faqs">
            <tr>
                <th style="width: 20%;">Question</th>
                <th>Answer</th>
                <th style="width: 10%;">Save changes</th>
                <th style="width: 10%;">Delete</th>
            </tr>
            <#list faqs as faq>
                <tr>
                    <td>${faq.question}
                    </td>
                    <td>${faq.answer}
                    </td>
                    <td><a href="/admin/faq/${faq.id}?ex-question=${faq.question}&ex-answer=${faq.answer}"
                           class="btn btn-primary">Edit</a></td>
                    <td>
                        <a href="/admin/faq/delete/${faq.id}" class="btn btn-danger"
                           onclick="if (!confirm('Are you sure you want to delete the answer?')) return false;">Delete</a>
                    </td>
                </tr>
            </#list>
        </table>
        <#if !editMode>
            <div class="question-adding">
                <textarea rows="4" name="question" placeholder="Your new question..."></textarea><br>
                <textarea rows="6" name="answer" placeholder="Your answer..."></textarea><br>
                <button type="submit" name="addFaq" class="save-button add-button">Add answer</button>
            </div>
        </#if>
    </form>
</main>
</body>
</html>
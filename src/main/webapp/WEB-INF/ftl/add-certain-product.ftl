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

    <#assign css = "./../static/css">

    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/general.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/admin.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/add-product.css'/>"/>
</head>
<body>
<#include "admin-header.ftl">
<main class="main">
    <form action="/admin/products" method="post">
        <#if category == 'Phones'>
            <#list phoneFilters?keys as key>
                <div class="input-group">
                    <p>${key}</p>
                    <select name="${key}" class="categories">
                        <#list phoneFilters[key] as item>
                            <#if item?contains("present")>
                                <option value="">Present?</option>
                            </#if>
                            <option value="${item}">${item}</option>
                        </#list>
                    </select>
                </div>
            </#list>
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
        </#if>

        <#if category == 'Laptops'>
            <#list laptopFilter?keys as key>
                <div class="input-group">
                    <p>${key}</p>
                    <select name="${key}" class="categories">
                        <#list laptopFilters[key] as item>
                            <#if item?contains("present")>
                                <option value="">Present?</option>
                            </#if>
                            <option value="${item}">${item}</option>
                        </#list>
                    </select>
                </div>
            </#list>
            <div class="big-group">
                <div class="input-group">
                    <p>Weight</p>
                    <input name="weight" type="text" placeholder="1.7 kg e.g" required>
                </div>
            </div>
        </#if>

        <#if category == 'Watches'>
            <#list watchFilters?keys as key>
                <div class="input-group">
                    <p>${key}</p>
                    <select name="${key}" class="categories">
                        <#list watchFilters[key] as item>
                            <#if item?contains("present")>
                                <option value="${item} doesn't present">Not present</option>
                            </#if>
                            <option value="${item}">${item}</option>
                        </#list>
                    </select>
                </div>
            </#list>
            <div class="big-group">
                <div class="input-group">
                    <p>Purpose</p>
                    <input name="purpose" type="text" placeholder="For business, for swimming eg" required>
                </div>
            </div>
        </#if>

        <button onclick="history.back()" class="history-back">Back</button>
        <#if category == 'Phones'>
            <button type="submit" name="phone" class="add-product-button">Save</button>
        </#if>
        <#if category == 'Laptops'>
            <button type="submit" name="laptop" class="add-product-button">Save</button>
        </#if>
        <#if category == 'Watches'>
            <button type="submit" name="watch" class="add-product-button">Save</button>
        </#if>
    </form>
</main>
</body>
</html>
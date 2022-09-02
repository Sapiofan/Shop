<#import "spring.ftl" as spring>
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

    <#assign css = "./../static/css">

    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/general.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/admin.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/add-product.css'/>"/>
</head>
<body>
<#include "admin-header.ftl">
<main class="main">
    <form action="/admin/addProduct" method="post">
        <div class="big-group">
            <div class="input-group">
                <p>Name</p>
                <#if !product?has_content>
                    <#assign name = "">
                    <#assign brand = "">
                    <#assign price = "">
                    <#assign payment = "">
                    <#assign warranty = "">
                    <#assign gifts = "">
                    <#assign discount = "">
                    <#assign image = "">
                    <#assign available = false>
                <#else>
                    <#assign name = product.name>
                    <#assign brand = product.brand>
                    <#assign price = product.price>
                    <#assign payment = product.payment>
                    <#assign warranty = product.warranty>
                    <#assign gifts = product.gifts>
                    <#assign discount = product.discount>
                    <#assign image = product.image>
                    <#assign available = product.available>
                </#if>
                <input name="name" value="${name}" type="text" required>
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
                <input name="brand" value="${brand}" type="text" required>
            </div>
        </div>
        <div class="big-group">
            <div class="input-group">
                <p>Price</p>
                <input name="price" value="${price}" type="number" required>
            </div>
            <div class="input-group">
                <p>Payment</p>
                <input name="payment" value="${payment}" type="text" required>
            </div>
            <div class="input-group">
                <p>Warranty</p>
                <input name="warranty" value="${warranty}" type="number" required>
            </div>
        </div>
        <div class="big-group">
            <div class="input-group">
                <label class="container1">
                    <#if available>
                        <input name="available" type="checkbox" checked>
                    </#if>
                    <#if !available>
                        <input name="available" type="checkbox">
                    </#if>
                    <span class="checkmark"></span>
                    Is available?
                </label>
            </div>
        </div>
        <div class="big-group">
            <div class="input-group">
                <p>Gifts</p>
                <#if !editMode??>
                    <input name="gifts" value="none" type="text">
                </#if>
                <#if editMode??>
                    <input value="${gifts}" name="gifts" type="text">
                </#if>
            </div>
            <div class="input-group">
                <p>Discount (%)</p>
                <#if !editMode??>
                    <input name="discount" value="0" type="number">
                </#if>
                <#if editMode??>
                    <input value="${discount}" name="discount" type="number">
                </#if>
            </div>
        </div>
        <div class="big-group">
            <div class="input-group">
                <p>Image link</p>
                <input value="${image}" name="link" type="text" style="width: 34vw" required>
            </div>
        </div>
        <button type="button" onclick="history.back()" class="history-back">Back</button>
        <#if !editMode??>
            <button type="submit" class="add-product-button">Continue</button>
        </#if>
        <#if editMode??>
            <button type="submit" name="edit" class="add-product-button" formmethod="post" formaction="/admin/products">
                Save
            </button>
        </#if>
    </form>
</main>
</body>
</html>
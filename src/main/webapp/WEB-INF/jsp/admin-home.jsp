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

    <link rel="stylesheet" href="/css/general.css">
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/home.css">
</head>
<body>
<jsp:include page="admin-header.jsp"/>
<main class="main">

    <form action="/admin/home" method="post">
        <div class="footnote-buttons">
            <button name="see" type="submit" class="reload">See changes</button>
            <button name="save" type="submit" class="footnote-save">Save changes</button>
        </div>
        <section id="banners">
            <div class="banner" style="margin-right: 50px;">
                <img class="banner-img"
                     src="${link1}">
                <div class="banner-text">
                    <p>${banner11}</p>
                    <h1>${banner12}</h1>
                    <p>${banner13}</p>
                    <button class="b-button">Shop</button>
                </div>
            </div>
            <div class="banner">
                <img class="banner-img"
                     src="${link2}">
                <div class="banner-text">
                    <p>${banner21}</p>
                    <h1>${banner22}</h1>
                    <p>${banner23}</p>
                    <button class="b-button">Shop</button>
                </div>
            </div>
        </section>
        <div class="footnote-banners">
            <div class="block1">
                <input name="link1" type="text" class="footnote-input footnote-link"
                       value="${link1}">
                <input name="banner11" type="text" class="footnote-input" value="${banner11}"><br>
                <input name="banner12" type="text" class="footnote-input" value="${banner12}"><br>
                <input name="banner13" type="text" class="footnote-input" value="${banner13}">
                <input name="productId1" type="number" class="footnote-input" value="${productId1}">
            </div>
            <div class="block2">
                <input type="text" name="link2" class="footnote-input footnote-link"
                       value="${link2}">
                <input name="banner21" type="text" class="footnote-input" value="${banner21}"><br>
                <input name="banner22" type="text" class="footnote-input" value="${banner22}"><br>
                <input name="banner23" type="text" class="footnote-input" value="${banner23}">
                <input name="productId2" type="number" class="footnote-input" value="${productId2}">
            </div>
        </div>
    </form>
</main>
</body>
</html>
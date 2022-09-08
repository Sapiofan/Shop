<#import "spring.ftl" as spring>
<#setting number_format="computer">
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

    <#assign css = "./static/css">
    <#assign js = "./static/js">
    <#assign img = "./static/img">

    <link rel="stylesheet" href="${css}/filters.css">
    <link rel="stylesheet" href="${css}/general.css">
    <link rel="stylesheet" src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body id="body">
<#include "header.ftl">
<main class="main">
    <form action="/${category?lower_case}" method="post" class="filters-blocks">
        <#if !hasAsideMenu??>
            <h1>${category}</h1>
        </#if>
        <#if hasAsideMenu??>
            <input type="hidden" value="${searchKey}" name="searchKey">
            <h1></h1>
        </#if>
        <article>
            <#if hasAsideMenu??>
                <div class="aside" id="filters">
                    <h2 class="search-header">See also products by categories</h2>
                    <div class="aside-links">
                        <a href="/phones">
                            <div class="aside-category">
                                Phones
                            </div>
                        </a>
                        <a href="/laptops">
                            <div class="aside-category">
                                Laptops
                            </div>
                        </a>
                        <a href="/watches">
                            <div class="aside-category">
                                Watches
                            </div>
                        </a>
                        <a href="/discounts">
                            <div class="aside-category">
                                Discounts
                            </div>
                        </a>
                        <a href="/bestsellers">
                            <div class="aside-category">
                                Bestsellers
                            </div>
                        </a>
                        <a href="#">
                            <div class="aside-category">
                                Drones
                            </div>
                        </a>
                        <a href="#">
                            <div class="aside-category">
                                Computers
                            </div>
                        </a>
                    </div>
                </div>
            </#if>
            <#if !hasAsideMenu??>
                <div class="aside" id="filters">
                    <div class="head-filters">
                        <p>Filters</p>
                        <span onclick="document.getElementById('filters').style.display='none';
                        document.body.style.overflowY = 'auto';" class="close close-f">&times;</span>
                        <hr>
                    </div>
                    <div id="price">
                        <span class="price-header">Price</span>
                        <div class="wrapper">
                            <div class="price-input">
                                <div class="field">
                                    <input type="number" name="input-min" class="input-min" value="${minValue}">
                                </div>
                                <div class="separator">-</div>
                                <div class="field">
                                    <input type="number" name="input-max" class="input-max" value="${maxValue + 1000}">
                                </div>
                            </div>
                            <div class="slider">
                                <div class="progress"></div>
                            </div>
                            <div class="range-input">
                                <input type="range" class="range-min" min="0" max="${max}" value="${minValue}"
                                       step="500">
                                <input type="range" class="range-max" min="0" max="${max}" value="${maxValue}"
                                       step="500">
                            </div>
                        </div>
                    </div>
                    <#list filters?keys as filter>
                        <div id="${filter}" class="dropdown-check-list">
                            <span class="anchor">${filter}</span>
                            <ul class="items">
                                <#assign flag = false>
                                <#list filters[filter] as item>
                                    <label class="container1">${item}
                                        <#list filters?keys as name>
                                            <#if name == item>
                                                <input name="filter-name" type="checkbox" value="${item}" checked>
                                                <span class="checkmark"></span>
                                                <#assign flag = true>
                                            </#if>
                                        </#list>
                                        <#if !flag>
                                            <input name="filter-name" type="checkbox" value="${item}">
                                            <span class="checkmark"></span>
                                        </#if>
                                        <#assign flag = false>
                                    </label>
                                </#list>
                            </ul>
                        </div>
                        <br>
                    </#list>
                    <input type="submit" class="filters-button" value="Show">
                </div>
            </#if>
            <section id="serction-products">
                <div class="sort-part">
                    <p class="sort-text">Show:</p>
                    <select name="sort" onchange="submit()" id="sort" class="sort">
                        <option id="popularity" class="sort-choose">By popularity</option>
                        <option id="fcte" class="sort-choose">From cheap to expensive</option>
                        <option id="fetc" class="sort-choose">From expensive to cheap</option>
                        <option id="novelties" class="sort-choose">Novelties</option>
                        <option id="bn" class="sort-choose">By name</option>
                    </select>
                    <div class="filters-icon">
                        <button type="button" onclick="document.getElementById('filters').style.display='block';
                            document.body.style.overflowY = 'hidden';">
                            <img src="${img}/filter-_2_.svg">
                        </button>
                    </div>
                </div>
                <div class="product-list">
                    <#list products as product>
                        <div class="product-card">
                            <a href="/product/${product.id}">
                                <div class="card-image">
                                    <img src="${product.image}" class="product-img">
                                </div>
                            </a>
                            <div class="right-desc">
                                <a href="/product/${product.id}" class="product-link">
                                    <div class="product-name">
                                        <p>${product.name}</p>
                                    </div>
                                </a>
                                <div id="${product.id}-signs" class="card-signs">
                                    <div class="Stars" style="--rating: ${product.rating};"></div>
                                    <a href="/product/${product.id}#reviews">
                                        <img src="${img}/chat-bubble.png"
                                             style="width: 20px; height: 20px; margin-right: 10px;">
                                    </a>
                                    <#if isAuthenticated??>
                                        <#assign contains = false>
                                        <#list favoriteProducts as favorite>
                                            <#if favorite.id == product.id>
                                                <#assign contains = true>
                                            </#if>
                                        </#list>
                                        <#if !contains>
                                            <button id="${product.id}" type="button" class="empty-heart"
                                                    onclick="productChanges('/addProduct/${product.id}')"><img
                                                        src="${img}/heart.png"
                                                        style="width: 20px; height: 20px; margin-right: 10px;"></button>
                                        </#if>
                                        <#if contains>
                                            <button id="${product.id}" type="button" class="empty-heart"
                                                    onclick="productChanges('/deleteFavorite/${product.id}')"><img
                                                        src="${img}/red-heart.png"
                                                        style="width: 20px; height: 20px; margin-right: 10px;"></button>
                                        </#if>
                                    </#if>
                                    <#if !isAuthenticated??>
                                        <button type="button" class="empty-heart"
                                                onclick="document.getElementById('login').style.display='block';">
                                            <img src="${img}/heart.png"
                                                 style="width: 20px; height: 20px; margin-right: 10px;">
                                        </button>
                                    </#if>
                                </div>
                                <div class="buy-elements">
                                    <div class="filters-price">
                                        <#if product.discount != 0>
                                            <div class="pre-price">${product.price} &#8372;</div>
                                            <#assign result = product.price-(product.price*product.discount/100)>
                                            <div class="cur-price">${result?round}
                                                &#8372;
                                            </div>
                                        <#else>
                                            <div class="cur-price">${product.price} &#8372;</div>
                                        </#if>
                                    </div>
                                    <#if isAuthenticated??>
                                        <button type="button" onclick="cartChanges('/addToCart/${product.id}')"
                                                class="add-product">In Cart
                                        </button>
                                    </#if>
                                    <#if !isAuthenticated??>
                                        <a href="/checkout/${product.id}">
                                            <button type="button" class="add-product">Buy</button>
                                        </a>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            </section>
        </article>
    </form>
</main>
<#include "footer.ftl">
<script src="${js}/general.js"></script>
<script src="${js}/filters.js"></script>
</body>

<script>
    function submit() {
        $('form#sort-form').submit();
    }

    window.onload = ev => {
        let str = '${sortType}';
        let pop = document.getElementById("popularity");
        let fetc = document.getElementById("fetc");
        let fcte = document.getElementById("fcte");
        let nov = document.getElementById("novelties");
        let bn = document.getElementById("bn");
        if (str == pop.textContent) {
            pop.selected = "true";
        } else if (str == fetc.textContent) {
            fetc.selected = "true";
        } else if (str == fcte.textContent) {
            fcte.selected = "true";
        } else if (str == nov.textContent) {
            nov.selected = "true";
        } else if (str == bn.textContent) {
            bn.selected = "true";
        }
    }
</script>

</html>
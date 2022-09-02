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

    <#assign css = "/static/css">
    <#assign js = "/static/js">
    <#assign img = "/static/img">

    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/general.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/description.css'/>"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body id="body">
<header>
    <nav class="nav" id="nav">
        <a href="/" id="logo"><p class="logo">SmartShop</p></a>
        <button class="mobile-icon" id="mobile-icon"
                onclick="document.getElementById('body').style = '--visibility: hidden';">
            <a href="javascript:void(0);" class="icon">&#9776;</a>
        </button>
        <div class="navigation" id="navigation">
            <a data-langkey="Nav-Our-Work" href="/">Home</a>
            <div class="dropdown">
                <button class="dropbtn">Categories</button>
                <div class="dropdown-content">
                    <a href="/phones">Phones</a>
                    <a href="/laptops">Laptops</a>
                    <a href="/watches">Watches</a>
                    <a href="/discounts">Discounts</a>
                    <a href="/bestsellers">Bestsellers</a>
                    <a href="#">Computers</a>
                    <a href="#">Drones</a>
                </div>
            </div>
            <a data-langkey="Nav-The-Team" href="/about">About</a>
            <a data-langkey="Nav-The-Process" href="/help">Help</a>
            <a data-langkey="Nav-Contact" href="/contact">Contact</a>
        </div>
        <div class="signs">
            <div id="search-sign" class="sign">
                <form action="/searching" method="get">
                    <div id="search-wrapper">
                        <input type="text" name="search" id="search" autocomplete="off" placeholder="Search device..."/>
                        <div id="close-icon"></div>
                        <input type="submit" style="display:none"/>
                    </div>
                </form>
            </div>
            <div class="sign">
                <#if user?has_content>
                    <form action="/" method="post">
                        <div class="dropdown-user">
                            <button type="button" class="dropbtn-user user"><img src="./..${img}/user.png"
                                                                                 style="width: 36px; height: 36px;">
                            </button>
                            <div class="dropdown-content-user">
                                <a href="#">SmartShop money</a>
                                <a href="#">My orders</a>
                                <a>
                                    <button type="submit" class="logout" name="logout">Logout</button>
                                </a>
                            </div>
                        </div>
                    </form>
                </#if>
                <#if !user?has_content>
                    <button onclick="document.getElementById('login').style.display='block'"
                            class="user"><img src="./..${img}/user.png" style="width: 36px; height: 36px;"></button>
                </#if>
            </div>
            <div class="sign">
                <#if user?has_content>
                    <button id="header-heart" onclick="document.getElementById('likes').style.display='block'"
                            class="likes">
                        <img src="./..${img}/heart (2).png" style="width: 36px; height: 36px;">
                        <span id="header-heart-n" class="wishlist-count">${favoriteProducts?size!0}</span>
                    </button>
                </#if>
                <#if !user?has_content>
                    <button onclick="document.getElementById('login').style.display='block'"
                            class="likes">
                        <img src="./..${img}/heart (2).png" style="width: 36px; height: 36px;">
                    </button>
                </#if>
            </div>
            <div class="sign" id="cart">
                <#if !user?has_content>
                    <nav id="main-nav">
                        <a class="cart-button" href="" onclick="document.getElementById('body').style = '--visibility: auto';
                    document.getElementById('login').style.display='block';">
                            <span class="bag-icon"></span>
                        </a>
                    </nav>
                </#if>
                <#if user?has_content>
                    <nav id="main-nav">
                        <a class="cart-button" href=""
                           onclick="document.getElementById('body').style = '--visibility: hidden';">
                            <span id="cart-counter" class="bag-count">${cartProducts?size!0}</span>
                            <span class="bag-icon"></span>
                        </a>
                    </nav>
                </#if>
            </div>
        </div>
    </nav>
    <#include "registration.ftl">
    <#include "login.ftl">
    <#include "favorite.ftl">
    <#include "asideMenu.ftl">
    <#if isAuthenticated??>
        <#include "cart.ftl">
        <script>
            document.getElementById('body').style = "--visibility: hidden";
        </script>
    </#if>
</header>
<main class="main" id="main">
    <header id="chars-header" class="">
        <div class="product-chars">
            <div class="product-image-description">
                <img src="${product.image}">
            </div>
            <div class="full-product">
                <p>${product.name}</p>
                <nav class="chars-nav sticky">
                    <a href="#carousel">About</a>
                    <a href="#chars">Characteristics</a>
                    <a href="#reviews">Reviews</a>
                </nav>
            </div>
            <div class="user-rating">
                <p class="u-rating">User rating: </p>
                <div class="one-line">
                    <div class="Stars" style="--rating: ${product.rating};"></div>
                    <p class="rating">${product.rating}</p>
                    <a href="#reviews" class="reviews-link">
                        <img src="./../${img}/chat-bubble.png" width="16px" height="16px">
                        <p>${reviews?size} review(s)</p>
                    </a>
                </div>
            </div>
            <div class="signs-desc">
                <div class="header-price">
                    <#assign result = product.price-(product.price*product.discount/100)>
                    ${result?round}$
                </div>
                <#if user?has_content>
                    <button type="button" onclick="cartChanges('/addToCart/${product.id}')" class="header-buy"
                            id="header-buy">Add to cart
                    </button>
                </#if>
                <#if !user?has_content>
                    <a href="/checkout/${product.id}">
                        <button class="header-buy">Buy</button>
                    </a>
                </#if>
                <div class="header-like">
                    <button type="button" class="empty-heart" onclick="productChanges('/addProduct/${product.id}')">
                        <img src="./../${img}/heart (2).png">
                    </button>
                </div>
            </div>
        </div>
        <nav class="chars-nav">
            <a href="#carousel">About</a>
            <a href="#chars">Characteristics</a>
            <a href="#reviews">Reviews</a>
        </nav>
    </header>
    <section id="carousel">
        <div class="carousel-container">
            <ul class="carousel my-carousel carousel--thumb">
                <input class="carousel__activator" type="radio" id="K" name="thumb" checked="checked"/>
                <input class="carousel__activator" type="radio" id="L" name="thumb"/>
                <input class="carousel__activator" type="radio" id="M" name="thumb"/>
                <input class="carousel__activator" type="radio" id="N" name="thumb"/>
                <input class="carousel__activator" type="radio" id="O" name="thumb"/>
                <div class="carousel__controls">
                    <label class="carousel__control carousel__control--backward" for="O"></label>
                    <label class="carousel__control carousel__control--forward" for="L"></label>
                </div>
                <div class="carousel__controls">
                    <label class="carousel__control carousel__control--backward" for="K"></label>
                    <label class="carousel__control carousel__control--forward" for="M"></label>
                </div>
                <div class="carousel__controls">
                    <label class="carousel__control carousel__control--backward" for="L"></label>
                    <label class="carousel__control carousel__control--forward" for="N"></label>
                </div>
                <div class="carousel__controls">
                    <label class="carousel__control carousel__control--backward" for="M"></label>
                    <label class="carousel__control carousel__control--forward" for="O"></label>
                </div>
                <div class="carousel__controls">
                    <label class="carousel__control carousel__control--backward" for="N"></label>
                    <label class="carousel__control carousel__control--forward" for="K"></label>
                </div>
                <li class="carousel__slide" style="background-image: url('${product.image}');"></li>
                <li class="carousel__slide"></li>
                <li class="carousel__slide"></li>
                <li class="carousel__slide"></li>
                <li class="carousel__slide"></li>
                <div class="carousel__indicators">
                    <label class="carousel__indicator" style="background-image: url('${product.image}');"
                           for="K"></label>
                    <label class="carousel__indicator" for="L"></label>
                    <label class="carousel__indicator" for="M"></label>
                    <label class="carousel__indicator" for="N"></label>
                    <label class="carousel__indicator" for="O"></label>
                </div>
            </ul>
        </div>
        <div class="about-product">
            <div class="general-info">
                <div class="general-info-text">
                    <#if product.available>
                        <p>Are available</p>
                    </#if>
                    <#if !product.available>
                        <p>Not available</p>
                    </#if>
                    <#if product.discount != 0>
                        <div class="discount-price">
                            <p class="old-price">${product.price} $</p>
                            <p class="profit">-${product.price-discount}</p>
                        </div>
                        <div class="price-now">
                            ${discount} $
                        </div>
                    </#if>
                    <#if product.discount == 0>
                        <div class="price-now">
                            ${product.price}$
                        </div>
                    </#if>
                </div>
                <#if user?has_content>
                    <button type="button" onclick="cartChanges('/addToCart/${product.id}')" class="add-to-cart">Add to
                        cart
                    </button>
                </#if>
                <#if !user?has_content>
                    <a href="/checkout/${product.id}">
                        <button class="add-to-cart">Buy</button>
                    </a>
                </#if>
                <div class="like-container">
                    <button type="button" class="empty-heart" onclick="productChanges('/addProduct/${product.id}')">
                        <img src="./../${img}/heart.png" style="width: 32px; height: 32px;">
                    </button>
                </div>
            </div>
            <#if product.discount != 0 || product.gifts != 'none'>
                <div class="discounts">
                    <h6>Discounts and Stocks</h6>
                    <#if product.discount != 0>
                        <div class="discount-type">
                            <img src="./../${img}/badge.png" width="30px" height="30px">
                            <p><span class="bold-text">Discount</span> Benefit up
                                to ${product.price-discount} UAH</p>
                        </div>
                    </#if>
                    <#if product.gifts != 'none'>
                        <div class="discount-type">
                            <img src="./../${img}/shipping.png" width="30px" height="30px">
                            <p><span class="bold-text">Gifts</span> ${product.gifts}</p>
                        </div>
                    </#if>
                </div>
            </#if>
            <div class="payment">
                <h6>Payment</h6>
                <p>${product.payment}</p>
            </div>
            <div class="exchange">
                <h6>Exchange/Return</h6>
                <p>Exchange and return of goods is carried out within 14 days after the purchase,
                    in accordance with the law of Ukraine "On the Protection of Consumer Rights of Ukraine"</p>
                <p>Warranty: ${product.warranty} months</p>
            </div>
        </div>
    </section>
    <section id="chars" class="">
        <h3>Characteristics</h3>
        <#list descTable?keys as key>
            <h5>${key}</h5>
            <table class="screen-table">
                <col style="width: 50%;"/>
                <#list descTable[key] as value>
                    <tr>
                        <td>${value}</td>
                        <#list descData?keys as data>
                            <#if data == value>
                                <#assign dataKey = descData[data]>
                                <#break>
                            </#if>
                        </#list>
                        <td>${dataKey!""}</td>
                    </tr>
                </#list>
            </table>
        </#list>
    </section>
    <section id="reviews" class="">
        <h3>Reviews: <span class="reviews-num">${reviews?size!0}</span></h3>
        <div class="general-rev-info">
            <div class="statistics-block">
                <div class="user-rating-block">
                    <p class="user-rating-r">User rating:</p>
                    <div class="star-rating-cont">
                        <p>${product.rating}</p>
                        <div class="Stars star-r" style="--rating: ${product.rating};"></div>
                    </div>
                </div>
                <div class="recommended-circle">
                    <div class="single-chart">
                        <svg viewBox="0 0 36 36" class="circular-chart green">
                            <path class="circle-bg"
                                  d="M18 2.0845
                                      a 15.9155 15.9155 0 0 1 0 31.831
                                      a 15.9155 15.9155 0 0 1 0 -31.831"
                            />
                            <path class="circle"
                                  stroke-dasharray="${recommended}, 100"
                                  d="M18 2.0845
                                      a 15.9155 15.9155 0 0 1 0 31.831
                                      a 15.9155 15.9155 0 0 1 0 -31.831"
                            />
                            <text x="18" y="20.35" class="percentage">${recommended}%</text>
                        </svg>
                    </div>
                    <p>Recommended</p>
                </div>
            </div>
            <#if user?has_content>
                <div class="feedback-but-cont">
                    <button class="feedback-but" onclick="document.getElementById('feedback').style.display='block'">
                        Leave feedback
                    </button>
                </div>
            </#if>
            <#if !user?has_content>
                <div class="feedback-but-cont">
                    <button class="feedback-but"
                            onclick="document.getElementById('registration').style.display='block'">
                        Leave feedback
                    </button>
                </div>
            </#if>
        </div>
        <div class="rewiews-container">
            <#list reviews as review>
                <div class="review-container">
                    <div class="name">
                        <p>${review.user.name}</p>
                        <div class="Stars" style="--rating: ${review.rating};"></div>
                    </div>
                    <div class="comment-container">
                        <div class="date-check">
                            <p class="date">${review.date}</p>
                            <#if review.recommended>
                                <div class="recommend">
                                    <img src="./../${img}/check.png" width="16px" height="16px">
                                    <p>Recommended</p>
                                </div>
                            </#if>
                        </div>
                        <p class="rev-text">${review.review}</p>
                    </div>
                </div>
            </#list>
            <div id="feedback" class="modal-feed">
                <form class="modal-feed-content" action="/product/${product.id}" method="post">
                    <div class="container">
                        <span onclick="document.getElementById('feedback').style.display='none'"
                              class="close">&times;</span>
                        <div class="rate-product">
                            <p>Rate this product:</p>
                            <div class="rate">
                                <input type="radio" id="star5" name="rate" value="5" required/>
                                <label for="star5" title="text">5 stars</label>
                                <input type="radio" id="star4" name="rate" value="4" required/>
                                <label for="star4" title="text">4 stars</label>
                                <input type="radio" id="star3" name="rate" value="3" required/>
                                <label for="star3" title="text">3 stars</label>
                                <input type="radio" id="star2" name="rate" value="2" required/>
                                <label for="star2" title="text">2 stars</label>
                                <input type="radio" id="star1" name="rate" value="1" required/>
                                <label for="star1" title="text">1 star</label>
                            </div>
                        </div>
                        <textarea class="rev-input" type="text" name="review" placeholder="Your review"
                                  required></textarea>
                        <label class="container1">I recommend this product
                            <input name="recommend" type="checkbox" checked>
                            <span class="checkmark"></span>
                        </label>

                        <div class="clearfix">
                            <button type="submit" class="send-rev" name="reviewSend">Send</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</main>
<#include "footer.ftl">
<script src="${js}/description.js"></script>
<script src="${js}/general.js"></script>
<script>
    window.onload = ev => {
        let reviewExists = "${reviewExists!""}"
        if (reviewExists) {
            alert("Sorry, but you have already sent a review for this product")
        }
    }
</script>
</body>
</html>
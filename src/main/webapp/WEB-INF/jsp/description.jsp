<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="/css/general.css">
    <link rel="stylesheet" href="/css/description.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body id="body">
<jsp:include page="header.jsp"/>
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
                        <img src="/img/chat-bubble.png" width="16px" height="16px">
                        <p>${reviews.size()} review(s)</p>
                    </a>
                </div>
            </div>
            <div class="signs-desc">
                <div class="header-price">
                    <c:set var = "i" value = "${product.price-(product.price*product.discount/100)}" />
                    <fmt:parseNumber var = "discount" integerOnly = "true"
                                     type = "number" value = "${i}" />
                    ${discount}$
                </div>
                <sec:authorize access="isAuthenticated()">
                    <button type="button" onclick="cartChanges('/addToCart/${product.id}')" class="header-buy" id="header-buy">Add to cart</button>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <a href="/checkout/${product.id}">
                        <button class="header-buy">Buy</button>
                    </a>
                </sec:authorize>
                <div class="header-like">
                    <button type="button" class="empty-heart" onclick="productChanges('/addProduct/${product.id}')">
                        <img src="/img/heart (2).png">
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
                    <c:if test="${product.available}">
                        <p>Are available</p>
                    </c:if>
                    <c:if test="${!product.available}">
                        <p>Not available</p>
                    </c:if>
                    <c:if test="${product.discount != 0}">
                        <div class="discount-price">
                            <p class="old-price">${product.price} $</p>
                            <p class="profit">-${product.price-discount}</p>
                        </div>
                        <div class="price-now">
                                ${discount} $
                        </div>
                    </c:if>
                    <c:if test="${product.discount == 0}">
                        <div class="price-now">
                                ${product.price}$
                        </div>
                    </c:if>
                </div>
                <sec:authorize access="isAuthenticated()">
                        <button type="button" onclick="cartChanges('/addToCart/${product.id}')" class="add-to-cart">Add to cart</button>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <a href="/checkout/${product.id}">
                        <button class="add-to-cart">Buy</button>
                    </a>
                </sec:authorize>
                <div class="like-container">
                    <button type="button" class="empty-heart" onclick="productChanges('/addProduct/${product.id}')">
                        <img src="/img/heart.png" style="width: 32px; height: 32px;">
                    </button>
                </div>
            </div>
            <c:if test="${product.discount != 0 || product.gifts != 'none'}">
                <div class="discounts">
                    <h6>Discounts and Stocks</h6>
                    <c:if test="${product.discount != 0}">
                        <div class="discount-type">
                            <img src="/img/badge.png" width="30px" height="30px">
                            <p><span class="bold-text">Discount</span> Benefit up
                                to ${product.price-discount} UAH</p>
                        </div>
                    </c:if>
                    <c:if test="${product.gifts != 'none'}">
                        <div class="discount-type">
                            <img src="/img/shipping.png" width="30px" height="30px">
                            <p><span class="bold-text">Gifts</span> ${product.gifts}</p>
                        </div>
                    </c:if>
                </div>
            </c:if>
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
        <c:forEach items="${descTableKeys}" var="key">
            <h5>${key}</h5>
            <table class="screen-table">
            <col style="width: 50%;" />
            <c:forEach items="${descTable[key]}" var="value">
                    <tr>
                        <td>${value}</td>
                        <td>${descData[value]}</td>
                    </tr>
            </c:forEach>
            </table>
        </c:forEach>
    </section>
    <section id="reviews" class="">
        <h3>Reviews: <span class="reviews-num">${reviews.size()}</span></h3>
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
            <sec:authorize access="isAuthenticated()">
                <div class="feedback-but-cont">
                    <button class="feedback-but" onclick="document.getElementById('feedback').style.display='block'">
                        Leave feedback
                    </button>
                </div>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <div class="feedback-but-cont">
                    <button class="feedback-but" onclick="document.getElementById('registration').style.display='block'">
                        Leave feedback
                    </button>
                </div>
            </sec:authorize>
        </div>
        <div class="rewiews-container">
            <c:forEach items="${reviews}" var="review">
            <div class="review-container">
                <div class="name">
                    <p>${review.user.name}</p>
                    <div class="Stars" style="--rating: ${review.rating};"></div>
                </div>
                <div class="comment-container">
                    <div class="date-check">
                        <p class="date">${review.date}</p>
                        <c:if test="${review.recommended}">
                            <div class="recommend">
                                <img src="/img/check.png" width="16px" height="16px">
                                <p>Recommended</p>
                            </div>
                        </c:if>
                    </div>
                    <p class="rev-text">${review.review}</p>
                </div>
            </div>
            </c:forEach>
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
                        <input type="text" name="name" value="${user.name}" class="rev-input name-input" placeholder="Your name" required>
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
    </section>
</main>
<jsp:include page="footer.jsp"/>
<script src="/js/description.js"></script>
<script src="/js/general.js"></script>
<script>
    window.onload = ev => {
        if (${reviewExists}) {
            alert("Sorry, but you have already sent a review for this product")
        }
    }
</script>
</body>
</html>
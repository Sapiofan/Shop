<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="nav" id="nav">
        <a href="/" id="logo"><p class="logo">SmartShop</p></a>
        <button class="mobile-icon" id="mobile-icon" onclick="document.getElementById('body').style = '--visibility: hidden';">
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
                <c:if test="${isAuthenticated}">
                    <form action="/" method="post">
                        <div class="dropdown-user">
                            <button type="button" class="dropbtn-user user"><img src="img/user.png"
                                                                   style="width: 36px; height: 36px;"></button>
                            <div class="dropdown-content-user">
                                <a href="#">SmartShop money</a>
                                <a href="#">My orders</a>
                                <a><button type="submit" class="logout" name="logout">Logout</button></a>
                            </div>
                        </div>
                    </form>
                </c:if>
                <c:if test="${!isAuthenticated}">
                    <button onclick="document.getElementById('login').style.display='block'"
                            class="user"><img src="img/user.png" style="width: 36px; height: 36px;"></button>
                </c:if>
            </div>
            <div class="sign">
                <c:if test="${isAuthenticated}">
                    <button id="header-heart" onclick="document.getElementById('likes').style.display='block'"
                            class="likes">
                        <img src="img/heart (2).png" style="width: 36px; height: 36px;">
                        <span id="header-heart-n" class="wishlist-count">${favoriteProducts.size()}</span>
                    </button>
                </c:if>
                <c:if test="${!isAuthenticated}">
                    <button onclick="document.getElementById('login').style.display='block'"
                            class="likes">
                        <img src="img/heart (2).png" style="width: 36px; height: 36px;">
                    </button>
                </c:if>
            </div>
            <div class="sign" id="cart">
                <c:if test="${!isAuthenticated}">
                    <nav id="main-nav">
                        <a class="cart-button" href="" onclick="document.getElementById('body').style = '--visibility: auto';
                    document.getElementById('login').style.display='block';">
                            <span class="bag-icon"></span>
                        </a>
                    </nav>
                </c:if>
                <c:if test="${isAuthenticated}">
                    <nav id="main-nav">
                        <a class="cart-button" href=""
                           onclick="document.getElementById('body').style = '--visibility: hidden';">
                            <span id="cart-counter" class="bag-count">${cartProducts.size()}</span>
                            <span class="bag-icon"></span>
                        </a>
                    </nav>
                </c:if>
            </div>
        </div>
    </nav>
    <jsp:include page="registration.jsp"/>
    <jsp:include page="login.jsp"/>
    <jsp:include page="favorite.jsp"/>
    <jsp:include page="asideMenu.jsp"/>
    <c:if test="${isAuthenticated}">
        <jsp:include page="cart.jsp"/>
        <script>
            document.getElementById('body').style = "--visibility: hidden";
        </script>
    </c:if>
</header>
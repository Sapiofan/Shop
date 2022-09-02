<#assign img = "./static/img">
<#setting number_format="computer">
<aside id="menu">
    <div class="menu-logo">
        <p>SmartShop</p>
    </div>
    <div class="menu-choice">
        <#if isAuthenticated??>
            <form action="/" method="post">
                <button type="submit" name="logout" class="user menu-item">
                    <img src="${img}/user.png" style="width: 30px; height: 30px;">
                    <p>Logout</p>
                </button>
            </form>
        </#if>
        <#if !isAuthenticated??>
            <button onclick="document.getElementById('login').style.display='block'"
                    class="user menu-item">
                <img src="${img}/user.png" style="width: 30px; height: 30px;">
                <p>Sign in</p>
            </button>
        </#if>
        <#if isAuthenticated??>
            <button id="header-heart" onclick="document.getElementById('likes').style.display='block'"
                    class="likes menu-item">
                <img src="${img}/heart (2).png" style="width: 30px; height: 30px;">
                <p>Favorites</p>
            </button>
        </#if>
        <#if !isAuthenticated??>
            <button onclick="document.getElementById('login').style.display='block'"
                    class="likes menu-item">
                <img src="${img}/heart (2).png" style="width: 30px; height: 30px;">
                <p>Favorites</p>
            </button>
        </#if>
        <a href="/">
            <div class="menu-item">
                <img src="${img}/home.svg" style="width: 30px; height: 30px;">
                <p>Home</p>
            </div>
        </a>
        <div id="cat-id">
            <div class="menu-item">
                <div class="menu-container">
                    <div class="cat-container" id="cat-container" onclick="openList()">
                        <img src="${img}/application.png" style="width: 30px; height: 30px;">
                        <p class="menu-coll">Categories</p>
                    </div>
                    <ul class="menu-category" id="menu-category">
                        <a href="/phones">Phones</a>
                        <a href="/laptops">Laptops</a>
                        <a href="/watches">Watches</a>
                        <a href="/discounts">Discounts</a>
                        <a href="#">Bestsellers</a>
                        <a href="#">Computers</a>
                        <a href="#">Drones</a>
                    </ul>
                </div>
            </div>
        </div>
        <a href="/about">
            <div class="menu-item">
                <img src="${img}/info.png" style="width: 30px; height: 30px;">
                <p>About</p>
            </div>
        </a>
        <a href="/help">
            <div class="menu-item">
                <img src="${img}/communication.png" style="width: 30px; height: 30px;">
                <p>Help</p>
            </div>
        </a>
        <a href="/contact">
            <div class="menu-item">
                <img src="${img}/messenger.png" style="width: 30px; height: 30px;">
                <p>Contact</p>
            </div>
        </a>
    </div>
</aside>
<div id="menu-curtain"></div>
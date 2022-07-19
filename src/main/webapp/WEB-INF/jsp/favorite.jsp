<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    function ajax() {
        $.ajax({
            type: "GET",
            url: "/cleanWishlist",
            dataType: "json",
            complete: [
                function (response) {
                    $("#wishlist").remove();
                    $("#header-heart-n").remove();
                    htmlTable = `<div id="wishlist" class="wishlist">`;
                    var data = ``;
                    var obj = $.parseJSON(response.responseText);
                    number = obj.length;
                    for (var i = 0; i < obj.length; i++) {
                        data += `<div class="wishlist-product"> <a href="/product/`+obj[i].id+`"><img width="150px" height="150px" src="`+obj[i].image+`"></a>`;
                        data += `<a href="/product/`+obj[i].id+`"><div class="product-description">
                                        <p class="wish-product-name">`+obj[i].name+`</p>
                                        <p class="wish-price">`+obj[i].price+`$</p>
                                        <div class="Stars" style="--rating: `+obj[i].rating+`"></div>
                                    </div>
                                </a>
                                <button class="heart" name="heart">&#10084;</button>
                            </div>`;
                    }
                    htmlTable += data;
                    counter = `<span id="header-heart-n" class="wishlist-count">`+ number +`</span>`;
                    $("#w").append(htmlTable);
                    $("#header-heart").append(counter);
                }
            ]
        });
    }

    function productChanges(url) {
        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",
            complete: [
                function (response) {
                    $("#wishlist").remove();
                    $("#header-heart-n").remove();
                    var productId = parseInt(url.split("/")[2]);
                    var action = url.split("/")[1];
                    $("#"+productId).remove();
                    htmlTable = `<div id="wishlist" class="wishlist">`;
                    var data = ``;
                    var obj = $.parseJSON(response.responseText);
                    number = obj.length;
                    for (var i = 0; i < obj.length; i++) {
                        data += `<div class="wishlist-product"> <a href="/product/`+obj[i].id+`"><img width="150px" height="150px" src="`+obj[i].image+`"></a>`;
                        data += `<a href="/product/`+obj[i].id+`"><div class="product-description">
                                        <p class="wish-product-name">`+obj[i].name+`</p>
                                        <p class="wish-price">`+obj[i].price+`$</p>
                                        <div class="Stars" style="--rating: `+obj[i].rating+`"></div>
                                    </div>
                                </a>
                                <button type="button" onclick="productChanges('/deleteFavorite/`+ obj[i].id +`')" class="heart" name="heart">&#10084;</button>
                            </div>`;
                    }
                    htmlTable += data;
                    counter = `<span id="header-heart-n" class="wishlist-count">`+ number +`</span>`;
                    if(action === 'addProduct'){
                        likeBut = `<button id="`+productId+`" type="button" class="empty-heart" onclick="productChanges('/deleteFavorite/`+productId+`')"><img
                                                    src="img/red-heart.png"
                                                    style="width: 20px; height: 20px; margin-right: 10px;"></button>`;
                        $("#"+productId+"-signs").append(likeBut);
                    }
                    if(action === 'deleteFavorite'){
                        likeBut = `<button id="`+productId+`" type="button" class="empty-heart" onclick="productChanges('/addProduct/`+productId+`')"><img
                                                    src="img/heart.png"
                                                    style="width: 20px; height: 20px; margin-right: 10px;"></button>`;
                        $("#"+productId+"-signs").append(likeBut);
                    }
                    $("#w").append(htmlTable);
                    $("#header-heart").append(counter);
                }
            ]
        });
    }
</script>
<div id="likes" class="modal-likes">
    <form id='favform' class="likes-content" action="/updateFavorites" method="get">
        <input type="hidden" value="${pageContext.servletContext.contextPath}" name="path">
        <div class="container-likes">
            <span onclick="document.getElementById('likes').style.display='none'" class="close">&times;</span>
            <h2>Wishlist</h2>
            <div id="w">

            <div id="wishlist" class="wishlist">
                <c:forEach items="${favoriteProducts}" var="product">
                    <input type="hidden" value="${product.id}" name="productId">
                    <div class="wishlist-product">
                        <a href="/product/${product.id}"><img width="150px" height="150px" src="${product.image}"></a>
                        <a href="/product/${product.id}">
                            <div class="product-description">
                                <p class="wish-product-name">${product.name}</p>
                                <p class="wish-price">${product.price}$</p>
                                <div class="Stars" style="--rating: ${product.rating};"></div>
                            </div>
                        </a>
                        <button type="button" onclick="productChanges('/deleteFavorite/${product.id}')" class="heart" name="heart">&#10084;</button>
                    </div>
                </c:forEach>
            </div>
        </div>
        </div>
        <div class="clearfix-likes">
            <button type="button" onclick="ajax()" class="clean-button" id="clean-button" name="clean">Clear wishlist</button>
        </div>
    </form>
</div>
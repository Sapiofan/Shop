//package com.example.shopjava.cucumber;
//
//import com.example.shopjava.controllers.CommandsTests;
//import com.example.shopjava.entities.user.Favorite;
//import com.example.shopjava.services.FavoriteService;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//
////@CucumberOptions(features = "classpath: cucumber", glue = "com.example.shopjava.controllers")
////@RunWith(Cucumber.class)
//public class FavoritesTests extends CommandsTests {
//
//    @Autowired
//    private FavoriteService favoriteService;
//    private Favorite favorite;
//
//    @Given("user doesn't have products in favorites")
//    public void favoritesIsEmpty() {
//        favorite = favoriteService.getUserProducts(1l);
//        Assertions.assertEquals(0, favorite.getFavoriteProducts().size());
//    }
//
//    @When("user adds product")
//    public void userAddProduct() throws Exception {
//        addFavoriteTest();
//    }
//
//    @Then("at least one product exists in favorites")
//    public void oneProductExistsInFavorites() {
//        favorite = favoriteService.getUserProducts(1l);
//        Assertions.assertEquals(1, favorite.getFavoriteProducts().size());
//    }
//
//    @Given("favorites with one product")
//    public void favoritesContainOneProduct() {
//        favorite = favoriteService.getUserProducts(1l);
//        Assertions.assertEquals(1, favorite.getFavoriteProducts().size());
//    }
//
//    @When("user removes product from favorites")
//    public void userRemovesProductFromFavorites() throws Exception {
//        deleteFavoriteTest();
//    }
//
//    @Then("favorites become empty after deleting 1 product")
//    public void emptyFavoritesAfterDeletingOneProduct() {
//        favorite = favoriteService.getUserProducts(1l);
//        Assertions.assertEquals(0, favorite.getFavoriteProducts().size());
//    }
//
//    @Given("favorites with two products")
//    public void favoritesContainTwoProduct() {
//        favoriteService.addProduct(favorite, 1l);
//        favoriteService.addProduct(favorite, 2l);
//        favorite = favoriteService.getUserProducts(1l);
//        Assertions.assertEquals(2, favorite.getFavoriteProducts().size());
//    }
//
//    @When("user removes products")
//    public void userRemovesAllProductsFromFavorites() throws Exception {
//        cleanWishlistTest();
//    }
//
//    @Then("favorites become empty")
//    public void emptyFavoritesAfterDeletingAllProducts() {
//        favorite = favoriteService.getUserProducts(1l);
//        Assertions.assertEquals(0, favorite.getFavoriteProducts().size());
//    }
//}

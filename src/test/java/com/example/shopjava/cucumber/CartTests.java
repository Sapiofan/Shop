//package com.example.shopjava.cucumber;
//
//import com.example.shopjava.controllers.CommandsTests;
//import com.example.shopjava.entities.user.cart.Cart;
//import com.example.shopjava.services.CartService;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//
//@CucumberOptions(features = "src/test/resources")
//@RunWith(Cucumber.class)
//public class CartTests extends CommandsTests {
//
//    @Autowired
//    private CartService cartService;
//    private Cart cart;
//
//    @Given("user doesn't have products in cart")
//    public void cartIsEmpty() {
//        cart = cartService.getUserProducts(1l);
//        Assertions.assertEquals(0, cart.getCartProducts().size());
//    }
//
//    @When("user adds product to cart")
//    public void userAddProduct() throws Exception {
//        addToCartTest();
//    }
//
//    @Then("at least one product exists in cart")
//    public void oneProductExistsInFavorites() {
//        cart = cartService.getUserProducts(1l);
//        Assertions.assertEquals(1, cart.getCartProducts().size());
//    }
//
//    @Given("cart with one product and its quantity equals to 1")
//    public void cartContainsOneProduct() {
//        cart = cartService.getUserProducts(1l);
//        Assertions.assertEquals(1, cart.getCartProducts().size());
//        Assertions.assertEquals(1, new ArrayList<>(cart.getCartProducts()).get(0).getQuantity());
//    }
//
//    @When("user increases quantity of product in cart")
//    public void increaseQuantityOfProduct() throws Exception {
//        increaseQuantityTest();
//    }
//
//    @Then("cart contains 1 product and its quantity equals to 2")
//    public void oneProductAndTwoQuantity() {
//        cart = cartService.getUserProducts(1l);
//        Assertions.assertEquals(1, cart.getCartProducts().size());
//        Assertions.assertEquals(2, new ArrayList<>(cart.getCartProducts()).get(0).getQuantity());
//    }
//
//    @Given("cart with one product and its quantity equals to 2")
//    public void cartContainsOneProductTwoQuantity() {
//        cart = cartService.getUserProducts(1l);
//        Assertions.assertEquals(1, cart.getCartProducts().size());
//        Assertions.assertEquals(2, new ArrayList<>(cart.getCartProducts()).get(0).getQuantity());
//    }
//
//    @When("user decreases quantity of product in cart")
//    public void decreaseQuantityOfProduct() throws Exception {
//        decreaseQuantityTest();
//    }
//
//    @Then("cart contains 1 product and its quantity equals to 1")
//    public void oneProductAndOneQuantityAfterDecreasing() {
//        cart = cartService.getUserProducts(1l);
//        Assertions.assertEquals(1, cart.getCartProducts().size());
//        Assertions.assertEquals(1, new ArrayList<>(cart.getCartProducts()).get(0).getQuantity());
//    }
//
//    @Given("cart has one product")
//    public void cartContainsOneProductBeforeDeleting() {
//        cart = cartService.getUserProducts(1l);
//        Assertions.assertEquals(1, cart.getCartProducts().size());
//    }
//
//    @When("user removes product")
//    public void deleteProductFromCart() throws Exception {
//        deleteFomCartTest();
//    }
//
//    @Then("cart becomes empty")
//    public void cartIsEmptyAfterDeleting() {
//        cart = cartService.getUserProducts(1l);
//        Assertions.assertEquals(0, cart.getCartProducts().size());
//    }
//}

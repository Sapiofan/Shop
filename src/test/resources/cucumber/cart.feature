#Feature: working with user's cart
#  Scenario: user would like to add product to cart
#    Given user doesn't have products in cart
#    When user adds product to cart
#    Then at least one product exists in cart
#
#  Scenario: user would like to increase quantity of product
#    Given cart with one product and its quantity equals to 1
#    When user increases quantity of product in cart
#    Then cart contains 1 product and its quantity equals to 2
#
#  Scenario: user would like to decrease quantity of product
#    Given cart with one product and its quantity equals to 2
#    When user decreases quantity of product in cart
#    Then cart contains 1 product and its quantity equals to 1
#
#  Scenario: user wants to delete product from cart
#    Given cart has one product
#    When user removes product
#    Then cart becomes empty
#Feature: working with user's favorite products
#  Scenario: user would like to add product to favorites
#    Given user doesn't have products in favorites
#    When user adds product
#    Then at least one product exists in favorites
#
#  Scenario: user wants to delete product from favorites
#    Given favorites with one product
#    When user removes product from favorites
#    Then favorites become empty after deleting 1 product
#
#  Scenario: user would like to remove all favorite products by 1 click
#    Given favorites with two products
#    When user removes products
#    Then favorites become empty
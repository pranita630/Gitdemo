@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  Background: 
  Given I landed on Ecommerce Page

    @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | name  								 		| password 					| productName	|
      | pranitamadhup90@gmail.com | Pranitamadhup@90  | ZARA COAT 3 |
      

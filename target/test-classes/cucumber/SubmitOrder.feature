Feature: To submit an order from Ecom web page

  Background:
    Given The user lands on Ecom landing page

  @Regression
  Scenario Outline: To submit an order for a product in Ecommerce application
    Given The user logs in   with user  <username> and password <password>
    When The user adds the <product> to the cart on page
    And Checksout <product> and submit the order
    Then Verify that the "Thankyou for the order." message is displayed

    Examples:
      | username      | password  | product     |
      | abc@gmail.com | Roopa@123 | ZARA COAT 3 |

Feature:To check the error validation feature of login page
@Error
Scenario Outline:
To verify that the error message is displayed for incorrect login
//Given The user lands on Ecom landing page
Then Verify that the login error  message is displayed for <username> and <password>

Examples:
|username     |password	 |
|abc@gmail.com|Roopsa@123|

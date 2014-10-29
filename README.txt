
ReferralInida usecases developed.

1. Signup:
   Method: POST
   URL :   http://localhost:8080/referralindia/signup
   Example Payload :  {"personalEmail": "xxxxx@gmail.com" , "password" : "xxxx", "firstName" : "xxxxx" , "lastName": "xxxxx"}

   possible outputs
		1.		201,sessionid(randomnumber)
		2.		500,internal server error
		3.		409,conflict(Email already registered)




2. Login:
   Method: GET
   URL   : http://localhost:8080/referralindia/login?username=xxxx&password=xxxxx
   
   
   possible outputs
		1.		200,sessionid(randomnumber)
		2.		404,Invalid user credentials
		3.		500,Internal server error. Please try again later
   

3. Method : DELETE
   URL    : http://localhost:8080/referralindia/logout
   Header value : secretKey : xxxxx  
   
   
   possible outputs
		1.		200,successfuly loggedout
		2.		500,Internal server error
  	
   
   
   
4. ForgotPassword: GET
   URL		:http://localhost:8080/referralindia/forgetPassword/xxx@gmail.com
   
   
   possible outputs
		1.		200,sent reset password link to your mail id
		2.		404,Invalid Mail ID. Please try wiht valid mail id
		3.		500,Internal server error. Please try again later
		
   
   
 5.ResetPassword:
   URL		:http://localhost:8080/referralindia/resetPassword/xxxx  
   
   possible outputs
		1.		201,Password reset succesfully
		2.		404,Invalid password reset request
		3.		500,Internal server error. Please try again later 
   
   


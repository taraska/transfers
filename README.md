# Transfers
task, trasnfers API
you need to install maven

# Notes
1 - You have 4 accounts:
1 - id=1; {balance: 100, "USD"},{balance: 10000, "RUB"}
2 - id=2; {balance: 1000, "USD"},{balance: 30000, "RUB"}
3 - id=3; {balance: 9000, "USD"},
4 - id=4; {balance: 10000, "USD"},


# Run the application:

1 - mvn clean install

2 - java -jar transfers-0.0.1-SNAPSHOT.jar

3 - main URL http://localhost:4567

# Api

1 - One of the ways to transfer money 

	Method : Get
	
	example url : /transfer?from=2&to=3&amount=10&currency=USD
  
2 - One of the ways to transfer money 

	Method : POST
	
	url : /transfer
  
	example body : {
	
					"from": 2,
					
					"to": 3,
					
					"amount": 10,
					
					"currency": "USD"
					
				}
3 - Get history of transfers

	Method : Get
	
	url : /history

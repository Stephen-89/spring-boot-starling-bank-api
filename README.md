# Stephen Hoey - Starling Bank Task

	Starling Bank Test

## APIs

	- GET 		http://localhost:8080/api/v1/accounts-roundup
	- GET 		http://localhost:8080/api/v1/accounts
	- GET 		http://localhost:8080/api/v1/accounts-savings-goals
	- GET		http://localhost:8080/api/v1/accounts-savings-goal
	- PUT		http://localhost:8080/api/v1/accounts-savings-goal-create
	- PUT		http://localhost:8080/api/v1/accounts-savings-goal-update
	- PUT		http://localhost:8080/api/v1/accounts-savings-goal-add-money
	- DELETE	http://localhost:8080/api/v1/accounts-savings-goal-delete
	- GET		http://localhost:8080/api/v1/accounts-transactions

## API Documentation

	- Run the application and go to http://localhost:8080/api/v1/swagger-ui.html to get a list of all available APIs and their parameters.
	
## Postman

	- Import starling_bank_api.postman_collection.json from the project directory into postman to get a list of all available APIs and their parameters.

## Update Access Token

	- Replace {BEARER_TOKEN} in application.properties with a token generated from the Starling Bank developer console.

## Compile the application from the command line

	- mvn clean install

## Run tests from the command line

	- mvn test

## Run the application from the command line

	- mvn spring-boot:run

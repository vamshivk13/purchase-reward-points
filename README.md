# Reward points application

this is a simple spring boot app that calculates reward points based on purchase transactions.

rules:
- 2 points for every dollar over $100
- 1 point for every dollar between $50 and 100

## how to run
just use maven wrapper to build and run it. it uses an in-memory h2 database so no need to setup anything locally.
there's also some mock data loaded on startup by default.

to build and run tests:
`./mvnw clean install`

to start the server:
`./mvnw spring-boot:run`

h2 console is at `/h2-console` if you need to check the db.

## endpoints
- get all rewards -> GET `/api/rewards`
- get rewards for a specific customer -> GET `/api/rewards?customerId={id}`
- get all transactions -> GET `/api/transactions`
- add new transaction -> POST `/api/transactions`

## project structure
- controller: handles the api requests
- service: handles the point calculation logic
- repository: deals with h2 db queries
- exception: basic error handling 
- model/dto: basic data classes for requests and tables

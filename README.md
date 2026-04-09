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

Postman Request and Responses

<img width="1007" height="720" alt="Screenshot 2026-04-10 at 2 11 08 AM" src="https://github.com/user-attachments/assets/a38672e5-a82a-4eb8-ab14-5b0a890b1a40" />

<img width="997" height="467" alt="Screenshot 2026-04-10 at 2 07 47 AM" src="https://github.com/user-attachments/assets/1741efa4-f5b7-47da-958c-a897b7cf048f" />

<img width="1013" height="782" alt="Screenshot 2026-04-10 at 2 01 49 AM" src="https://github.com/user-attachments/assets/3a813519-0203-4e8f-a0c1-8b23688e8a8f" />




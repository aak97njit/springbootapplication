## A sample Spring Boot CRUD Application for Customers.

- **1:** [Create Customer] POST http://localhost:8080/customers

Sample Request Payload:
```json
{
  "firstName": "TESTFN",
  "lastName": "TESTLN",
  "cellNumber": "1234567809",
  "emailId": "test@test.com"
}
```
Sample Response Payload:
```json
{
  "id": "099dba80-0e7f-4555-a005-e14ffa7188e7",
  "firstName": "TESTFN",
  "lastName": "TESTLN",
  "cellNumber": "1234567809",
  "emailId": "test@test.com"
}
```

---
- **2:** [Update Customer] PUT http://localhost:8080/customers/{{id}}
  - Path variable id is customer uuid which is unique to each customer 

Sample Request Payload:
```json
{
  "firstName": "TESTFN Updated",
  "lastName": "TESTLN Updated",
  "cellNumber": "1234567809",
  "emailId": "testupdated@test.com"
}
```
Sample Response Payload:
```json
{
  "id": "099dba80-0e7f-4555-a005-e14ffa7188e7",
  "firstName": "TESTFN Updated",
  "lastName": "TESTLN Updated",
  "cellNumber": "1234567809",
  "emailId": "testupdated@test.com"
}
```

---
- **3:** [Delete Customer] DELETE http://localhost:8080/customers/{{id}}
  - Path variable id is customer uuid which is unique to each customer 

---
- **4:** [ALL Customers] GET http://localhost:8080/customers
  
Sample Response Payload:
```json
[
  {
    "id": "099dba80-0e7f-4555-a005-e14ffa7188e7",
    "firstName": "TESTFN Updated",
    "lastName": "TESTLN Updated",
    "cellNumber": "1234567809",
    "emailId": "testupdated@test.com"
  },
  {
    "id": "d73b8c65-b5e7-438c-a820-85b5e598ea4d",
    "firstName": "TESTFN",
    "lastName": "TESTLN",
    "cellNumber": "2134567809",
    "emailId": "test@test.com"
  }
]
```

---
- **5:** [Total customers count] GET http://localhost:8080/customers/count
  - Returns total number of customers as LONG value.

---
---
- **H2 console:** H2 DB can be accessed on http://localhost:8080/h2-console
  - JDBC URL: jdbc:h2:mem:customerdb
  - User: SA
---
---
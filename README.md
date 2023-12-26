## About This Project
This project designs a simple CRUD REST API for demo purposes, implemented using Spring Boot (Java). There are two entities present in this project, namely Customer and Account.  
<br/>

## Requirements  
API should follow REST API best practice.
* /customer/getalldetails   -- _List of customers (with account details) can be retrieved. Paging required._  
* /customer/getdetailById   -- _Single entity of customer (with account details) can be retrieved._  
* /customer/create          -- _Single customer entity can be created._  
* /customer/update          -- _Single customer entity can be updated._  
* /customer/delete          -- _Single customer entity can be deleted._  
<br/>

## High Level Design
**Data Storage**  
Entity data ( ```Customer``` , ```Account``` ) will be stored in MySQL database.  
<br/>

**Data Access Objects (DAO)**  
To reduce boilerplate code, the repository of the entities are handled by Spring Data JPA.  
<br/>

**Exception Handling**  
Currently, the global exception handler catches two types of exception, ```CustomerNotFoundException``` and general exceptions. ```CustomerNotFoundException``` is a custom exception which will be thrown when the given customer ID does not match any customer ID in the database.  
<br/>


**Available REST API Endpoints**  

| HTTP Method  | URL Path | Description |
| ------------ | -------- | ----------- |
| ```GET```  | /api/customers  | Get all customers |
| ```GET```  | /api/customers/{customerId}  | Get single customer by customer ID |
| ```POST```  | /api/customers  | Create a new customer |
| ```PUT```  | /api/customers  | Update existing customer by customer ID |
| ```DELETE```  | /api/customers/{customerId}  | Delete existing customer by customer ID |
| ```POST``` | /api/customers/generate | Saves a list of hardcoded customers <sub><sup>(For demo purposes only)</sup></sub> |
<br/>

## Detailed Design  
### Customer and Acccount Entity Relationship  
The current code is writen such that the relationship between the ```Customer``` entity and the ```Account``` entity is ***One-To-One uni-directional***, with all cascading operations enabled -- one customer entity will only be associated with one account entity, and vice versa. The deletion of a customer entity will cause the deletion of the associated account.  
<br/>  

**Customer Table**
| Field | Data Type |
| ----- | -------- |
| customer_id ```PK``` | int ```NOT NULL``` |
| phone_number | varchar(45) ```DEFAULT NULL``` |
| email | varchar(45) ```DEFAULT NULL``` |
| address | varchar(45) ```DEFAULT NULL``` |
| account ```FK``` | int ```DEFAULT NULL``` |
<br/>
  
**Account Table**
| Field | Data Type |
| ----- | -------- |
| account_number ```PK``` | int ```NOT NULL``` |
| account_type | varchar(45) ```DEFAULT NULL``` |
| account_name | varchar(45) ```DEFAULT NULL``` |
| account_balance | numeric(19,4) ```DEFAULT NULL``` |
>[!Note]
>The ```customer_id``` is set to auto increment starting from ```1```, while ```account_number``` is set to auto increment starting from ```1000```. The auto generation of schemas and tables by Hibernate is not enabled. Schemas and tables are created manually using SQL scripts in MySQL database. To prepare the database environment, use the SQL scripts from the ```sql``` folder in the root directory.  
<br/>  

**Customer JSON Data Format**
```json
{
  "customerId": 1,
  "phoneNumber": "012-3456789",
  "email": "ali@gmail.com",
  "address": "123, Awesome Town",
  "account": {
                "accountNumber": 1000,
                "accountType": "Free",
                "accountName": "Ali",
                "accountBalance": 556.78
  }
}
```  
<br/>

## REST API Endpoints

### Getting All Customers
```http
GET /api/customers?page={page}&size={size}
```  
To view all customers, the request has to be sent via a HTTP GET method, with optional parameters of page and size. ```page``` corresponds to the page number, and ```size``` corresponds to the page size.   
>[!Note]
>By default, without specifying the ```page``` or ```size``` parameters, the page number would be ```0``` and the page size would be ```10```.  
<br/>

### Getting a Single Customer by Customer ID
```http
GET /api/customers/{customerId}
```
To view a single customer entity, the request has to be sent via a HTTP GET method, with a valid ```customerID``` in the URL path. If the customer ID is not present in the database or is invalid, an error message will be returned.  
<br/>

### Creating a New Customer
```http
POST /api/customers
```
To create a customer, the details of the customer should be passed in the request body, in JSON format. If there are no details given in the request body, a default value of ```null``` will be assigned to the fields which values are not handled by the database. When creating a customer, an account will be created as well, with the details given in the request body. A successful request echos the created customer entity.  
>[!Note]
>The ```account``` field must be present in the request body as a nested object.
<br/>

### Updating a Single Customer by Customer ID
```http
PUT /api/customers
```
To update a customer, the updated customer details should be passed in the request body in JSON format, with a valid ```customerID```. If the customer ID is not found within the database or is invalid, an error message will be returned. A successful request echos the updated customer entity.  
>[!Note]
>The ```account``` field must be present in the request body as a nested object.  
<br/>

### Deleting a Customer  
```http
DELETE /api/customers/{customerId}
```
To delete a customer, send a HTTP DELETE request with a valid ```customerID``` in the URL path. If the customer ID is not present in the database or is invalid, an error message will be returned. A successful delete returns the following string:  
``` Customer with id {customerId} is deleted.```  
<br/>

### Saving List of Hardcoded Customers  
```http
POST /api/customers/generate
```
To save a list of hardcoded customers, send a HTTP POST request to the URL path above. An echo of the list of saved customers will be returned.  
>[!Caution]
>This feature is for demo purposes only.  
<br/>

## Limitations
Authentication is unvailable at the moment. The REST APIs allow any user to perform CRUD operation.

# Mini-Aspire Loan Management System

## Description
The Mini-Aspire Loan Management System is a Java Spring Boot application designed to handle loan applications, repayments, and approvals. It allows authenticated users to create loan requests, view their approved loans and repayments, and provides admin functionality for loan approval management.

## Features
1. **Loan Application:**
    - Authenticated users can submit loan requests specifying the amount and term.
    - Scheduled repayments are generated automatically.

2. **Admin Loan Approval:**
    - Admins can review and approve or reject loan requests.
    - Loan status updates automatically based on approval or rejection.

3. **Customer Loan Views:**
    - Authenticated customers can view their approved loans.
    - They can also view their repayment details, including future and past repayments.

4. **Loan Repayments:**
    - Customers can make repayments on their approved loans.
    - Repayment status updates automatically.

## Assumptions
Because of lack of open discussion, few assumptions were made to move ahead. However, in a team, these issues could be easily solved.  
Assumptions:  
- Username of user is unique
- Customer doesn't need to strictly pay all the terms. He could pay more in less terms.
- Customer can pay any amount within the constraints: Amount Paid in each installment >= Weekly Installment decided initially and if at end we have remaining amount < Weekly Installment decided initially then he can pay only the remaining amount.
- We don't remove the remaining repayments which are pending but just close out the loan by marking as `PAID`

## Versions used
Please make sure to have the following version on your machine for the project to run smoothly  
- Java: 11
- OpenJDK: 11.0.20
- Apache Maven: 3.6.3
- Additionally, if required, I have used Ubuntu 20.04.6 LTS as my OS on an Intel i5 processor 

## Steps to Run
1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd mini-aspire`
3. Build the project: `mvn clean install`
4. Run the Spring Boot application: `mvn spring-boot:run`

## How to use the program
Base URL of program is: `http://localhost:8080`
1. There are 4 users hard coded in the internal volatile database. You can use them for your testing. One is: `{"username": "Aspire", "password": "aspire123", "email": "admin@aspire.com"}`
2. First login using the username and password on the endpoint: `/mini-aspire/v1/auth/login`. You'll get an accessToken, use this for all steps ahead as Bearer Token.
3. You can create a loan by using the endpoint: `/mini-aspire/v1/loan/application`. Sample request:
```json
{
    "amountRequired": 10000.0,
    "loanTerm": 3
}
```
4. One of the many ways to see loan and other details is through this endpoint: `/mini-aspire/v1/loan/consolidation/user-screen/repayments?userId=2`. This will give you all the details of repayments, loans and your own user. Notice the userId as path parameter.
```json
[
    {
        "id": 1,
        "dueDate": "2023-08-28",
        "amountPaid": 0.0,
        "status": "PENDING",
        "loan": {
            "id": 1,
            "amountRequired": 10000.0,
            "amountPaid": 0.0,
            "loanTerm": 3,
            "applicationDate": "2023-08-21",
            "status": "PENDING",
            "user": {
                "id": 1,
                "username": "Sachin",
                "password": "sachin123",
                "email": "sachindarade11g@gmail.com",
                "admin": true
            }
        }
    },
    {
        "id": 2,
        "dueDate": "2023-09-04",
        "amountPaid": 0.0,
        "status": "PENDING",
        "loan": {
            "id": 1,
            "amountRequired": 10000.0,
            "amountPaid": 0.0,
            "loanTerm": 3,
            "applicationDate": "2023-08-21",
            "status": "PENDING",
            "user": {
                "id": 1,
                "username": "Sachin",
                "password": "sachin123",
                "email": "sachindarade11g@gmail.com",
                "admin": true
            }
        }
    },
    {
        "id": 3,
        "dueDate": "2023-09-11",
        "amountPaid": 0.0,
        "status": "PENDING",
        "loan": {
            "id": 1,
            "amountRequired": 10000.0,
            "amountPaid": 0.0,
            "loanTerm": 3,
            "applicationDate": "2023-08-21",
            "status": "PENDING",
            "user": {
                "id": 1,
                "username": "Sachin",
                "password": "sachin123",
                "email": "sachindarade11g@gmail.com",
                "admin": true
            }
        }
    }
]
```
5. You can approve the loan yourself as of now using API: `mini-aspire/v1/loan/approval`. Sample request is:
```json
{
   "loanId": 1,
   "approved": true
}
```
6. You can pay for a loan repayment using this: `/mini-aspire/v1/loan/repayment`. Request looks like this:
```json
{
    "loanId": 1,
    "amount": 3333.34
}
```
6. Doing this 3 times in total (payments) will mark the loan as **PAID**.
7. Please follow the steps as given with values to avoid any confusion in request/response.

## Future Roadmaps
Due to time constraint, full-fledged features were not possible to build. Below enhancements could be made given more time:  
1. Better error handling of edge cases
2. When repayments are done before time, we currently aren't handling it properly. This could be improved.
3. Exhaustive swagger documentation. Inline comments enhancement.
4. Role based access of resources. Integrating this with JWT.
5. Password can be encrypted and stored in Database.
6. Scalable production grade database like PostgreSQL could be integrated.
7. Pagination and sorting in GET APIs
8. Detailed exception throwing with proper messages.

---

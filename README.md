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

# Bank ATM CLI

This is a project to simulate the experience of using an ATM machine via the console. This is built using Java and MySQL (switched to PostgreSQL) but it can work with any relational database once you install the dependency.

Before you run the application, make you have two tables named 'customers' and 'accounts' with their respective schemas below:

#### For Customers

```sql
CREATE TABLE customers (
    id NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    pin VARCHAR(5) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL
)
```

#### For Accounts

```sql
CREATE TABLE accounts (
    id NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customerId INT NOT NULL,
    accountType ENUM('SAVINGS', 'CURRENT') NOT NULL,
    balance DECIMAL(17,2) NOT NULL

    FOREIGN KEY (customerID)
    REFERENCES customers(id)
)
```

Once you clone the repository, you follow the command below to get started:

```shell
mvn clean install
mvn compile exec:java
```

If you are using a different database in your local machine, you can click [here](https://mvnrepository.com/) to visit the Maven repository, search the database you are using and go through the documentation so that you can integrate it into the project.

If you have any questions, suggestions, objectives, or reviews about the projects, you can:

- Send an email to [chigbovcm2020@gmail.com](mailto:chigbovcm2020@gmail.com)
- Send a DM via [X](https://x.com/chivicks_hazard) or [LinkedIn](https://linkedin.com/in/victorchigbo)

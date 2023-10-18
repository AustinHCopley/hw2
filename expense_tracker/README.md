# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTrackerApp
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

## Functionality

ExpenseTrackerApp consists of *model*, *view*, and *controller* components

The *model* keeps track of the data, the *view* displays the data as a table and also displays buttons and text boxes which are also interactible as part of the *controller*

Data is added into the program in the form of a *Transaction* consisting of: *amount*, *category*, and *timestamp*

The view shows the user a table with columns: serial, Amount, Category, Date
The controller components consist of a label/input pair for the category and amount, and an addTransaction button

Input data is checked with InputValidation which ensures that the data is properly formatted before it is added to the model 
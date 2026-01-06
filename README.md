 FoodOrder Project

FoodOrder is a simple food ordering system developed using Java and designed according to Object-Oriented Programming (OOP) principles.
The project simulates a basic ordering workflow where customers can browse a menu, select items, and complete payments using different payment methods.

 Project Design & Architecture

The system is built with a modular and object-oriented structure. Class relationships and responsibilities are clearly defined and represented using a UML Class Diagram.

The following OOP concepts are applied throughout the project:

🔹 Inheritance

Customer is derived from the User class.

cashPayment and creditCardPayment extend the abstract Payment class.

This structure promotes code reuse and logical hierarchy.

🔹 Polymorphism

Different payment methods are handled through the Payment superclass.

The system can process payments without depending on the concrete payment type.

This allows new payment methods to be added easily.

🔹 Encapsulation

Sensitive user information such as username, password, address, and phone is encapsulated using private fields.

Access to these fields is controlled through getter and setter methods.

🔹 Association

The App class manages multiple Customer and MenuItem objects.

A Customer interacts with menu items while placing an order.

The Restaurant class is associated with its menu items.

These relationships model real-world interactions within the system.

🔹 Dependency

The App class depends on the Payment class only during the payment process.

Payment objects are not stored permanently, ensuring loose coupling.

 Testing

Unit testing is implemented using JUnit to verify the correctness of the core components.
Each main class has a corresponding test class:

src/test/java
 └─ com.google.FoodOrder
     ├─ AppTest
     ├─ CustomerTest
     ├─ MenuItemTest
     ├─ PaymentTest
     └─ UserTest


These tests help ensure reliability and correctness of the system logic.

🛠 Technologies Used

Java

Maven

JUnit

UML (Class Diagram)

🎯 Project Goal

The main goal of this project is to demonstrate the practical use of Object-Oriented Programming concepts, proper class design, UML modeling, and unit testing within a real-world inspired application.

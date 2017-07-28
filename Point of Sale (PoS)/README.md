Point of Sale (PoS) Systems ~ Thomas Yamakaitis
=====
## About
The following repository contains multiple versions of the same or similar PoS systems. These are just GUI versions for testing purposes. They are meant to assist developers at the Compuflex Corporation to test screen mapping and management software(s). These application(s) are, simply put, Graphical representations of actual PoS systems, and they mimic common behavior(s) in such systems.

## Versions in this repository
 - PoS (Java - JavaFX) v1.3 ~ This is probably one of my best implementations of this project. It was created with Java / JavaFX using Eclipse IDE and Scene Builder 2.0 software.
 - PoS (Java - Swing) v2.0 ~ This is another Java version of the project using a different "flavor". It was created with Java / Swing / AWT using Eclipse IDE and it's "WindowBuilder" plugin.
 - MockPoS (JavaScript) ~ This implementation was done using web technologies including JavaScript, JQuery v3.2.1, Bootstrap v3.3.7, Font Awesome 4.7.0, HTML5, and CSS.

## 1. The Cart
The cart stores item(s) that are to be processed into a transaction, it generates a total price based on the individual price(s) of the item(s) in the cart. (i.e. for every 1 item priced at $1.00 in the cart, $1.00 will be added to the total price). You can view the cart by clicking the "Cart" tab at the top left.

### a.) Adding an item to the cart
Click the "Add" button at the top left of the window (which is to the left of the "Clear" button [left of the "Remove" button in the JavaScript version]) to add an item to the cart, the item will be randomly generated based on hard-coded names and prices in the source code. After clicking "Add", the item will be displayed in a graphical table, and the value of "Total" at the bottom left of the window will be incremented.

### b.) Clearing the cart
Click the "Clear" button at the top left of the window (which is to the right of the "Add" button) to clear all item(s) from the cart. This will clear all item(s) from the cart, cancel the transaction, and set the value of "Total" at the bottom left to "$0.00", effectively, resetting every value to default.

### c.) (ONLY IN JAVASCRIPT VERSION) Removing an item from the cart
To remove a specific item from the cart, simply click the checkbox next to that item in the table and it will remove all instances of that item. [This operation was only done in the JavaScript version for the sake of simplicity in the Java version(s).]

## 2. The Transaction Record
The transaction record stores transaction(s) which contain information about a cart that was processed through the system at the Point of Sale. It holds the total price of the cart, the amount of currency tendered, and the amount of change returned as well as the time that the transaction took place and the user who processed it. You can view the transaction record by clicking the "Trans" tab at the top left, to the right of the "Cart" tab.

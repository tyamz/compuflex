# Compuflex - PoS - Java (Swing)
## Point of Sale (PoS) version 2.0
This is a new and improved sample Point of Sale (PoS) System made with Java Swing / AWT for the Compuflex Corporation to help test various screen mapping and management software(s).

## News
 This is version 2.0, this may seem very similar to 1.1, however v2.0 was done from scratch because attempting to write over and in the code for 1.1 would have been much more difficult considering it was my first time using Swing / AWT. Of course, certain code snippets and naming convention(s) were reused, but overall it was basically a complete revamp. The code might not be EXTREMELY clean, but it is simpler to work with in an IDE like Eclipse, in my opinion. Besides, I finished it much faster than version 1.x.

## File "Structure"
 - [PoS.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20Swing)/PoS.java) ~ main file that comprises the entire PoS system.
 - [Cart.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20Swing)/Cart.java) ~ class file for the cart which holds the product(s).
 - [Item.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20Swing)/Item.java) ~ class file for the items(s) which are added to the cart.
 - [TransactionRecord.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20Swing)/TransactionRecord.java) ~ class file that holds and prints a log of transaction(s).
 - [Transaction.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20Swing)/Transaction.java) ~ class file for the transaction(s) that go in the log(s).
 - [User.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20Swing)/User.java) ~ class file for user(s).
 - [Settings.java](/PoS%20(Java%20Swing)/Settings.java) ~ class file for application settings.
 - [SettingsWindow.java](/PoS%20(Java%20Swing)/SettingsWindow.java) ~ class file for the pop-up settings menu.
 - [PayWindow.java](/PoS%20(Java%20Swing)/PayWindow.java) ~ class file for the pop-up pay window.
 - [default.properties](/PoS%20(Java%20Swing)/default.properties) ~ default Java properties file for the application.
 - [Compuflex_PoS_Swing_2.0.log](/PoS%20(Java%20Swing)/Compuflex_PoS_Swing_2.0.log) ~ log file for the application.
 - [Old%20Version(s)](/PoS%20(Java%20Swing)/Old%20Version(s)/) ~ Old Version(s) of Compuflex (PoS) Swing.

## Try it?
 - [Compuflex_PoS_Swing_2.0.jar](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20Swing)/Compuflex_PoS_Swing_2.0.jar) ~ an executable version of this whole package.

## Changes
### (July 24th, 2017 - 4:33 PM ~ Thomas Yamakaitis) v1.1 to v2.0
 - Added a settings menu.
 - Moved pay window to a separate class file.
 - Settings include:
    - ability to switch display type of the user name.
    - ability to toggle the pop-up window for the pay button (no more holding CTRL).
 - Remade the Cart, Item (formerly Product), Transaction, and TransactionRecord (formerly Trans) classes.
 - Removed the TableBuilder class (will look into bringing it back as an interface, if at all necessary).
 - Added Java Properties file to save settings to next session.

# Compuflex - PoS - Java (JavaFX)
## Point of Sale (PoS) version 1.3
This is a sample Point of Sale (PoS) System made with JavaFX for the Compuflex Corporation to help test various screen mapping and management software(s).

## File "Structure"
 - [LayoutController.java](/Point%20Of%20Sale%20(PoS)/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/LayoutController.java) ~ controller for the main "Layout.fxml" file.
 - [Layout.fxml](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/Layout.fxml) ~ layout file that defines the layout of the application.
 - [Main.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/Main.java) ~ runs the entire application.
 - [Cart.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/Cart.java) ~ class file that stores CartItem(s) in an ObservableList<>.
 - [CartItem.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/CartItem.java) ~ class file for the item(s) that go in the cart.
 - [Transaction.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/Transaction.java) ~ class file for creating Transaction(s) (a record of the value(s) processed in a cart).
 - [TransactionRecord.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/TransactionRecord.java) ~ class file that stores Transaction(s) in an ObservableList<>.
 - [PayWindowLayoutController.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/PayWindowLayoutController.java) ~ controller for the pay window layout, "PayWindowLayout.fxml" file.
 - [PayWindowLayout.fxml](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/PayWindowLayout.fxml) ~ layout file that defines the layout for the pay window.
 - [application.css](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/application.css) ~ CSS file that defines style(s) for the application.
 - [Settings.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/Settings.java) ~ class file the loads, stores, and saves settings to and from the properties file(s).
 - [SettingsLayoutController.java](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/SettingsLayoutController.java) ~ controller for the settings window layout, "SettingsLayout.fxml" file.
 - [SettingsLayout.fxml](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/SettingsLayout.fxml) ~ layout file that defines the layout for the settings window.
 - [default.properties](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/default.properties) ~ default Java properties file for the application.
 - [Compuflex_PoS_JavaFX_1.3.properties](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/Compuflex_PoS_JavaFX_1.3.properties) ~ main Java properties file for the application.

## Try it?
 - [Compuflex_PoS_JavaFX_1.3.jar](/Point%20Of%20Sale%20(PoS)/PoS%20(Java%20-%20JavaFX)/Compuflex_PoS_JavaFX_1.3.jar) ~ an executable version of this whole package.

## Changes
### (July 27th, 2017 - 11:45 AM ~ Thomas Yamakaitis) v1.2 to v1.3
 - Automatically generates the properties file(s), if they do not exist.
 - Settings class created to manage the properties and settings a bit better.
 - Overall updates to increase efficiency and readability of the code.

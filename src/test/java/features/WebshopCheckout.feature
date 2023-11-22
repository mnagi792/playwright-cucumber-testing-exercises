Feature: Einkaufen auf einer E-Commerce-Website

  Background:
  Given der Benutzer ist mit gültigen Anmeldedaten angemeldet
    |standard_user|secret_sauce|

  Scenario: Produkt zum Warenkorb hinzufügen
    Given der Nutzer betrachtet ein Produkt "Fleece Jacket"
    When der Benutzer auf die Schaltfläche Add to Cart für das Produkt "fleece-jacket" klickt
    Then Dann sollte das Produkt "Fleece Jacket" zum Warenkorb des Benutzers hinzugefügt werden

  Scenario: Checkout-Prozess durchführen
    Given the user has products in their cart
    When der Benutzer hat Produkte im Warenkorb
    And auf die Schaltfläche Checkout klickt
    And seine Adresse und seinen Namen eingibt
      | firstName | Tom     |
      | lastName  | Jerry   |
      | postCode  | 190230  |
    And den Checkout bestätigt
    And die Bestellung überprüft und den Checkout abschließt
    Then sollte die Bestellung des Benutzers erfolgreich verarbeitet werden



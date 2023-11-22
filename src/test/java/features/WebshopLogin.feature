Feature: Login auf Webshop

  Scenario: : Anmeldung mit gültigen Anmeldedaten
    Given Benutzer ist auf der Sauce-Labs Seite
    When der Benutzer gültige Anmeldedaten eingibt
      |standard_user|secret_sauce|
    Then sollte sich der Benutzer erfolgreich anmelden können und eine neue Seite öffnen


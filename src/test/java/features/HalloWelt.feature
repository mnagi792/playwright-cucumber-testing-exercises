Feature: Wikipedia Suche

  Scenario: Suche nach "Hallo Welt!" Artikel
    Given der Benutzer ist auf der Wikipedia-Startseite
    When der Benutzer nach "Hallo Welt!" sucht
    Then sollte der Artikelheader "Hallo Welt!" angezeigt werden

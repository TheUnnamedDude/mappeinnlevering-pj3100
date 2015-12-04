#Forutsetninger for å kjøre besvarelsen
Prosjektet er satt opp med maven, så det kreves for å bygge prosjektet.
Maven vil legge til mysql connector jar-filen i det ferdige produktet så
man trenger ikke å installere mysql driveren. Tilkoblingsinfo er hardkodet
i MusikkHandler.java, se kommentar der for info.

#Kommentarer
Innloggingsinformasjon til databasen burde ha en konfigurasjonsfil eller
ved bruk av standarder som JNDI(har ikke lest meg opp på det). Jeg burde ha
skrevet JUnit tester.

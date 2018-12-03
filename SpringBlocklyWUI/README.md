SpringBlocklyWUI
================
## English
To make an executable jar use: 
	
	./gradlew build
	
And then to launch: 

	./gradlew bootRun
	
If you are using maven:

	mvn clean package
	
to launch using maven: 

	mvn spring boot:run

If you want to launch the User interface using the jar you can use: 

	java -jar target/spring-blockly-wui-X.X.X.jar

Dependencies
------------
To run this component you need the following packages:

* google-blockly - The version included in the current project has some small changes. If you want to use a newe version of blockly, make sure to apply these changes again.
* google-closure
* syntax-higlight

Java 8 is required (unfortunately it doesn't work with java 9)


## Italian

Per creare un jar eseguibile lanciare: 

	./gradlew build

ed eseguirlo con: 

	./gradlew bootRun

Se invece si usa maven, il progetto si può buildare usando: 

	mvn clean package

e per lanciarlo:

	mvn spring boot:run

Infine se si ha il jar a disposizione lo si può eseguire l'applicazione con: 

	java -jar target/spring-blockly-wui-0.1.1.jar

Dipendendenze
-------------
Per essere esegutio correttamente servono i seguenti pacchetti:

* google-blockly - La versione utilizzata dal progetto presenta alcune piccole modifiche rispetto all'originale, quindi se si dovesse sostituire tale libreria, accertarsi di riprodurre le stesse modifiche.
* google-closure
* syntax-higlight

Per funzionare correttamente richiede Java 8 (non funziona con Java 9)



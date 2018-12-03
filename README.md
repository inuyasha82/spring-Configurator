SpringBlocklyWUI & ClassToBlocklyWUI
====================================
English
=======

The purpose of the project is to create a tool that given a java package/class it generates a user friendly interface to create XML Configurration files for spring using a visual interface based on google-blockly.  It is composed by two components: the user interface (SpringBlocklyWUI) and the crawler (ClassToBlocklyWUI).

SpringBlocklyWUI
----------------

Is a web based simple user interface, it let the user to use the blocks created by ClassToBlocklyWUI. More details about its usage are in the project Readme file.

ClassToBlocklyWUI
-----------------

This project creates, starting from a library in the classpath, the source files needed by blockly to visualize the blocks used by the user to generate the XML configuratiopn files.

How to use it are in the project Readme.

Requirements and limitations
----------------------------
In order to work both projects need the following installed packages:

* [Maven] (https://maven.apache.org/)
* [Java 8] (http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)

Both projects are create using STS (Sprin Tool Suite), and IDE based on eclipse.

Currently it works only with classes that are defined into the project as maven dependencies. More info are on the project Readme.

Next steps
----------

* Add more documentation
* Add list of issues tasks


Italian
=======
Lo scopo del progetto é creare uno strumento che dato un package/classe java generat una interfaccia utente user-friendly per creare i file xml di configurazione per spring usando  blocchi visuali basati su google-blockly . Si compone di due componenti: l'interfaccia utente (SpringBlocklyWUI) e il crawler (ClassToBlocklyWUI)

SpringBlocklyWUI
-------------------
Si tratta dell'interfaccia web, che permette di utilizzare i blocchi generati da ClassToBlocklyWUI.

Maggiori dettagli sull'utilizzo sono nel relativo Readme.

ClassToBlocklyWUI
----------

Questo progetto si occupa di generare a partire da una libreria nel classpath i sorgenti necessari a blockly per generare i file di configurazioen di spring in formato xml.

Dettagli sull'utilizzo sono nel relativo Readme.

Requisiti e limitazioni
-----------------------

Per funzionare entrambi i progetti hanno bisogno dei seguenti pacchetti installati:

* [Maven] (https://maven.apache.org/)
* [Java 8] (http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)

Entrambi i progetti sono stati sviluppati utilizzando STS (Spring Tool Suite), un ambiente di sviluppo basato su eclipse, quindi probabilmente qualsiasi versione di Eclipse dovrebbe essere sufficiente per modificare il progetto.

Al momento permette di aggiungere solamente classi che sono state definito all'interno del package come dipendenza maven. Per maggiori info consultare il Readme del progetto ClassToBlocklyWUI.

Next steps
----------

* Aggiungere documentazione
* Aggiungere lista issues/tasks

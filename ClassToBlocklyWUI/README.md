Spring Class to Blockly WUI
===========================
English
=======
Prerequisites
-------------
To compile this project you need maven installed

Compile
-------
To compile launch:

	mvn clean install
	
It will create a jar file in the target folder:

	class-to-blockly-wui-VER-jar-with-dependencies.jar
	
Usage
-----	

To launch:

1. With a single class:

	java  -jar class-to-blockly-wui-X.X.X-jar-with-dependencies.jar -c it.uniroma1.lcl.csi.core.actions.PipelinedAction
		
2. With a package:
	
	java  -jar class-to-blockly-wui-X.X.X-jar-with-dependencies.jar -p it.uniroma1.lcl.csi.core.actions

3. You can also customize the destination folder:

	java  -jar class-to-blockly-wui-0.0.1-jar-with-dependencies.jar -c it.uniroma1.lcl.csi.core.actions.PipelinedAction -d destinationFolder

The output, if you haven't specified a destination folder, it will be stored in a folder with the name: projectTIMESTAMP.
The structure of the output folder is:

* blocks -> Contains the files with blocks definition
* generators -> It contains the folder crawler and another folder with the block generator source
* html -> Contains the html code of blocks that will be added to the workspace.
* documentation -> It contains the html automatic documentation for the generated blocks

Automatic Update procedure
--------------------------
The update.sh script is automating the following steps:

1. Source file creation using the crawler
2. Copy them in the  google-blockly folder
3. Launch of the blockly blocks update procedure
4. And finally it create a jar with a minimal user interface to use the blocks.

This script works well on Unix Like systems.

Before launching it you must update the following variables used in the script:

* `JARPATH` It contains the path of ClassToBlocklyWUI jar (if you used maven to compile the project, it can be left as it is)
* `JARNAME` The jar file name.
* `DESTINATIONFOLDER` Where the script stores the output files
* `WEBINTERFACEPATH` Where the project SprinbBlocklyWUI is saved
* `POMPATH` The pom.xml path for SpringBlocklyWUI project

Once configuret the above variables, is possible to launch the script using the following syntax:

```shell
./update.sh -p nomepackage
```
If we want to analyze a full package.

```shell
./update.sh -c nomeclasse
```
If we want to analyze a single class

Add classes to be analyzed
-----------
WIth the current version only classes that are added in the build path during the compilation phase can be analyzed. Since the dependencies are handled using maven, we just need to to add the library in the pom.xml file. Then launch the command:  

```shell
mvn clean package
```

In the future we will try to support adding packages/classes to analyze at runtime.
 
	
Italian
=======
Prerequisiti
------------
Per compilare il progetto è necessario avare maven installato 

Compilare
---------

Per compilare eseguire: 

	mvn clean install
	
Verrà generato un file jar nella cartella target con nome: 

	class-to-blockly-wui-VER-jar-with-dependencies.jar

Lanciare
--------
Per lanciare:

1. Con una sola classe:
	
	java  -jar class-to-blockly-wui-0.0.1-jar-with-dependencies.jar -c it.uniroma1.lcl.csi.core.actions.PipelinedAction

2. Con un package: 

	java  -jar class-to-blockly-wui-0.0.1-jar-with-dependencies.jar -p it.uniroma1.lcl.csi.core.actions
	
3. Si puó anche specificare una cartella di destinazione personalizzata: 
	
	java  -jar class-to-blockly-wui-0.0.1-jar-with-dependencies.jar -c it.uniroma1.lcl.csi.core.actions.PipelinedAction -d destinationFolder

L'output generato, se non si é specificata una cartella destinazione, verrá messo in una cartella chiamata projectTIMESTAMP.
La cartella al suo interno é cosí strutturata: 

* blocks -> Contiene il file con la definizione dei blocchi
* generators -> Contiene la cartella crawler e il file con il codice del generatore del blocco
* html -> Contiene invece il codice html dei blocchi che verranno aggiunti al workspace.

Procedura automatica di aggiornamento
-------------------------------------

Tale procedura automatizza i seguenti passi:

1. Crea i file sorgente di blockly mediante il crawler
2. Li copia nei corrispondenti path della libreria google-blockly 
3. Lancia la procedura di aggiornamento dei files in google-blockly
4. Crea un file jar, che conterrá una interfaccia web minimale per interagire con i blocchi appena creati

Lo script funziona correttamente solo su sistemi UnixLike (Linux, Unix, MacOs). 

Prima di lanciarlo occorre opportunamente modificare alcune variabili utilizzate dallo script: 
* `JARPATH` Contiene il path dello jar di ClassToBlocklyWUI (normalmente se si é utilizzato maven si puo lasciare il valore di default)
* `JARNAME` Il nome dello jar, cambiare solo se si é rinomninato il file.
* `DESTINATIONFOLDER` La cartella dove vogliamo salvare i file temporanei generati dallo script
* `WEBINTERFACEPATH` Il percorso della cartella resources del progetto SpringBlocklyWUI
* `POMPATH` Il percorso del file pom.xml per il progetto SpringBlocklyWUI.

Una volta configurati questi valori possiamo lanciare lo script, usando la seguente sintassi:

```shell
./update.sh -p nomepackage
```
Se si vuole lanciare l'analisi di un intero package.

```shell
./update.sh -c nomeclasse
```
Se si vuole lanciare l'analisi di una singola classe.

Aggiungere le classi da analizzare
-----------
La versione corrente permette di analizzare solo classi che sono state inserite nel buildpath in fase di compilazione, ed essendo le dipendenze gestite via Maven, occorre prima aggiungere la libreria che si vuole analizzare nel file pom.xml, dopo di che lanciare il comando 

```shell
mvn clean package
```

Per aggiornare le dipendenze. Questo dovrebbe essere sufficiente per poter analizzare le classi che si desiderano.
In futuro si cercherá di rendere possibile l'aggiunta delle classi dinamicamente, senza dover compilare ogni volta.


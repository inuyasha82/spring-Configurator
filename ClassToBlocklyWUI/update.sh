#!/bin/bash
set -e
#PROJECTROOT=/Users/gualandri/Projects/Universita/AFC/CSI-Web-UI
PROJECTROOT=`cd .. && pwd`
JARPATH=./target/
JARNAME=class-to-blockly-wui-1.0.0-jar-with-dependencies.jar
FULLJARPATH=$JARPATH$JARNAME
DESTINATIONFOLDER=scriptOutput
WEBINTERFACEPATH=$PROJECTROOT/SpringBlocklyWUI/src/main/resources
BLOCKLYSUBPATH=/static/js/google-blockly
TEMPLATESUBPATH=templates/
POMPATH=$PROJECTROOT/SpringBlocklyWUI/

usage(){
	echo "Usage:"
	echo "$0 -p packagename"
	echo "or:"
	echo "$0 -c classname"
}
#
# Steps:
# 
#
#

if [ $# -eq 2 ]; then
	echo $FULLJARPATH
	if [ -d "$DESTINATIONFOLDER" ]; then
		echo "Deleting folder"
		rm -rf $DESTINATIONFOLDER
	fi 
	java -jar $FULLJARPATH $1 $2 -d $DESTINATIONFOLDER
	echo "Return value: $?" 
	if [ $? -eq "0" ]; then
		echo "Ok"
		cp $DESTINATIONFOLDER/blocks/* $WEBINTERFACEPATH$BLOCKLYSUBPATH/blocks/
		cp $DESTINATIONFOLDER/generators/crawler/* $WEBINTERFACEPATH$BLOCKLYSUBPATH/generators/crawler/
		cp $DESTINATIONFOLDER/html/* $WEBINTERFACEPATH/$TEMPLATESUBPATH/
		if [ -d "$WEBINTERFACE/static/documentation" ]; then
			rm -rf $WEBINTERFACE/static/documentation
		fi
		cp -r $DESTINATIONFOLDER/documentation $WEBINTERFACEPATH/static/
		cp $WEBINTERFACEPATH/templates/static_blocks/blocks/* $WEBINTERFACEPATH$BLOCKLYSUBPATH/blocks/
		cp $WEBINTERFACEPATH/templates/static_blocks/generators/* $WEBINTERFACEPATH$BLOCKLYSUBPATH/generators/crawler/
		cd $WEBINTERFACEPATH$BLOCKLYSUBPATH
		python build.py
		cd $POMPATH
		mvn clean package
	else
		echo "Error $?"
	fi
	echo $?
else
	usage
fi

#cd $0


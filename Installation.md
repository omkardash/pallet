#Instructions for installing and running Pallet.

# Introduction #

Pallet is a maven project, versioned in an svn repository and developed in java.  Therefore you will need:
  * [svn client](http://subversion.tigris.org/).
  * [maven2](http://maven.apache.org/).
  * [java 6](http://java.sun.com/javase/downloads/index.jsp).

If you are running Ubuntu, all of these are available through the package manager.


# Checkout the code #
To checkout the code from svn see instruction [here](http://code.google.com/p/pallet/source/checkout)

# Before building the code #
Pallet depends on the Mallet library.  Mallet is not currently hosted in a maven repository.  So [download it](http://pallet.googlecode.com/files/mallet.jar) and then install into your local maven repository:

`mm@ubuntu:~/pkg/pallet$ mvn install:install-file -DgroupId=umass.mallet -DartifactId=mallet -Dversion=2.0 -Dpackaging=jar -Dfile=/home/mm/pkg/mallet/dist/mallet.jar`

Similarly [download all](http://pallet.googlecode.com/files/External_jars.rar) External
jars and then install into your local maven repository:

# Building the code #
To build the code, go into the root Pallet folder and type:

`mm@ubuntu:~/pkg/pallet$ mvn clean install`
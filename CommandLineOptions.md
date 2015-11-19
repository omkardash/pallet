# Introduction #
The BlackBookSimulator need different types of command line arguments in order to execute it.
# Details #
### Train the classifier ###
TRAIN <training data set> <training algorithm> <output file name> <classification property >
```
for eg

TRAIN C:\Users\pralabh\workspace2\Mallet1\Montery2RDF.rdf NaiveBayes C:\Users\pralabh
\workspace2\Mallet1\MonteryTrainer2 http://blackbook.com/terms#STAT_EVENT
```
  * C:\Users\pralabh\workspace2\Mallet1\Montery2RDF.rdf : The training data
  * NaiveBayes: Algorithm which is used to train the classifier.
  * C:\Users\pralabh\workspace2\Mallet1\MonteryTrainer2: The location where trained classifier will be stored.
  * http://blackbook.com/terms#STAT_EVENT : The classification Property on the basis of which classification will be done.

Each input must be seperated by one space.

### Incremental Training ###
INC\_TRAIN <training data set> <Filename where the classifier stored> <Output file name> <classification Property>
```
for eg 

Inc_TRAIN C:\Users\pralabh\workspace2\Mallet1\Montery2RDF.rdf C:\User\old_classifier c:\User\new_classifier http://blackbook.com/terms#STAT_EVENT
```
  * C:\Users\pralabh\workspace2\Mallet1\Montery2RDF.rdf : New Training Data.
  * C:\User\old\_classifier: The location where old classifier is stored.
  * c:\User\new\_classifier: The location where new classifier will be stored.
  * http://blackbook.com/terms#STAT_EVENT: Classification Property.

### Classifying Stand Alone ###
CLASSIFY\_SD <data which is to be classified> <trainer object file name> <validation source> <Classification Property>
```
for eg 
CLASSIFY_SD C:\Users\pralabh\workspace2\Mallet\Montery2RDF.rdf C:\Users\pralabh\workspace2\Mallet1\MonteryTrainer2 C:\Users\pralabh\workspace2\Mallet1\Montery2RDF.rdf http://blackbook.com/terms#STAT_EVENT
```
  * C:\Users\pralabh\workspace2\Mallet\Montery2RDF.rdf : Data which is to be classified
  * C:\Users\pralabh\workspace2\Mallet1\MonteryTrainer2 : Place where the already trained classifier is stored.
  * C:\Users\pralabh\workspace2\Mallet1\Montery2RDF.rdf : The Validation model which contains then classification of the unclassified data.(This is to compare the results of Mallet classification with actual classification)
  * http://blackbook.com/terms#STAT_EVENT: The classification Property on the basis of which classification occurred.

### Classifying with Current Trained Data ###
CLASSIFY <Training data Set> <test data set> <Training algorithm> <Trainer Destination File> <Validation data src> <Classification Property>

  * Note: In the option ,training the classifier,storing it into the file ,classifying the test data and validating the final classified data all these things happening simultaneously.

### Classifying with Incremental Trained Data ###
CLASSIFY\_INC <test data set> <trained data Set> <trainer source file> <output file> <validating data source> <classification Property>

  * Note: In this option the already trained classifier is incrementally trained ,stored in a new position, classifying the test data and validating it with validating data source.

### Important Points ###

  * If your test data set and trained data set contains multiple files or folders then you can use them by seperating them with ,.  for eg
    * If your test data set contains two files from different location then specified them as path\_of\_filename1,path\_of\_filename2(C:/user/abc.rdf,d:/user/cba.edf)

  * User can use above stated option simultaneously. For e.g. if user want to use train and classify stand alone option simultaneously then
```
TRAIN C:\Users\pralabh\workspace2\Mallet1\Montery2RDF.rdf NaiveBayes C:\Users\pralabh
\workspace2\Mallet1\MonteryTrainer2 http://blackbook.com/terms#STAT_EVENT@CLASSIFY_SD 
C:\Users\pralabh\workspace2\Mallet\Montery2RDF.rdf C:\Users\pralabh\workspace2\Mallet1
\MonteryTrainer2 C:\Users\pralabh\workspace2\Mallet1\Montery2RDF.rdf http://blackbook.com
/terms#STAT_EVENT
```

**Note : The two options are seperated by @ symbol.**

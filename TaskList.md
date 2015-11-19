#Our task list from which we coordinate with one another and generate release notes.

# Reduce Complexity #
Any task listed here should take 1 hour to no more than 2 weeks work.  If a task listed will take more than 2 weeks work then it should be broken into sub-tasks that take less time.

# Tasks #
(%) - means the task needs to be broken down into several sub-tasks
## Not started (In order of priority) ##

  * Documentation of the entity extraction functionality of the Mallet
  * Sample Application using entity extraction of Mallet
  * Retrieve entire data source model, within Blackbook - Lance
  * Create Generalized Training Algorithm which takes an algorithm name and RDF data to train on - Sharath.
  * Wrap the generalized training algorithm as a Blackbook algorithm - Lance.
    * RDF -> Pipe -> MalletP
    * MalletTrain.train(algorithmName,malletP)
    * return wrapClassifier(Classifier)
  * Create generalized mallet classifier in Blackbook which takes a Classifier and the RDF data to be classified -Lance.
    * rdf -> pipe
    * classify
    * jena add statement and reified statement for provenance
  * Create generalized incremental training algorithm as a Blackbook algorithm which takes a Classifier and RDF to be trained on and return the Classifier - Lance.


  * The unit testing of all the pipes.(%)
  * Data Classification:
    * Types - includes feature vector, instance labels.
      * Alphabet
      * ArrayListSequence
      * ArraySequence
      * AugmentableFeatureVector
      * ChainedInstanceIterator
      * CrossValidationIterator
      * DenseMatrix
      * DenseVector
      * Dirichlet
      * Dirichlet.Estimator
      * Dirichlet.MethodOfMomentsEstimator
      * ExpGain
      * ExpGain.Factory
      * FeatureConjunction
      * FeatureConjunction.List
      * FeatureCounter
      * FeatureCounts
      * FeatureCounts.Factory
      * FeatureInducer
      * FeatureSelection
      * FeatureSelector
      * FeatureSequence
      * FeatureSequenceWithBigrams
      * FeatureVector
      * FeatureVectorSequence
      * GainRatio
      * GradientGain
      * GradientGain.Factory
      * HashedSparseVector
      * IDSorter
      * IndexedSparseVector
      * InfoGain
      * InfoGain.Factory
      * Instance
      * InstanceList
      * InstanceListTUI
      * InvertedIndex
      * KLGain
      * Label
      * LabelAlphabet
      * Labelings
      * Labels
      * LabelSequence
      * LabelsSequence
      * LabelVector
      * Matrix2
      * Matrixn
      * MatrixOps
      * Minkowski
      * MultiInstanceList
      * Multinomial
      * Multinomial.Estimator
      * Multinomial.LaplaceEstimator
      * Multinomial.Logged
      * Multinomial.MAPEstimator
      * Multinomial.MEstimator
      * Multinomial.MLEstimator
      * NormalizedDotProductMetric
      * PagedInstanceList
      * PartiallyRankedFeatureVector
      * PerLabelFeatureCounts
      * PerLabelFeatureCounts.Factory
      * PerLabelInfoGain
      * PerLabelInfoGain.Factory
      * RankedFeatureVector
      * ROCData
      * SequencePair
      * SequencePairAlignment
      * SingleInstanceIterator
      * SparseMatrixn
      * SparseVector
      * StringEditFeatureVectorSequence
      * StringEditVector
      * StringKernel
      * Token
      * TokenSequence
    * Mallet Utils
      * Property List
    * Decision Trees.
      * planned extension to support pruning.
    * Balanced Winnow.
    * AdaBoost?.
    * Bootstrap Aggregating (bagging)
    * Machine Learning Ensemble.
    * Rank Maximum Entropy
    * Winnow
    * Confidence Predicting.
    * Maximum Entropy models with Generalized Expectation Criteria.
    * Data classification unit tests
    * Real Application to show the Data Import and Classification functionality.
    * C4.5 Decision Tree
      * Code Review.
      * Documentation and Tutorials.
    * Creation of Confidence Vector - Sharath.
    * Completion of Jena Simulator - Sharath.
    * Pallet Functionality Test:
      * unctionalities to be tested:*** Training of data set.
      * Incremental Training.
      * Classification.
      * Validation (Answer Model).
      * pplication that tests these functionalities should have following modules:**
      * Parser(Data Import): Fetches the training/testing data.
```
if (data in rdf format)  {
converts to Instance List;
} else {
    1. converts the text data to rdf format.// we have huge data set of 20-news-channel which are not in rdf format.
    2. converts the intermediate data to instance list.
```
      * Trainer:
```
    <Get training data> --> <Convert to Mallet Instance List> --> <Choose Algorithm and train the data> --> <Persist Classifier>
```
      * Incremental Trainer:
```
    <Get Training data> --> <Convert to Mallet Instance List> --> <Get the persisted Classifier that needs to be incrementally trained> --> <Persist the new classifier(use the same filename as before)>
```
      * Classification:
```
    <Get Specified classifier(persisted)> --> <Get data set to be classified(as instance list)> --> <Classify the data> --> <if validation enabled, then validate data> --> <Persist o/p>
```
      * Validation:
> > > > Works in two modes:
        * In conjunction with Classification:
```
    <Data is classified in the classification step, Get the validation data set> -> <Valiadate the classified data set against validation data set> --> <Print/Persist O/p>
```
        * Independent Existence:
```
    <Get the test data to be validated> --> <Get the validation data> --> <Validate the test data against validation data set> --> <Persist/Print o/p>
```
      * Command Line Option parser:

> > > Options to be supported:
        * Trainer options;
          * --Train-dir <Set of directories from where the data is to be fetched> <Training Algo to be used>
```
         There can be sequence of such commands:
         eg: --Train-dir <directory1 ... directoryn> <algo1 to be used> --Train-dir <Dir1...DirM> <Algo2 to be used> .......
        Useful in testing same algo on different data sets and same data set on different algos.
```
          * --Train-files <File1.... fileN> 

&lt;Algo1&gt;

 --Train-files <File1...FileM> 

&lt;Algo2&gt;


        * Test Options:
          * --test-dir <dir1....dirN> <classifier1 to be used for testing>.
          * --test-file <file1....fileN> <Classifier1 to be used>
> > > > > similar to trainer options multiple commands can be concatenated.
        * Validation Options:
          * --Validate-data <Validattion data dir/file list> - to be used in conjunction with the classification.
          * --Validate-data <Test data o/p that needs to be validated> <Validation data set> -- Independent existence.
        * Incremental Train:
          * -IncTrain-dir <dir list> <Classifier to be used(will be persisted during training)>
          * -IncTrain-file <file list> <Classifier to be used>
```
All commands can be concatenated as described above
```





## Currently being worked on ##

  * Understanding the entity extraction functionality of the Mallet.
  * Understanding the HMM and CRF algorithms on which Mallet's entity extraction is based.
  * Maximum Entropy
    * Code Review - Currently have paused this work and have moved onto Decision Tree
    * Documentation and Tutorials
  * Convert the classification data(data without labels) into Mallet Instances


## Completed ##
  * The following task has been completed on the pipes(mentioned below)
    * Understanding the Algorithms
    * Documenting them
    * Small sample source code for each pipe.
    * Documenting everything in the form of small tutorials
    * Source code and Documentation Reviewed
      * [Filename2CharSequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [CharSequenceArray2TokenSequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Csv2Array](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Array2FeatureVector](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Csv2FeatureVector](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [TargetStringToFeatures](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [SimpleTaggerSentence2TokenSequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [SimpleTokenizer](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Pipeutils](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [StringAddNewLineDelimiter](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [CharSequenceRemoveUUEncodedBlocks](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [StringList2FeatureSequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [FeatureSequenceConvolution](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [FeatureVectorConjunction](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Input2CharSequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [CharSequenceReplace](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [CharSequenceRemoveHTML](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [CharSequence2TokenSequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [TokenSequenceRemoveStopwords](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [MakeAmpersandXMLFriendly](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [TokenSequence2Feature Sequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Target2Label](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [FeatureSequence2AugmentableFeatureVector](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [AugmentableFeatureVectorLogScale](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [CharSequence2charngrams](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [CharSequenceLowecase](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [CharSubsequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [LineGroupString2TokenSequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [CharSequence2TokenSequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [TokenSequenceLowercase](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [TokenSequenceRemoveStopwords](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Target2Label](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [FeatureSequence2FeatureVector](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [PrintInputAndTarget](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Token Sequence Remove Non Alpha](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Token Sequence NGrams](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [SGML2 Token Sequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Target 2 Feature Sequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Target 2 Label Sequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Token Sequence 2 Feature Vector Sequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Token Sequence Feature Parse Feature String](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Token Sequence Match Data And Target](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Token Sequence 2FeatureSequenceWithBigrams](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Target Remeber Last Label](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Source Location 2 Token Sequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Add Classifier Token Predictions](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Add Classifier Token Predictions . Token Classifiers](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Augmentable Feature Vector Add Conjunctions](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Classification 2 Confidence Predicting Feature Vector](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Directory 2 File Iterator](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Feature Count Pipe](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Feature Value String 2 Feature Vector](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Feature Vector Sequence 2 Feature Vectors](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Filter Empty Feature Vectors](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Instance List Trim Features By Count](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Noop](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Pipe](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Print Input](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Print Token Sequence Features](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Save Data In Source](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Selective SGML 2 Token Sequence](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Simple Tagger Sentence 2 String Tokenization](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Token 2 Feature Vector](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Token Sequence 2 Token Instances](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)
      * [Token Sequence Parse Feature String](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial)




  * Naive Bayes Algorithm
    * Understanding the algorithm
    * Reviewing Mallet code to understand the implementation.
    * Documentation of the mallet implementation of the algorithm and its tutorials.
  * Maximum Entropy
    * Understanding the algorithm.
  * Decision Tree
    * Code review and Tutorial.
  * Application to Import RDF Data into Mallet Instances
    * RDF->Jena Model->Inspect each resource
    * list all statements for that resource where the object is literal
    * check if label is already exist using supplied predicate argument.
    * Output in Mallet Format
  * Convert the classification data(data without labels) into Mallet Instances
    * Use the trained pipe
    * Create the Instance List having Target value provided by the trained Pipe.
  * Convert the final output of the classification into Reified RDF showing the confidence value for each label
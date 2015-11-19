Before proceeding any further, this tutorial is not intended to describe the algorithms, it just aims at exposing the MALLET interfaces.

# Introduction #
MALLET supports rich set of Document Classification Algorithms:
  * [DocClassification#Naïve\_Bayes](.md).
  * [DocClassification#Maximum\_Entropy](.md).
  * [DocClassification#C4.5 Decision Trees](.md).
  * [DocClassification#Decision\_trees](.md)
  * Balanced Winnow.
  * AdaBoost.
  * Bootstrap Aggregating (bagging)
  * Machine Learning Ensemble.
  * Rank Maximum Entropy
  * Winnow
  * Confidence Predicting.
  * Maximum Entropy models with Generalized Expectation Criteria.

Every Classifier is implemented as a Trainer and Classifier, and performs classification only on the instances processed with the pipe (import data pipe) associated with this classifier. Instances are generally Feature Vectors. Trainer is responsible for training data and the Classifier performs the classification based on the trained data.

Every Trainer consists train () method, which is used to train the data. A call to this train() method instantiates the Classifier which can classify the data based on its training.
Further, all Trainers and Classifiers can be found in cc.mallet.classify. Also, each trainer and classifier is implemented as an object in the MALLET. All the trainer mentioned above extends _ClassifierTrainer_ class and the trainers mentioned extends Classifier respectively, both of which can be found at **cc.mallet.classify**

Given, the general introduction on Classifier, let us proceed to explore each of the Algorithms.

> 
---

> 
---


# Naïve Bayes #
As with every classifier Naïve Bayes is also implemented as a trainer and classifier. Naïve Bayes assumes the Conditional Model to classify data.

> p(Classification|Data) = p(Data|Classification)p(Classification)/p(Data)
Further, Naïve Bayes also assumes the conditional independence between the data.
> p(Data|Classification) = p(d1,d2,..dn | Classification)
> p(d1,d2,...dn | Classification) = p(d1|Classification)p(d2|Classification)

Naïve Bayes Trainer and classifier are under the nomenclature NaiveBayesTrainer and NaiveBayesClassifier respectively and can be found at **cc.mallet.classify**.


Before, getting into details of Naïve Bayes Classifier, Let us look at a sample code for NaiveBayes classification (should also work for all the other classification algorithms).


```
class AppNaiveBayes
{
           // Look at Import Data Document for BuildPipe and Instance Lists description
        public static BuildPipe buildPipe ()
	{
	    String [][][] trainingdata = new String [][][] {
	    {{ "on the plains of africa the lions roar",
	    "in swahili ngoma means to dance",
	    "nelson mandela became president of south africa",
	    "the saraha dessert is expanding"}, {"africa"}},

            {{ "panda bears eat bamboo",
	    "china's one child policy has resulted in a surplus of boys",
	    "tigers live in the jungle"}, {"asia"}},

	    {{ "home of kangaroos",
	    "Autralian's for beer - Foster",
	    "Steve Irvin is a herpetologist"}, {"australia"}}};
		
	    BuildPipe bpipe = new BuildPipe ();		
	    Pattern tokenPattern = Pattern.compile("[\\p{L}\\p{N}_]+");
	    try {
		bpipe.CreatePipe( new Input2CharSequence("UTF-8"),
			new CharSequence2TokenSequence(tokenPattern), 
			true, 
			new TokenSequenceRemoveStopwords(true, true), 
			new TokenSequence2FeatureSequence(), 
			new Target2Label(), 
			new FeatureSequence2FeatureVector(), 
			false);
	    } catch (IOException e ) {};
		
	    for (int i = 0; i < 3; i++) 
            {
		try {
		  bpipe.addThruPipe (
                       new ArrayIterator (trainingdata[i][0],trainingdata[i][1][0]));
		} catch (Exception e) { }
	    }
	    return bpipe;
	}

	public static void main (String Args []) {				
		// 1. Create the instance list
		BuildPipe bpipe = AppNaiveBayes.buildPipe();
		// 2. Create NaiveBayes Trainer
		NaiveBayesTrainer trainer = new NaiveBayesTrainer ();
		// 3. Train the trainer with the data
		NaiveBayes cl = trainer.train(bpipe.GetInstanceList());
		// 4. Classify the sample data based on the trained data
  	        Classification c = cl.classify("nelson Mandela never eats lion");
 	        // 5. Validation of correctness
	        System.out.println (“Class Name – “ + 
					c.getLabeling().getBestLabel());
        }
}
```


Above source code shows the steps involved in the MALLET document classification.
```
1.Instance list of training data is created using the BuildPipe object.
2.Instantiation of NaiveBayesTrainer.
3.Training the classifier with the instance list created. 
As you can see the train method returns an instance of NaiveBayes Classifier.
(more detailed explanation in Instance method section of this document).
4.Classification of new data on the trained data is achieved using the method: NaiveBayes.classify (Object obj).
(more detailed explanation in Instance method section of this document).
```

#Note - look at the Import Data document for more info on Importing Data, Instance Lists, and Iterators.

## NaïveBayes Trainer ##

```
Extends    - ClassifierTrainer<NaiveBayes>
Implements - ClassifierTrainer.ByInstanceIncrements<NaiveBayes>, 
	     Boostable,AlphabetCarrying and java.io.Serializable.
```

### Constructors: ###

  * [NaiveBayesTrainer ()](http://mallet.cs.umass.edu/api/)

  * [NaiveBayesTrainer (Pipe instancePipe)](http://mallet.cs.umass.edu/api/)

  * [NaiveBayesTrainer (NaiveBayes initialClassifier)](http://mallet.cs.umass.edu/api/)
> > Used to initialize trainer with an existing Classifier.
> > When initialized with an existing classifier, a trainer need not train on the same training data

### Public Instance Methods: ###

  * [NaiveBayes getClassifier ()](http://mallet.cs.umass.edu/api/)
> > Returns instance of the NaiveBayes classifier.

  * [NaiveBayes train (InstanceList trainingList)](http://mallet.cs.umass.edu/api/)
> > Trains the naivebayes trainer with the training list passed as an Instance List of
> > Feature Vector, all previous internal states are reset when train returns.

  * [NaiveBayes trainIncremental (InstanceList trainingList)](http://mallet.cs.umass.edu/api/)
> > Same as the train method but does not resets the internal state.
> > Incrementally adds the count of the training data and Training data is lnstance list of
> > Feature Vector.

  * [NaiveBayes trainIncremental(Instance instance)](http://mallet.cs.umass.edu/api/)
> > Same as previous incremental trainer with training data as an Instance.

  * [NaiveBayesTrainer setDocLengthNormalization(double d)](http://mallet.cs.umass.edu/api/)
> > Sets Documentation Length Normalization.

  * [Multinomial.Estimator getFeatureMultinomialEstimator()](http://mallet.cs.umass.edu/api/)
> > Returns the multinomial Estimator that is used for document classification.

  * [Multinomial.Estimator getPriorMultinomialEstimator()](http://mallet.cs.umass.edu/api/)

  * [NaiveBayesTrainer setPriorMultinomialEstimator(Multinomial.Estimator me)](http://mallet.cs.umass.edu/api/)

  * [Alphabet getAlphabet()](http://mallet.cs.umass.edu/api/)

  * [Alphabet[](http://mallet.cs.umass.edu/api/) getAlphabets()]

  * [alphabetsMatch(AlphabetCarrying object)](http://mallet.cs.umass.edu/api/)
> > Alphabet maps integers and objects where the mapping in each direction is efficient.
> > (Will be explained in detail under the section Alphabet.

### Code snippet for incremental training: ###

```
   // Create Feature Vector Instance List as shown in the code above
   // Refer Import Data document for more info
   // Assume Instance list is created by previous code snippet

   NaiveBayesTrainer trainer = new NaiveBayesTrainer ();
   NaiveBayes classifier = trainer.train(pipe.GetInstanceList());

		
   Classification c = classifier.classify("nelson mandela never eats lions");
   System.out.println("Class Name - " + c.getLabeling().getBestLabel());

		
   c = classifier.classify("Steve irvin stays in australia");
   System.out.println("Class Name - " + c.getLabeling().getBestLabel());

		
   String [][][] incTrainingdata = new String [][][] {
   {{"Tiger woods is golf master",
     "The Niagara Falls are voluminous waterfalls on the Niagara River",
     "Christopher Columbus discovered American Continents"}, {"America"}}};

		
   pipe.addThruPipe (
    new ArrayIterator (incTrainingdata[0][0],incTrainingdata[0][1][0]));		

	 
   classifier = trainer.trainIncremental(pipe.GetInstanceList());
   c = classifier.classify("Woods has won American golf championships");
   System.out.println("Class Name - " + c.getLabeling().getBestLabel());	

```


### Code Snippet for Reusing the existing Trained Classifier ###

```
   NaiveBayesTrainer reuse_trainer = new NaiveBayesTrainer (classifier);
   NaiveBayes reuse_classifier = trainer.getClassifier();
   c = reuse_classifier.classify("Woods has won American golf championships");
   System.out.println("Class Name - " + c.getLabeling().getBestLabel());
```

All Trainers works the same way!!!

## NaiveBayes Classifier ##

```
public class NaiveBayes
extends Classifier
implements java.io.Serializable
```

### Constructors ###

```
NaiveBayes(Pipe instancePipe, Multinomial.Logged prior, Multinomial.Logged[] classIndex2FeatureProb) 

NaiveBayes(Pipe dataPipe, Multinomial prior, Multinomial[] classIndex2FeatureProb)
```

### Instance Methods ###

```
 * Classification classify(Instance instance) 

 * double dataLogLikelihood(InstanceList ilist)

 * Multinomial.Logged[] getMultinomials()

 * Multinomial.Logged getPriors()

 * double labelLogLikelihood(InstanceList ilist)
 
 *  void printWords(int numToPrint)
```


> 
---

> 
---



# Decision trees #


I accept it is kind of nagging, but I have to remind it to you, even Decision tree is implemented as a Trainer and a Classifier.
MALLET calls its decision tree trainer as  [DecisionTreeTrainer](http://mallet.cs.umass.edu/api/) and classifier as [DecisionTree](http://mallet.cs.umass.edu/api/).

## Overview of Decision Trees ##

Decision Tree Learning uses a decision tree as a predictive model which maps observations about an item to conclusions about the item's target value. In these tree structures, leaves represent classifications and branches represent conjunctions of features that lead to those classifications.<Thanks to wiki>

Main Principle: Entropy at the root of the tree is maximum and the goal is to reduce the entropy for every child node created.

In MALLET internal node in the decision tree looks at whether the vector contains a feature and if it does, sends it to the right child of that node (if the feature is absent it sends it to the left child). If the node is a leaf it labels the vector with a
label given to that leaf.

More info on Decision trees and decision tree learning can be found at:
  * [Decision Trees](http://www.decisiontrees.net)
  * [Decision Tree Learning](http://en.wikipedia.org/wiki/Decision_tree_learning)
  * [Building Decision Trees](http://onlamp.com/pub/a/python/2006/02/09/ai_decision_trees.html)
  * [AI and decision trees](http://www.cs.ubc.ca/~poole/aibook/html/ArtInt_165.html)

Limitations of Decision Tree Learning in MALLET:
  * Does not create pure leaves.
  * Does not support pruning.

As kedar Bellare(MALLET research team member) says MALLET is more tailored towards [NaiveBayes](http://mallet.cs.umass.edu/api/) and [DecisionTree MaxEnt](http://mallet.cs.umass.edu/api/) models. Other classifiers have better implementations in [Weka](http://www.cs.waikato.ac.nz/ml/weka/).

[info on decision trees will be updated as and when I learn "more" about it ;)](more.md)

## Decision Tree Trainer ##

> public class [DecisionTreeTrainer](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html) extends [ClassifierTrainer](http://mallet.cs.umass.edu/api/cc/mallet/classify/ClassifierTrainer.html)<[DecisionTree](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTree.html)> implements [Boostable](http://mallet.cs.umass.edu/api/cc/mallet/classify/Boostable.html)

### Constructors ###
  * [DecisionTreeTrainer()](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html)
> Default constructor with depth 5

  * [DecisionTreeTrainer(int maxDepth)](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html)
> Constructor specifying the tree depth explicitly

### Public Methods ###
  * [DecisionTree train(InstanceList trainingList)](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html)
> Creates a Decision tree classifier from a set of trained data, look at Data Import tutorials for more information on Instance List.
> #### Parameters: ####
    1. trainingList - Instance of [InstanceList](http://mallet.cs.umass.edu/api/cc/mallet/types/InstanceList.html), Training data in form of instance lists ((look at [Data Import tutorials](http://code.google.com/p/pallet/wiki/Tutorial_Data_Import) for [InstanceList](http://mallet.cs.umass.edu/api/cc/mallet/types/InstanceList.html))
> #### Return value: ####
    1. [DecisionTree](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTree.html) - Instance of Decision Tree Classifier

  * [DecisionTree getClassifier()](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html)
> Returns an instance of Decision tree classifier if trainer was trained with the training data else returns null.
> #### Return value: ####
    1. [DecisionTree](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTree.html) - Instance of Decision Tree Classifier

  * [boolean 	isFinishedTraining()](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html)
> Returns true if the classifier is already trained with training data else true, generally useful in conjunction with [DecisionTree 	getClassifier()](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html)
> #### Return value: ####
    1. True if Training is finished else false

  * [DecisionTreeTrainer setMaxDepth (int maxDepth)](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html)
> Sets the maximum depth of the decision tree.
> #### Parameters: ####
    1. maxDepth - integer type, Max depth of the decision tree to be constructed.
> #### Return value: ####
    1. Instance of [[DecisionTree Trainer](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html).

  * [DecisionTreeTrainer setMinInfoGainSplit(double m)](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html)
> Sets the minimum info-gain, default is .001.look at the above decision tree links for more information on info-gain
> #### Parameters: ####
    1. m - double type, Minimum info gain to be used with decision tree construction.
> #### Return value: ####
    1. Instance of [DecisionTree Trainer](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.html).

### Protected Methods ###
  * [void splitTree(DecisionTree.Node node, FeatureSelection selectedFeatures, int depth)](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTreeTrainer.htmlprotected)
> Splits the node(attributes) into two childs, one with the feature another without feature.
> #### Parameters: ####
    1. node - [DecisionTree.Node](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTree.html) type (explained below), Instance of node.
    1. selectedFeatures - [FeatureSelection](http://mallet.cs.umass.edu/api/cc/mallet/types/FeatureSelection.html) type, Instance of [FeatureSelection](http://mallet.cs.umass.edu/api/cc/mallet/types/FeatureSelection.html) (look at [Data Import tutorials](http://code.google.com/p/pallet/wiki/Tutorial_Data_Import) for [FeatureSelection](http://mallet.cs.umass.edu/api/cc/mallet/types/FeatureSelection.html))
    1. depth - integer type, Maximum depth of the tree.

## Decision Tree Classifier ##
### Constructors ###
  * [DecisionTree](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTree.html)([Pipe](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Pipe.html) instancePipe, [DecisionTree.Node](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTree.html) root)
> Initializes Decision tree classifier.
> #### Parameters: ####
    1. Instance pipe used for training (look at [Import Data tutorials](http://code.google.com/p/pallet/wiki/Tutorial_Data_Import) for Pipe.)
    1. Root node of the decision tree constructed by trainer([DecisionTree.Node](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTree.html) is explained below).

### Public Methods ###
  * [Classification classify(Instance instance)](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTree.html)
> Classifies the data given as an instance of [Instance](http://mallet.cs.umass.edu/api/index.html?cc/mallet/types/Instance.html) based on the trained data.
> #### Parameters: ####
    1. instance - Instance of [Instance](http://mallet.cs.umass.edu/api/index.html?cc/mallet/types/Instance.html) type, Data to be classified
> #### Return value: ####
    1. Returns instance of [Classification](http://mallet.cs.umass.edu/api/cc/mallet/classify/Classification.html)(explained below).

  * [DecisionTree.Node getRoot()](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTree.html)
> #### Return value: ####
    1. Returns root node of the tree constructed, Type - [DecisionTree.Node](http://mallet.cs.umass.edu/api/cc/mallet/classify/DecisionTree.html) (Explained Below)



> 
---

> 
---


# Maximum Entropy #

Again, as every other Classifier Maximum Entropy is also implemented as Trainer and Classifier. Nomenclature used in MALLET for Maximum Entropy:
  * Trainer - [MaxEntTrainer](http://mallet.cs.umass.edu/api/)
  * Classifier - [MaxEnt](http://mallet.cs.umass.edu/api/)

Both [MaxEntTrainer](http://mallet.cs.umass.edu/api/) and [MaxEnt](http://mallet.cs.umass.edu/api/) could be found in [cc.mallet.classify](http://mallet.cs.umass.edu/api/)


## Overview of Maximum Entropy ##
Maximum entropy is a general technique for estimating probability distributions from data.
It works by identifying all possible states in which the system can exists and all parameters involved in the constraints. It is not assumed in which particular state the system is in (This uncertainty is known as entropy)and instead deal with probability that each state is being occupied (assumes distribution to be uniform). For more info refer [using Maximum Entropy for Document classification](http://www.kamalnigam.com/papers/maxent-ijcaiws99.pdf)

// More info will be provided for max entropy algorithm


## MaxEntTrainer ##
```
extends ClassifierTrainer<MaxEnt>
implements ClassifierTrainer.ByOptimization<MaxEnt>, Boostable, java.io.Serializable
```

### Constructors ###
  * [MaxEntTrainer ()](http://mallet.cs.umass.edu/api/)

  * [MaxEntTrainer (MaxEnt theClassifierToTrain)](http://mallet.cs.umass.edu/api/)
> > Constructs a MaxEnt Trainer using a trained classifier as initial values

  * [MaxEntTrainer (double gaussianPriorVariance)](http://mallet.cs.umass.edu/api/)
> > Constructs a Max Ent Trainer by explicitly specifying the Guassian Prior Value
> > default value is 1.0

### Instance Methods ###
  * [MaxEnt train (InstanceList trainingSet)](http://mallet.cs.umass.edu/api/)
> > Creates MaxEnt classifier from a set of Training Data.
> > Returns MaxEnt classifier as trained on the trainingList.

  * [MaxEnt train (InstanceList trainingSet, int numIterations)](http://mallet.cs.umass.edu/api/)
> > Creates MaxEnt classifier from a set of Training Data.
> > Number of Iterations are explicitly mentioned, default value is Integer.MAX\_VALUE
> > Returns MaxEnt classifier as trained on the trainingList.

  * [MaxEnt getClassifier ()](http://mallet.cs.umass.edu/api/)
> > Returns MaxEnt Classifier

  * [void setClassifier (MaxEnt theClassifierToTrain)](http://mallet.cs.umass.edu/api/)
> > Creates MaxEnt Trainer from an existing Trained Classifier.
> > Use getClassifier() to get the trainer created not train ()
> > Creating a classifier using setClassifier () is similar to Constructing the classifier using the trained Classifier

  * [Optimizable getOptimizable ()](http://mallet.cs.umass.edu/api/)

  * [MaxEntOptimizableByLabelLikelihood getOptimizable (InstanceList trainingSet)](http://mallet.cs.umass.edu/api/)

  * [MaxEntOptimizableByLabelLikelihood getOptimizable (InstanceList trainingSet, MaxEnt initialClassifier)](http://mallet.cs.umass.edu/api/)

  * [Optimizer getOptimizer ()](http://mallet.cs.umass.edu/api/)

  * [Optimizer getOptimizer (InstanceList trainingSet)](http://mallet.cs.umass.edu/api/)

  * [MaxEntTrainer setNumIterations (int i)](http://mallet.cs.umass.edu/api/)

  * [int getIteration ()](http://mallet.cs.umass.edu/api/)

  * [MaxEntTrainer setGaussianPriorVariance (double gaussianPriorVariance)](http://mallet.cs.umass.edu/api/)

  * [MaxEntTrainer setL1Weight(double l1Weight)](http://mallet.cs.umass.edu/api/)

  * 



## MaxEnt ##
```
public class MaxEnt
extends Classifier
implements java.io.Serializable
```

### Constructors ###

```
MaxEnt (Pipe dataPipe,	double[] parameters)

MaxEnt(Pipe dataPipe, double[] parameters, FeatureSelection featureSelection) 

MaxEnt (Pipe dataPipe, double[] parameters, FeatureSelection featureSelection, FeatureSelection[] perClassFeatureSelection)

MaxEnt (Pipe dataPipe, double[] parameters,
			FeatureSelection[] perClassFeatureSelection)
```


### Instance Methods ###
```
 * Classification classify(Instance instance)

 * void getClassificationScores(Instance instance, double[] scores)

 * void getClassificationScoresWithTemperature(Instance instance, double temperature, double[] scores)

 * int getDefaultFeatureIndex()           

 * FeatureSelection getFeatureSelection()

 * int getNumParameters()

 * static int getNumParameters(Pipe instancePipe)

 * double[] getParameters()

 * FeatureSelection[] getPerClassFeatureSelection()

 * void getUnnormalizedClassificationScores(Instance instance, double[] scores)           
          Outputs human-readable description of classifier (e.g., list of weights, decision tree) to System.out

 * void print(java.io.PrintStream out)          

 * void printExtremeFeatures(java.io.PrintWriter out, int num)           

 * void printRank(java.io.PrintWriter out)           

 * void setDefaultFeatureIndex(int defaultFeatureIndex)           

 * MaxEnt setFeatureSelection(FeatureSelection fs)           

 * void setParameter(int classIndex, int featureIndex, double value)           

 * void	setParameters(double[] parameters)           

 * MaxEnt setPerClassFeatureSelection(FeatureSelection[] fss) 
```



**Yet to be completed**
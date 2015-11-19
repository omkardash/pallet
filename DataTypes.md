# Introduction #

Contains the data types that are defined for MALLET <a href='Hidden comment:  Add Link for Data Import when all the four docs are combined'></a>Data Import and [Document Classification](http://code.google.com/p/pallet/wiki/DocClassification)

# Contents #
  * [Interfaces](DataTypes#Interfaces.md)
    * [AlphabetCarrying](DataTypes#Interfaces.md)
    * [CachedMetric](DataTypes#Interfaces.md)
    * [ConstantMatrix](DataTypes#Interfaces.md)
    * [Labeler](DataTypes#Interfaces.md)
    * [Labeling](DataTypes#Interfaces.md)
    * [Matrix](DataTypes#Interfaces.md)
    * [Metric](DataTypes#Interfaces.md)
    * [PartiallyRankedFeatureVector.Factory](DataTypes#Interfaces.md)
    * [PartiallyRankedFeatureVector.PerLabelFactory](DataTypes#Interfaces.md)
    * [PropertyHolder](DataTypes#Interfaces.md)
    * [RankedFeatureVector.Factory](DataTypes#Interfaces.md)
    * [RankedFeatureVector.PerLabelFactory](DataTypes#Interfaces.md)
    * [Sequence&lt;E&gt;](DataTypes#Interfaces.md)
  * [Classes](DataTypes#Classes.md)
    * [Alphabet](DataTypes#Alphabet.md)
      * [Constructors](DataTypes#Alphabet-Constructors.md)
      * [Public Methods](DataTypes#Alphabet-Public-Methods.md)
    * [ArrayListSequence](DataTypes#Array-List-Sequence.md)
    * [ArraySequence](DataTypes#Array-Sequence.md)
    * [AugmentableFeatureVector](DataTypes#Augmentable-Feature-Vector.md)
    * [ChainedInstanceIterator](DataTypes#Chained-Instance-Iterator.md)
    * [CrossValidationIterator](DataTypes#Cross-Validation-Iterator.md)
    * [DenseMatrix](DataTypes#Dense-Matrix.md)
    * [DenseVector](DataTypes#Dense-Vector.md)
    * [Dirichlet](DataTypes#Dirichlet.md)
    * [Dirichlet.Estimator](DataTypes#Dirichlet-Estimator.md)
    * [Dirichlet.MethodOfMomentsEstimator](DataTypes#Dirichlet-Method-Of-Moments-Estimator.md)
    * [ExpGain](DataTypes#Exp-Gain.md)
    * [ExpGain.Factory](DataTypes#Exp-Gain.Factory.md)
    * [FeatureConjunction](DataTypes#Feature-Conjunction.md)
    * [FeatureConjunction.List](DataTypes#Feature-Conjunction-List.md)
    * [FeatureCounter](DataTypes#Feature-Counter.md)
    * [FeatureCounts](DataTypes#Feature-Counts.md)
    * [FeatureCounts.Factory](DataTypes#Feature-Counts-Factory.md)
    * [FeatureInducer](DataTypes#Feature-Inducer.md)
    * [FeatureSelection](DataTypes#Feature-Selection.md)
    * [FeatureSelector](DataTypes#Feature-Selector.md)
    * [FeatureSequence](DataTypes#Feature-Sequence.md)
    * [FeatureSequenceWithBigrams](DataTypes#Feature-Sequence-With-Bigrams.md)
    * [FeatureVector](DataTypes#Feature-Vector.md)
    * [FeatureVectorSequence](DataTypes#Feature-Vector-Sequence.md)
    * [GainRatio](DataTypes#Gain-Ratio.md)
    * [GradientGain](DataTypes#Gradient-Gain.md)
    * [GradientGain.Factory](DataTypes#Gradient-Gain.Factory.md)
    * [HashedSparseVector](DataTypes#Hashed-Sparse-Vector.md)
    * [IDSorter](DataTypes#ID-Sorter.md)
    * [IndexedSparseVector](DataTypes#Indexed-Sparse-Vector.md)
    * [InfoGain](DataTypes#Info-Gain.md)
    * [InfoGain.Factory](DataTypes#Info-Gain.Factory.md)
    * [Instance](DataTypes#Instance.md)
    * [InstanceList](DataTypes#Instance-List.md)
    * [InstanceListTUI](DataTypes#Instance-List-TUI.md)
    * [InvertedIndex](DataTypes#Inverted-Index.md)
    * [KLGain](DataTypes#KL-Gain.md)
    * [Label](DataTypes#Label.md)
    * [LabelAlphabet](DataTypes#Label-Alphabet.md)
    * [Labelings](DataTypes#Labelings.md)
    * [Labels](DataTypes#Labels.md)
    * [LabelSequence](DataTypes#Label-Sequence.md)
    * [LabelsSequence](DataTypes#Labels-Sequence.md)
    * [LabelVector](DataTypes#Label-Vector.md)
    * [Matrix2](DataTypes#Matrix-2.md)
    * [Matrixn](DataTypes#Matrix-n.md)
    * [MatrixOps](DataTypes#Matrix-Ops.md)
    * [Minkowski](DataTypes#Minkowski.md)
    * [MultiInstanceList](DataTypes#Multi-Instance-List.md)
    * [Multinomial](DataTypes#Multinomial.md)
    * [Multinomial.Estimator](DataTypes#Multinomial.Estimator.md)
    * [Multinomial.LaplaceEstimator](DataTypes#Multinomial.Laplace-Estimator.md)
    * [Multinomial.Logged](DataTypes#Multinomial.Logged.md)
    * [Multinomial.MAPEstimator](DataTypes#Multinomial.MAP-Estimator.md)
    * [Multinomial.MEstimator](DataTypes#Multinomial.M-Estimator.md)
    * [Multinomial.MLEstimator](DataTypes#Multinomial.ML-Estimator.md)
    * [NormalizedDotProductMetric](DataTypes#Normalized-Dot-Product-Metric.md)
    * [PagedInstanceList](DataTypes#Paged-Instance-List.md)
    * [PartiallyRankedFeatureVector](DataTypes#Partially-Ranked-Feature-Vector.md)
    * [PerLabelFeatureCounts](DataTypes#Per-Label-Feature-Counts.md)
    * [PerLabelFeatureCounts.Factory](DataTypes#Per-Label-Feature-Counts.Factory.md)
    * [PerLabelInfoGain](DataTypes#Per-Label-Info-Gain.md)
    * [PerLabelInfoGain.Factory](DataTypes#Per-Label-Info-Gain.Factory.md)
    * [RankedFeatureVector](DataTypes#Ranked-Feature-Vector.md)
    * [ROCData](DataTypes#ROC-Data.md)
    * [SequencePair](DataTypes#Sequence-Pair.md)
    * [SequencePairAlignment](DataTypes#Sequence-Pair-Alignment.md)
    * [SingleInstanceIterator](DataTypes#Single-Instance-Iterator.md)
    * [SparseMatrixn](DataTypes#Sparse-Matrix-n.md)
    * [SparseVector](DataTypes#Sparse-Vector.md)
    * [StringEditFeatureVectorSequence](DataTypes#String-Edit-Feature-Vector-Sequence.md)
    * [StringEditVector](DataTypes#String-Edit-Vector.md)
    * [StringKernel](DataTypes#String-Kernel.md)
    * [Token](DataTypes#Token.md)
    * [TokenSequence](DataTypes#Token-Sequence.md)

> 
---

> 
---

# Interfaces #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

  * Alphabet-Carrying: [AlphabetCarrying](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Cached-Metric: [CachedMetric](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Constant-Matrix: [ConstantMatrix](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Labeler: [Labeler](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Labeling: [Labeling](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Matrix: [Matrix](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Metric: [Metric](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Partially-Ranked-Feature-Vector.Factory: [PartiallyRankedFeatureVector.Factory](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Partially-Ranked-Feature-Vector.Per-Label-Factory: [PartiallyRankedFeatureVector.PerLabelFactory](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Property-Holder: [PropertyHolder](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Ranked-Feature-Vector.Factory: [RankedFeatureVector.Factory RankedFeatureVector.Factory](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Ranked-Feature-Vector.Per-Label-Factory: [RankedFeatureVector.PerLabelFactory](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
  * Sequence`<E>`: [Sequence&lt;E&gt;](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> [Contents](DataTypes#Contents.md)
> 
---

> 
---

# Classes #
# Alphabet #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

public class Alphabet

&lt;BR CLEAR="ALL"|"LEFT"|"RIGHT"&gt;


extends java.lang.Object

&lt;BR CLEAR="ALL"|"LEFT"|"RIGHT"&gt;


implements java.io.Serializable

&lt;BR CLEAR="ALL"|"LEFT"|"RIGHT"&gt;



As described by the MALLET API Doc:

&lt;BR CLEAR="ALL"|"LEFT"|"RIGHT"&gt;


A mapping between integers and objects where the mapping in each direction is efficient. Integers are assigned consecutively, starting at zero, as objects are added to the Alphabet. Objects can not be deleted from the Alphabet and thus the integers are never reused.

The most common use of an alphabet is as a dictionary of feature names associated with a [FeatureVector](DataTypes#Feature-Vector.md) in an [Instance](DataTypes#Instance.md). In a simple document classification usage, each unique word in a document would be a unique entry in the Alphabet with a unique integer associated with it. [FeatureVectors](DataTypes#Feature-Vector.md) rely on the integer part of the mapping to efficiently represent the subset of the Alphabet present in the [FeatureVector](DataTypes#Feature-Vector.md).

[Contents](DataTypes#Contents.md)

## Alphabet-Constructors ##
  * [Alphabet()](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> Instantiates Alphabet object with default capacity (= 8).

  * [Alphabet(java.lang.Class entryClass)](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> Instantiates Alphabet object with default capacity and specified entry class
> ### Parameters ###
    1. entryClass - class of an object, type - java.lang.Class
```
 Alphabet dict = new Alphabet(String.class);
```

  * [Alphabet(int capacity)](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> Instantiates Alphabet object with specified capacity
> ### Parameters ###
    1. capacity - Initial Capacity of [Alphabet](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html), type - int
```
 Alphabet dict = new Alphabet(10);
```

  * [Alphabet(int capacity, java.lang.Class entryClass)](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> Instantiates Alphabet object with specified capacity and entry Class.
> ### Parameters ###
    1. capacity - Initial Capacity of [Alphabet](http://mallet.cs.umass.edu/api/cc/mallet/type/Alphabet.html), type - int
    1. entryClass - class of an object, type - java.lang.Class
```
 Alphabet dict = new Alphabet(10, String.class);
```

  * [Alphabet(java.lang.Object[](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html) entries)]
> Instantiates Alphabet object from array of "Object"s
```
 String [] s = new String [] {"string1", "string2"};
 Alphabet dict = new Alphabet(s);
```

[Contents](DataTypes#Contents.md)

## Alphabet-Public-Methods ##
  * void startGrowth()
> > Starts the growth of the alphabet.

  * void stopGrowth()
> > Stops the growth of the Alphabet.

  * [boolean growthStopped()](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)

> ### Return Value ###
    1. Returns true if the growth is stopped else true.

  * int size()
> ### Return Value ###
> Returns size of the Alphabet object (# of entries).

  * [int lookupIndex(java.lang.Object entry)](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> Looks for the "entry" in the Alphabet and returns the index if success:
> if "entry" is present
> > Returns the index to the entry.

> else
> if growth is not stopped,
> > adds the "entry" to the Alphabet
> > and
> > returns index

> else
> > returns -1

> ### Parameters ###
    1. entry - Object for which the index in Alphabet needs to be found, Type - java.lang.Object
> ### Return Value ###
    1. Returns the index of the object in the Alphabet, Type - int
```
 Alphabet dict = new Alphabet(10);
 int index = dict.lookupIndex("string1"); // index == 0
 System.out.println ("index - " + index);
 index = dict.lookupIndex("string2");     // index == 1
 System.out.println ("index - " + index);
```

  * [int lookupIndex(java.lang.Object entry, boolean addIfNotPresent)](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> if "entry" is present
> > Returns the index to the entry.

> else
> if growth is not stopped and addIfNotPresent == true
> > add the "entry" to the Alphabet and returns index.

> else
> > returns -1


> ### Parameters ###
    1. entry - Object for which the index in Alphabet needs to be found, Type - java.lang.Object
    1. addIfNotPresent - true or false
      1. if false
        * Looks for "entry" in the alphabet, if present returns its index else -1
      1. if true
        * Looks for "entry" in the alphabet, if present returns its index else adds "entry" to the Alphabet and returns its index
> ### Return Value ###
    1. Returns -1 if entry isn't present  and addIfNotPresent == false
    1. Returns index of "entry" in the Alphabet if addIfNotPresent == true
```
// addIfNotPresent == false
 Alphabet dict = new Alphabet(10);
 int index = dict.lookupIndex("string1", true); // index == 0
 index = dict.lookupIndex("string2",false);     // index == -1
 System.out.println ("index - " + index);
```

  * int`[]` [lookupIndices(java.lang.Object[](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html) objects, boolean addIfNotPresent)]
> ### Parameters ###
    1. objects - Array of objects that needs to be added or searched, similar to the previous method.
    1. addIfNotPresent - Similar to the previous method.
> ### Return Value ###
    1. Returns array of indices to the entries that were looked up, description similar to the previous method.

  * [java.lang.Class entryClass()](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> ### Return Value ###
> Returns the Runtime class of the object, Type - java.lang.Class

  * [boolean contains(java.lang.Object entry)](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> ### Return Value ###
> true - if entry is present else false

  * [java.lang.Object clone()](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> Clones the alphabet
> ### Return Value ###
    1. Returns cloned object, type - java.lang.Object (but, essentially Alphabet object)
```
 Alphabet cloned_alpha = (Alphabet)dict.clone();
 System.out.println ("cloned - " + cloned_alpha.toString());
```

  * [java.rmi.dgc.VMID getInstanceId()](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> ### Return Value ###
    1. Returns VMID of the Alphabet object.

  * [java.util.Iterator iterator()](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> ### Return Value ###
> Returns Alphabet's Iterator. type - java.util.Iterator

  * [java.lang.String toString()](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> Return String representation of all Alphabet entries, each separated by a newline.

  * [void dump()](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html) - 1
  * [void dump(java.io.PrintStream out)](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html) - 2
  * [void dump(java.io.PrintWriter out)](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html) - 3
> > Dumps the alphabet to either stdout(1) or specified stream (2,3)

  * [java.lang.Object[](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html) toArray()]

> ### Return Value ###
    1. Returns an array containing all the entries in the Alphabet.

  * java.lang.Object[.md](.md) toArray(java.lang.Object[.md](.md) in)
> Returns an array containing all the entries in the Alphabet.
> The runtime type of the returned array is the runtime type of in.
> If in is large enough to hold everything in the alphabet, then it
> it used.  The returned array is such that for all entries obj,
> ret[lookupIndex(obj)] = obj
> ### Return Value ###
    1. Returns an array containing all the entries in the Alphabet.

  * [java.lang.Object lookupObject(int index)](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html)
> Returns an array of the objects corresponding to indices
> ### Parameters ###
    1. indices - An array of indices to look up.
> ### Return Value ###
    1. An array of values from this Alphabet.

  * java.lang.Object[.md](.md) [lookupObjects(int[](http://mallet.cs.umass.edu/api/cc/mallet/types/Alphabet.html) indices, java.lang.Object[.md](.md) buf)]
> Returns an array of the objects corresponding to
> ### Parameters ###
    1. indices - An array of indices to look up.
    1. buf - An array to store the returned objects in.
> ### Return Value ###
    1. An array of values from this Alphabet. The runtime type of the array is the same as buf.

  * static boolean alphabetsMatch(AlphabetCarrying object1, AlphabetCarrying object2)
> Convenience method that can often implement alphabetsMatch in classes that implement the AlphabetsCarrying interface.
> ### Parameters ###
> ### Return Value ###

  * java.lang.Object readResolve()
> This gets called after readObject; it lets the object decide whether to return itself or return a previously read in version.


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Array-List-Sequence #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Array-Sequence #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

**Description -** Stores sequence of objects

public class ArraySequence`<E>`

&lt;BR CLEAR="ALL"|"LEFT"|"RIGHT"&gt;


extends java.lang.Object

&lt;BR CLEAR="ALL"|"LEFT"|"RIGHT"&gt;


implements Sequence`<E>`

&lt;BR CLEAR="ALL"|"LEFT"|"RIGHT"&gt;



## Array-Sequence-Constructors ##
  * [ArraySequence(java.util.ArrayList`&lt;E&gt;` a)](http://mallet.cs.umass.edu/api/cc/mallet/types/ArraySequence.html#ArraySequence(java.util.ArrayList))
> Creates ArraySequence from the ArrayList
> ### Parameters ###
> array\_list - Arraylist Object

```
 ArrayList al = new ArrayList ();
 for (int i = 0; i < 10; i++)
 {			
   al.add(i);
 }
 ArraySequence as = new ArraySequence(al);
```

  * [ArraySequence(E[](http://mallet.cs.umass.edu/api/cc/mallet/types/ArraySequence.html) a)]
> Creates ArraySequence from array of object
> ### Parameters ###
> a - Array of Objects

```
 String arr[] = new String [10];
 for (int i = 0; i < 10; i++)
 {			
   arr[i] = + i;
 }
 ArraySequence<String> as = new ArraySequence(arr);
```

  * [ArraySequence(E[](http://mallet.cs.umass.edu/api/cc/mallet/types/ArraySequence.html) a, boolean copy)]
> Creates ArraySequence from array object.
> ### Parameters ###
    1. arr - Array Object for which the ArraySequence
    1. copy - if true creates copy of the arr in ArraySequence.

  * [protected ArraySequence(Sequence`&lt;E&gt;` s, boolean copy)](http://mallet.cs.umass.edu/api/cc/mallet/types/ArraySequence.html)
> Creates ArraySequence from Sequence`<E>`
> ### Parameters ###
    1. s - Reference of Sequence object
    1. copy - if true creates copy of the arr in ArraySequence.

## Array-Sequence-Public-Methods ##
  * [E get(int index)](http://mallet.cs.umass.edu/api/cc/mallet/types/ArraySequence.html)
> Fetches data at the specified index.
> ### Parameters ###
    1. index - Index to the ArraySequence
> ### Return Value ###
    1. Object at the specified index

  * [int size()](http://mallet.cs.umass.edu/api/cc/mallet/types/ArraySequence.html)
> ### Return Value ###
    1. Returns size of the ArraySequence

  * [java.lang.String toString()](http://mallet.cs.umass.edu/api/cc/mallet/types/ArraySequence.html)
> ### Return Value ###
    1. Returns stringized array object.


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Augmentable-Feature-Vector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

public class AugmentableFeatureVector

&lt;BR CLEAR="ALL"|"LEFT"|"RIGHT"&gt;


extends FeatureVector

&lt;BR CLEAR="ALL"|"LEFT"|"RIGHT"&gt;


implements java.io.Serializable

&lt;BR CLEAR="ALL"|"LEFT"|"RIGHT"&gt;



## Augmentable-Feature-Vector-Constructor ##
  * [AugmentableFeatureVector(Alphabet dict)](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html#AugmentableFeatureVector(cc.mallet.types.Alphabet))
  * [AugmentableFeatureVector(Alphabet dict, boolean binary)](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html#AugmentableFeatureVector(cc.mallet.types.Alphabet,%20boolean))
  * [AugmentableFeatureVector](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html)(Alphabet dict, double`[]` values)
  * [AugmentableFeatureVector(Alphabet dict, double`[](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html)` values, int capacity)]
  * http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html AugmentableFeatureVector](Alphabet dict, int[.md](.md) indices, double[.md](.md) values, int capacity)
  * [AugmentableFeatureVector](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html)(Alphabet dict, int[.md](.md) indices, double[.md](.md) values, int capacity, boolean copy)
  * [AugmentableFeatureVector](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html)(Alphabet dict, int`[]` indices, double`[]` values, int capacity, boolean copy, boolean checkIndicesSorted)
  * [AugmentableFeatureVector](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html)(Alphabet dict, int`[]` indices, double`[]` values, int capacity, int size, boolean copy, boolean checkIndicesSorted, boolean removeDuplicates)
  * [AugmentableFeatureVector(Alphabet dict, int capacity, boolean binary)](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html#AugmentableFeatureVector(cc.mallet.types.Alphabet,%20int,%20boolean))
  * [AugmentableFeatureVector(Alphabet dict, PropertyList pl, boolean binary)](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html#AugmentableFeatureVector(cc.mallet.types.Alphabet,%20cc.mallet.util.PropertyList,%20boolean))
  * [AugmentableFeatureVector(Alphabet dict, PropertyList pl, boolean binary, boolean growAlphabet)](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html#AugmentableFeatureVector(cc.mallet.types.Alphabet,%20cc.mallet.util.PropertyList,%20boolean,%20boolean))
  * [AugmentableFeatureVector(FeatureSequence fs, boolean binary)](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html#AugmentableFeatureVector(cc.mallet.types.FeatureSequence,%20boolean))
  * [AugmentableFeatureVector(FeatureVector fv)](http://mallet.cs.umass.edu/api/cc/mallet/types/AugmentableFeatureVector.html#AugmentableFeatureVector(cc.mallet.types.FeatureVector))

## Augmentable-Feature-Vector-Methods ##

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Chained-Instance-Iterator #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Cross-Validation-Iterator #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Dense-Matrix #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---

# Dense-Vector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Dirichlet #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Dirichlet.Estimator #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Dirichlet.Method-Of-Moments-Estimator #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Exp-Gain #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Exp-Gain.Factory #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Conjunction #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Conjunction.List #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Counter #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Counts #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Counts.Factory #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Inducer #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Selection #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Selector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Sequence #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

Feature sequence is a mapping of features(dictionary of alphabets) to indices.
Feature sequence as the following data structure:
  1. Dictionary of alphabets  represented by [Alphabet](DataTypes#Alphabet.md).
  1. indices represents the dictionary to be stored, it contains the index to the [Alphabet](DataTypes#Alphabet.md).

```
eg: Let dictionary be "Never test a test string".
If the dictionary is represented as a Feature Sequence then 
	Feature sequence looks as follows:
 	Alphabet :
	index | Dictionary
	0 	Never
	1       test
        2       a
	3       string

	Index in Feature sequence
	 index [0] = 0 (Never)
         index [1] = 1 (test)
	 index [2] = 2 (a)
	 index [3] = 1 (test)
	 index [4] = 3 (string)
```
> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Sequence-With-Bigrams #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Vector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Feature-Vector-Sequence #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Gain-Ratio #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Gradient-Gain #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Gradient-Gain.Factory #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Hashed-Sparse-Vector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# ID-Sorter #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Indexed-Sparse-Vector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Info-Gain #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Info-Gain.Factory #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Instance #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Instance-List #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Instance-List-TUI #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)
> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Inverted-Index #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# KL-Gain #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Label #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Label-Alphabet #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Labelings #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Labels #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Label-Sequence #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Labels-Sequence #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Label-Vector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Matrix-2 #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Matrix-n #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Matrix-Ops #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Minkowski #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Multi-Instance-List #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Multinomial #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Multinomial.Estimator #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Multinomial.Laplace-Estimator #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Multinomial.Logged #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Multinomial.MAP-Estimator #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Multinomial.M-Estimator #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Multinomial.ML-Estimator #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Normalized-Dot-Product-Metric #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Paged-Instance-List #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Partially-Ranked-Feature-Vector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Per-Label-Feature-Counts #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Per-Label-Feature-Counts.Factory #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Per-Label-Info-Gain #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Per-Label-Info-Gain.Factory #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Ranked-Feature-Vector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# ROC-Data #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done

> [Contents](DataTypes#Contents.md)

> 
---

> 
---


# Sequence-Pair #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Sequence-Pair-Alignment #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Single-Instance-Iterator #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Sparse-Matrix-n #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Sparse-Vector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

A sparse vector is a vector having a relatively small number of nonzero elements.

Mallet uses Compressed-vector storage mode:
It uses two 1D arrays to store a sparse vector, each with a length of number of non-zero elements.
  1. Index array - Indicates the element position.
  1. Value array - Non-zero elements of the sparse vector.

eg:
Array : {0.0, 0.0, 1.0, 0.0, 2.0, 3.0, 0.0, 4.0, 0.0, 5.0, 0.0}
Index array : {3, 5, 6, 8, 10}
Value array : {1.0, 2.0, 3.0, 4.0, 5.0}

## Constructors ##
  * [SparseVector](http://mallet.cs.umass.edu/api/cc/mallet/types/SparseVector.html) (int[.md](.md) indices, double[.md](.md) values, int capacity, int size, boolean copy,boolean checkIndicesSorted boolean removeDuplicates)



## Public-Methods ##

> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# String-Edit-Feature-Vector-Sequence #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# String-Edit-Vector #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# String-Kernel #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Token #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---


# Token-Sequence #
**Package:** [cc.mallet.types](http://mallet.cs.umass.edu/api/cc/mallet/types/package-summary.html)

> To be done


> [Contents](DataTypes#Contents.md)
> 
---

> 
---

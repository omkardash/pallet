Table of Contents



# Introduction #
This tutorial is in the continuation of Data\_Import\_Tutorial 1,2,3,4. This is the last
tutorial which completes the Data Import Section of the Mallet.

# Details #

## Feature-Count-Pipe ##
This pipe is used to prune the features which have low count.It is used in order to save the memory which could have been occupied by low count features.This class supports a simpler method that makes two passes over the data : one to collect statistics and create an augumented "stop list",and secondly to actually create instances.

### Implementation ###

  * 1) FeatureCountPipe(): This is a simple constructor with no parameters which creates the instance of FeatureCountPipe.

  * 2)FeatureCountPipe(Alphabet datalphabet,Alphabet targetAlphabet): User can supply data alphabet and target alphabet explicity .

### Methods ###

  * 1)void addPrunedWordsToStoplist(SimpleTokenizer tokenizer, int minimumCount): Add all pruned words to the internal stoplist of a SimpleTokenizer.

  * 2)Alphabet getPrunedAlphabet(int minimumcount): Return a new Alphabet that contains only features at or above the specified limit

  * 3)void writeCommonWords(java.io.File commonfile,int totalwords): List the most common words ,for addition to a stop file.

  * 4)void writePrunedWords(java.io.File prunedfile,int minimumcount): Write a list of features that do not occur at or above the specified cutoff to the pruned file,one per file.

  * 5)Instance pipe(Instance instance): Method used to process the data.

### Sample Code ###

```
import cc.mallet.pipe.*;
import cc.mallet.types.*;
import java.io.*;

public class ImportExample200 {
	static Pipe pipe=null;
	
	public static void main(String args[])
	{
         SimpleTokenizer st=new SimpleTokenizer(1);
		
   String trainingdata="on the plains of  africa the lions123  roar the";
      Instance i=new Instance(trainingdata,"africa","Instance-1",null);
	Input2CharSequence i2cs=new Input2CharSequence();
	Instance i1=i2cs.pipe(i);
				
  SGML2TokenSequence cs2ts=new SGML2TokenSequence();
		 Instance i2=cs2ts.pipe(i1);
			 
  TokenSequence2FeatureSequence ts2fs=new TokenSequence2FeatureSequence();
		 Instance i3=ts2fs.pipe(i2);
				 
FeatureCountPipe fcp=new FeatureCountPipe(i3.getDataAlphabet(),i3.getTargetAlphabet());->1
		Instance i6=fcp.pipe(i3);--->2
		File f=new File("f200.txt");--->3
		try
		{
		fcp.writePrunedWords(f, 2);---->4
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		}
                }
```

  * 1) An instance of Feature Count Pipe is created with Data Alphabet and Target Alphabet of the previous pipe(TokenSequence2FeatureSequence in this case).
  * 2)An instance (i6) is generated which contains the data processed by FeatureCountPipe.
  * 3)A file is created which contains the pruned words.
  * 4)writePrunedWords is called with value 2. It means the words which doesn't occur more than 2 times will be pruned.

### Output ###
```
1)The file f200.txt will contains 
on
of
africa
lions
roar
```
These all words doesn't occur more than two times in the raw data so will be pruned from feature set.

## Feature-Value-String-2-Feature-Vector ##
It is simple pipe which converts the Feature Value String 2 Feature Vector.

### Implementation ###

  * 1)FeatureValueString2FeatureVector(): It is no argument constructor which simply creates an instance of FeatureValueString2FeatureVector.

  * 2)FeatureValueString2FeatureVector(Alphabet alphabet): If user wants to give Alphabet  externally then should use this constructor.

### Sample code ###
```

import cc.mallet.pipe.*;
import cc.mallet.types.*;

public class ImportExample901 {
	static Pipe pipe=null;
	
	
	public static void main(String args[])
	{
		
		
  String trainingdata="width=60 height=10 length=20 width=80";-------->1
  Instance i=new Instance(trainingdata,"africa","Instance-1",null);
  FeatureValueString2FeatureVector i2cs=new FeatureValueString2FeatureVector();----->2
	Instance i1=i2cs.pipe(i);
	PrintInputAndTarget piat=new PrintInputAndTarget();
	Instance i5=piat.pipe(i1);
		
	}

}
```
  * 1)The feature value string is used as the training set.
  * 2) An instance of FeatureValueString2FeatureVector is created.

### Output ###
The Feature Value String is converted int Feature Vector.
```
name: Instance-1
input: width(0)=140.0(Two occurences of the width is added)

height(1)=10.0

length(2)=20.0

target: africa

```
## Token-Sequence-2-Feature-Vector-Sequence ##
It is pipe which take input as Token Sequence of Instances and convert it into Feature
Vector Sequence.

### Implementation ###

  * 1)TokenSequence2FeatureVectorSequence():

  * 2)TokenSequence2FeatureVectorSequence(Alphabet dataDict):

  * 3)TokenSequence2FeatureVectorSequence(Alphabet dataDict, boolean binary, boolean augmentable):

  * 4)TokenSequence2FeatureVectorSequence(boolean binary, boolean augmentable):

dataDict: If user explicity wants to provide data Alphabet.
augmentable: It it is true than augmentable feature vector is created.

### Sample Code ###

```

import cc.mallet.pipe.*;
import cc.mallet.types.*;

public class ImportExample54{
	static Pipe pipe=null;
	
	
	public static void main(String args[])
	{
		
 String trainingdata="<abc> on the plains of </abc> africa the <a> lions123 has roared</a> life ";
	Instance i=new Instance(trainingdata,"africa","Instance-1",null);
	Input2CharSequence i2cs=new Input2CharSequence();
	Instance i1=i2cs.pipe(i);
	SGML2TokenSequence cs2ts=new SGML2TokenSequence();
	Instance i2=cs2ts.pipe(i1);
	Target2LabelSequence t2fs=new Target2LabelSequence();
	Instance i8=t2fs.pipe(i2);
TokenSequence2FeatureVectorSequence ts2fvs=new TokenSequence2FeatureVectorSequence();--->1
	Instance i6=ts2fvs.pipe(i8);
	PrintInputAndTarget piat=new PrintInputAndTarget();
	Instance i5=piat.pipe(i6);	
	
	}
}

```

  * 1)An instance of TokenSequence2FeatureVectorSequence is created.

### Output ###
```
name: Instance-1
input: cc.mallet.types.FeatureVectorSequence@1ffb8dc------>Token sequence is converted 
0:                                                         into feature Vector Sequence
1: 
2: 
3: 
4: 
5: 
6: 
7: 
8: 
9: 

target: 0: abc (0)
1: abc (0)
2: abc (0)
3: abc (0)
4: O (1)
5: O (1)
6: a (2)
7: a (2)
8: a (2)
9: O (1)
```

## Feature-Vector-Sequence-2-Feature-Vectors ##
It is the pipe which converts the Feature Vector Sequence to Feature Vectors. The previous pipe (TokenSequence2FeatureVectorSequence) converts the token sequence to feature vector sequence and this pipe than subsequently converts the given Feature Vector Sequence to Feature Vectors.So given an instance with a Feature Vector Sequence in the data field ,it break up the sequence into the individual feature vectors producing one feature vector per instance.

### Implementation ###

  * 1)FeatureVectorSequence2FeatureVectors(): It simply creates an instance of FeatureVectorSequence2FeatureVectors pipe.

### Method ###
  * 1)
```
Iterator<Instance> newIteratorFrom(java.util.Iterator<Instance> inputIterator): 
```
Take in input as an Instance iterator and return the Instance Iterator whose instances are already processed through FeatureVectorSequence2FeatureVectors pipe.



### Sample Code ###

The instances which is passed through TokenSequence2FeatureVectorSequence is added to the InstanceList. Then an iterator which is used to iterate the previous InstanceList is passed to the newIteratorFrom method of the FeatureVectorSequence2FeatureVectors pipe. These methods returns the iterator which contains the individual feature vectors producing one feature vector per instance.

### Output ###
Output contains individual feature vectors per instance.


## Filter-Empty-Feature-Vector ##
This pipe provides functionality of iterating through Feature Vectors individually.

### Implementation ###

  * 1)FilterEmptyFeatureVectors() : It simply creates an instance of FilterEmptyFeatureVectors pipe.

### Method ###

  * 1)
```
public Iterator<Instance> newIteratorFrom (Iterator<Instance> source) 
```

Given an Instance Iterator , return a new Instance Iterator whose instances have also been processed by this pipe.


### Sample Code ###
```
import java.io.IOException;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import cc.mallet.classify.Classification;
import cc.mallet.classify.Classifier;
import cc.mallet.classify.ClassifierTrainer;
import cc.mallet.classify.NaiveBayesTrainer;
import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.*;
import cc.mallet.types.*;

public class ImportExample202 {
	static Pipe pipe=null;
	static InstanceList instancelist = null;
	static Classifier cls=null;
	static FeatureCountPipe ob11=null;
	static TokenSequence2FeatureSequence ob10=null;
	public Pipe buidPipe()
	{
		ArrayList pipeList = new ArrayList();
		
   pipeList.add(new Input2CharSequence("UTF-8"));
       
      Pattern tokenPattern =
         Pattern.compile("[\\p{L}\\p{N}_]+");
        pipeList.add(new CharSequence2TokenSequence(tokenPattern));
   
     pipeList.add(new TokenSequence2FeatureSequence());
     
   
      pipeList.add(new Target2Label());
   
     pipeList.add(new FeatureSequence2FeatureVector());
       
   pipeList.add(new PrintInputAndTarget());
        
        return(new SerialPipes(pipeList));
		
	}
	
	
	public static void main(String args[])
	{
		ClassifierTrainer trainer = null; 
		String [][][] trainingdata = new String [][][] {
				{{ "on the plains of africa the lions roar",
				  "in swahili ngoma means to dance",
				  "nelson mandela became president of south africa",
				  "the saraha dessert saraha expanding"}, {"africa"}},				  
				{{ "panda bears eat bamboo",
				  "china's one child policy has resulted in a surplus of boys",
				  "tigers live in the jungle"}, {"asia"}},
				{{ "home of kangaroos",
				  "Autralian's for beer - Foster",
				  "Steve Irvin is a herpetologist"}, {"australia"}}				 
		};
		ImportExample202 ob1=new ImportExample202();
		pipe=ob1.buidPipe();
		InstanceList instances = new InstanceList(pipe);--------->1
		for (int i = 0; i < 3; i++) {
			try {
			 instances.addThruPipe (new ArrayIterator (trainingdata[i][0],trainingdata[i][1][0]));
			} catch (Exception e) { System.out.println(e);}
		}
	   Iterator<Instance> i3 = instances.iterator();---------->2
	
	   FilterEmptyFeatureVectors fefv=new FilterEmptyFeatureVectors ();------>3
		Iterator<Instance>i4=fefv.newIteratorFrom(i3);------>4
		while(i4.hasNext())
		{
		   Instance i8=i4.next();
		  		   PrintInputAndTarget piat=new PrintInputAndTarget();
			Instance i5=piat.pipe(i8);--------->5
		
		}
		
		
		
		
	}

}
```

  * 1) An InstanceList is created which contains feature vectors.
  * 2) An Iterater is created corresponding to the above InstanceList.
  * 3) An object of FilterEmptyFeatureVectors is created.
  * 4) The newIteratorFrom method of the FilterEmptyFeatureVectors is called.It returns a iterator which can be used to process the feature vectors individually.
  * 5) The feature vector is processed individually by PrintInputAndTarget pipe.

### Output ###
```
name: array:0
input: on(0)=1.0
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
lions(5)=1.0
roar(6)=1.0

target: africa
name: array:1
input: in(7)=1.0
swahili(8)=1.0
ngoma(9)=1.0
means(10)=1.0
to(11)=1.0
dance(12)=1.0

target: africa
name: array:2
input: of(3)=1.0
africa(4)=1.0
nelson(13)=1.0
mandela(14)=1.0
became(15)=1.0
president(16)=1.0
south(17)=1.0

target: africa
name: array:3
input: the(1)=1.0
saraha(18)=2.0
dessert(19)=1.0
expanding(20)=1.0

target: africa
name: array:0
input: panda(21)=1.0
bears(22)=1.0
eat(23)=1.0
bamboo(24)=1.0

target: asia
name: array:1
input: of(3)=1.0
in(7)=1.0
china(25)=1.0
s(26)=1.0
one(27)=1.0
child(28)=1.0
policy(29)=1.0
has(30)=1.0
resulted(31)=1.0
a(32)=1.0
surplus(33)=1.0
boys(34)=1.0

target: asia
name: array:2
input: the(1)=1.0
in(7)=1.0
tigers(35)=1.0
live(36)=1.0
jungle(37)=1.0

target: asia
name: array:0
input: of(3)=1.0
home(38)=1.0
kangaroos(39)=1.0

target: australia
name: array:1
input: s(26)=1.0
Autralian(40)=1.0
for(41)=1.0
beer(42)=1.0
Foster(43)=1.0

target: australia
name: array:2
input: a(32)=1.0
Steve(44)=1.0
Irvin(45)=1.0
is(46)=1.0
herpetologist(47)=1.0

target: australia
name: array:0
input: on(0)=1.0
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
lions(5)=1.0
roar(6)=1.0

target: africa
name: array:1
input: in(7)=1.0
swahili(8)=1.0
ngoma(9)=1.0
means(10)=1.0
to(11)=1.0
dance(12)=1.0

target: africa
name: array:2
input: of(3)=1.0
africa(4)=1.0
nelson(13)=1.0
mandela(14)=1.0
became(15)=1.0
president(16)=1.0
south(17)=1.0

target: africa
name: array:3
input: the(1)=1.0
saraha(18)=2.0
dessert(19)=1.0
expanding(20)=1.0

target: africa
name: array:0
input: panda(21)=1.0
bears(22)=1.0
eat(23)=1.0
bamboo(24)=1.0

target: asia
name: array:1
input: of(3)=1.0
in(7)=1.0
china(25)=1.0
s(26)=1.0
one(27)=1.0
child(28)=1.0
policy(29)=1.0
has(30)=1.0
resulted(31)=1.0
a(32)=1.0
surplus(33)=1.0
boys(34)=1.0

target: asia
name: array:2
input: the(1)=1.0
in(7)=1.0
tigers(35)=1.0
live(36)=1.0
jungle(37)=1.0

target: asia
name: array:0
input: of(3)=1.0
home(38)=1.0
kangaroos(39)=1.0

target: australia
name: array:1
input: s(26)=1.0
Autralian(40)=1.0
for(41)=1.0
beer(42)=1.0
Foster(43)=1.0

target: australia
name: array:2     -----Feature Vectors are processed individualy.
input: a(32)=1.0
Steve(44)=1.0
Irvin(45)=1.0
is(46)=1.0
herpetologist(47)=1.0

target: australia
```

## Instance-List-Trim-Features-By-Count ##
This pipe is unimplemented in the Mallet as of now.

## Noop ##
It is the pipe which does nothing to the instance fields but which has the side effects on the dictionary.Noop creates the Dictionary from dataAlphabet and targetAlphabet which can be used by the DictinariedPipe's.You might want to use this pipe before Parallel pipes where the previous pipe do not need dictionaries ,but later steps in each parallel paths do,and they all must share the same directory.

### Implementation ###

  * 1)Noop(): It is a simple constructor which does nothing.
  * 2)Noop(Alphabet dataDict,Alphabet targetDict): It is the constructor which creates the dictionary from the given data Alphabet and target Alphabet.It forces the creation of Alphabet ,so that pipes which based on Alphabet can use it.

### Sample Code ###
```

import cc.mallet.pipe.*;
import cc.mallet.types.*;
import java.util.regex.*;

public class ImportExample4044 {
	static Pipe pipe=null;
        public static void main(String args[])
	{
		String trainingdata="on the plains of africa the lions roar";
		Instance i=new Instance(trainingdata,"africa","Instance-1",null);
		Input2CharSequence i2cs=new Input2CharSequence();
		Instance i1=i2cs.pipe(i);
				
		
       Pattern tokenPattern =
	           Pattern.compile("[\\p{L}\\p{N}_]+");
CharSequence2TokenSequence cs2ts =new    CharSequence2TokenSequence(tokenPattern);
			 Instance i2=cs2ts.pipe(i1);
			 
 TokenSequence2FeatureSequence ts2fs=new TokenSequence2FeatureSequence();
		 Instance i3=ts2fs.pipe(i2);
				 
  Noop n1=new Noop(i3.getDataAlphabet(),i3.getTargetAlphabet());------>1 
		 Instance in=n1.pipe(i3);
		 
	 Target2Label t2l=new Target2Label();
	Instance i4=t2l.pipe(in);
		 
  FeatureSequence2FeatureVector fs2afv=new FeatureSequence2FeatureVector();
		Instance i6=fs2afv.pipe(i4);
		
		
		PrintInputAndTarget piat=new PrintInputAndTarget();
		Instance i5=piat.pipe(i6);
		
	}

}
```

  * 1)Noop forces the creation of dictionary or dataAlphabet and TargetAlphabet which can be further used by Target2Label and FeatureSequence2FeatureVector pipes.

### Sample Output ###
The dataAlphabet and targetAlphabet is created by the use Noop.


## Pipe ##
  * 1) It is an abstract super class which doesn't contain any abstract methods.
  * 2) In order to use Pipe subclass you have to override either the pipe method or the newIteratorFrom method
  * 3) The "pipe" method is used when the pipe's processing of an Instance is one to one and "newIteratorFrom" method is used when the pipe's processing may result in more or fewer Instances than arrive through its source iterator.
  * 4)A pipe operates on an Instance, which is a carrier of data. A pipe reads from and writes to fields in the Instance when it is requested to process the instance. It is up to the pipe which fields in the Instance it reads from and writes to, but usually a pipe will read its input from and write its output to the "data" field of an instance.
  * 5)A pipe doesn't have any direct notion of input or output - it merely modifies instances that are handed to it. A set of helper classes, which implement the interface Iterator, iterate over commonly encountered input data structures and feed the elements of these data structures to a pipe as instances.
  * 6)A pipe is frequently used in conjunction with an InstanceList As instances are added to the list, they are processed by the pipe associated with the instance list and the processed Instance is kept in the list.
  * 7)In one common usage, a FileIterator is given a list of directories to operate over. The FileIterator walks through each directory, creating an instance for each file and putting the data from the file in the data field of the instance. The directory of the file is stored in the target field of the instance. The FileIterator feeds instances to an InstanceList, which processes the instances through its associated pipe and keeps the results.
  * 8)Pipes can be hierachically composed. In a typical usage, a SerialPipe is created, which holds other pipes in an ordered list. Piping an instance through a SerialPipe means piping the instance through each of the child pipes in sequence.
  * 9)A pipe holds two separate Alphabets: one for the symbols (feature names) encountered in the data fields of the instances processed through the pipe, and one for the symbols (e.g. class labels) encountered in the target fields.
(Source: Mallet Javadoc)

### Implementation ###
  * 1)Pipe(): It constructs a pipe with no data and target dictionaries.
  * 2)Pipe(Alphabet dataDict,Alphabet targetDict): It constructs a pipe with data and target dictionaries.

Note : For further details about Pipe ,please see [following](http://mallet.cs.umass.edu/api/)

## Print-Input ##

It is pipe simillar to "PrintInputAndTarget" .The difference is that it only prints data fields of Mallet instances.

### Implementation ###

  * 1)PrintInput(): This is no argument constructor which does nothing.It prints the output on the standard output stream.
  * 2)PrintInput(java.io.PrintStream out) :  This constructor takes the PrintStream argument and print the output on the specified output stream.
  * 3)PrintInput(java.lang.String prefix) : It prints the output on the standard outputstream and add the specified string as the prefix to the output.
  * 4)PrintInput(java.lang.String prefix, java.io.PrintStream out) : This constructor is the combination of the second and third constructor.

### Sample Code ###
```

import java.util.*;
import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.*;
import cc.mallet.types.*;

public class ImportExample {
static Pipe pipe=null;
static InstanceList instancelist = null;
	
public Pipe buidPipe()
{
ArrayList pipeList = new ArrayList();
	
 pipeList.add(new Input2CharSequence("UTF-8"));
 pipeList.add(new SGML2TokenSequence());
 pipeList.add(new Target2LabelSequence());
 pipeList.add(new TokenSequence2FeatureSequence());
 pipeList.add(new FeatureSequence2FeatureVector());
 pipeList.add(new PrintInput("Pallet\n"));----------------->1
 return(new SerialPipes(pipeList));
}
	
	
public static void main(String args[])
{
		
	String [][][] trainingdata = new String [][][] {
		{{ "on the plains of africa the lions roar",
		  "in swahili ngoma means to dance",
		  "nelson <tag>mandela became</tag> president of south africa",
	  "the saraha dessert saraha expanding"}, {"africa"}},				  
			{{ "panda bears eat bamboo",
	  "china's one child policy has resulted in a surplus of boys",
		  "tigers live in the jungle"}, {"asia"}},
	{{ "home of kangaroos",
				  "Autralian's for beer - Foster",
	  "Steve Irvin is a herpetologist"}, {"australia"}}				 
		};
		ImportExample ob1=new ImportExample();
		pipe=ob1.buidPipe();
		InstanceList instances = new InstanceList(pipe);
		for (int i = 0; i < 3; i++) {
			try {
 instances.addThruPipe (new ArrayIterator (trainingdata[i][0],trainingdata[i][1][0]));
			} catch (Exception e) { System.out.println(e);}
		}
	}

}
```

1)It calles the constructor of the PrintInput pipe with the string.

### Output ###
```
Pallet   ------------>String Pallet is attached as specified by the User
on(0)=1.0
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
lions(5)=1.0
roar(6)=1.0

------------------->Only data part is displayed by the Pipe no target part of the instances is declared.

Pallet
in(7)=1.0
swahili(8)=1.0
ngoma(9)=1.0
means(10)=1.0
to(11)=1.0
dance(12)=1.0

Pallet
of(3)=1.0
africa(4)=1.0
nelson(13)=1.0
mandela(14)=1.0
became(15)=1.0
president(16)=1.0
south(17)=1.0

Pallet
the(1)=1.0
saraha(18)=2.0
dessert(19)=1.0
expanding(20)=1.0

Pallet
panda(21)=1.0
bears(22)=1.0
eat(23)=1.0
bamboo(24)=1.0

Pallet
of(3)=1.0
in(7)=1.0
china(25)=1.0
s(26)=1.0
one(27)=1.0
child(28)=1.0
policy(29)=1.0
has(30)=1.0
resulted(31)=1.0
a(32)=1.0
surplus(33)=1.0
boys(34)=1.0

Pallet
the(1)=1.0
in(7)=1.0
tigers(35)=1.0
live(36)=1.0
jungle(37)=1.0

Pallet
of(3)=1.0
home(38)=1.0
kangaroos(39)=1.0

Pallet
s(26)=1.0
Autralian(40)=1.0
for(41)=1.0
beer(42)=1.0
Foster(43)=1.0

Pallet
a(32)=1.0
Steve(44)=1.0
Irvin(45)=1.0
is(46)=1.0
herpetologist(47)=1.0

```

## Print-Token-Sequence-Features ##
It print features of the token sequence in the data field and the corressponding value (if exists) of any token sequence or feature sequence in the target field.

### Implementation ###
  * 1) PrintTokenSequenceFeatures(): It is a simple no argument constructor which display the output.
  * 2)PrintTokenSequenceFeatures(java.lang.String prefix) : It displays the output with the attached prefix given by the user.

### Sample Code ###

```



import java.util.regex.*;
import cc.mallet.pipe.*;
import cc.mallet.types.*;

public class ImportExample76{
	static Pipe pipe=null;
	
	
	public static void main(String args[])
	{
		
		
		
		String trainingdata="width=10 height=11 length=12";
		
		Instance i=new Instance(trainingdata,"africa","Instance-1",null);
		Input2CharSequence i2cs=new Input2CharSequence();
		Instance i0=i2cs.pipe(i);
		
		  Pattern tokenPattern =
	            Pattern.compile("[\\p{L}\\p{N}_\\=]+");
 CharSequence2TokenSequence cs2ts=  new CharSequence2TokenSequence(tokenPattern);
		  Instance i2=cs2ts.pipe(i0);
		
			
		
TokenSequenceParseFeatureString tspf=new TokenSequenceParseFeatureString(true,true,"=");
		 Instance i120=tspf.pipe(i2);
		
	
	
		
		PrintInputAndTarget piat=new PrintInputAndTarget();-------->1
		Instance i5=piat.pipe(i120);
		System.out.println("\n");
		
	PrintTokenSequenceFeatures ptsf=new PrintTokenSequenceFeatures();------>2
	    Instance i6=ptsf.pipe(i120);
		
	}

}

```

  * 1) PrintInputAndTarget is implemented ,so that we can see what is the actual output.
  * 2) PrintTokenSequenceFeatures is implemented ,which process the output from TokenSequenceParseFeatureString.

### Output ###
```
name: Instance-1
input: TokenSequence [width=10 feature(width)=10.0  span[0..8], height=11 feature(height)=11.0  span[9..18], length=12 feature(length)=12.0  span[19..28]]
Token#0:width=10 feature(width)=10.0  span[0..8]--->Token 0 has a feature width which has
Token#1:height=11 feature(height)=11.0  span[9..18]    value 10.0.
Token#2:length=12 feature(length)=12.0  span[19..28]

target: africa
_____________________________________________________________________________________
_____________________________________________________________________________________
name: Instance-1
width=10.0    ------->PritnTokenSequenceFeatures prints the features of the TokenSequence
height=11.0           in the data field.
length=12.0 
```

## Save-Data-In-Source ##
Each Mallet Instance has four part
  * a)Name : This field should contain a value that identifies this instance, usually a String.
  * b)Label: The label is primarily used in classification applications. It is usually a value within a finite set of labels, a LabelAlphabet.
  * c)Data: Generally a FeatureVector (unordered feature-value pairs) or a FeatureSequence (an ordered list of features, for example a sequence of words).
  * d)Source: A representation of the original state of the instance. Often a File object or a String containing the original, untokenized text. Frequently null.

Now if we want to save the data from the Data field to Source field then we use this pipe.
So we can say that the source fields of each instance to its data field.

### Implementation ###
  * 1)SaveDataInSource(): It is simple constructor which creates the object of the SaveDataInSource pipe.

Note: We need to just create the pipe and pass the data through this pipe with the help of pipe method and the data is copied into the source field from data field.

## Selective-SGML-2-Token-Sequence ##
It converts only the specified SGML tag into Labels.It is similar to SGML2TokenSequence which converts all the SGML tags into Labels.

### Implementation ###
  * 1)SelectiveSGML2TokenSequence(CharSequenceLexer lex, java.util.Set allowed):
  * 2)SelectiveSGML2TokenSequence(CharSequenceLexer lexer, java.lang.String backgroundTag, java.util.Set allowed):-
  * 3)SelectiveSGML2TokenSequence(java.util.Set allowed)
  * 4)SelectiveSGML2TokenSequence(java.lang.String regex, java.lang.String backgroundTag, java.util.Set allowed):-

Note : The constructor are almost similar to the SGML2Tokensequence pipe.The difference is that here we added one more arguments allowed. It tells us that which are the allowed tags  that can be converted into labels.

### Sample Code ###
```

import cc.mallet.pipe.*;
import cc.mallet.types.*;
import java.util.*;
public class ImportExample45 {
	static Pipe pipe=null;
        public static void main(String args[])
	{
		
String trainingdata="<abc> on the plains of </abc> africa the <a> lions123 </a> roar";
		Instance i=new Instance(trainingdata,"africa","Instance-1",null);
		Input2CharSequence i2cs=new Input2CharSequence();
		Instance i1=i2cs.pipe(i);
		Set s=new TreeSet();-------->1
		s.add("abc");
				
SelectiveSGML2TokenSequence cs2ts=new SelectiveSGML2TokenSequence("[\\p{L}\\p{N}_]+","pallet",s);--------->2
	//SGML2TokenSequence cs2ts=new SGML2TokenSequence("[\\p{L}\\p{N}_]+","pallet");
	//SGML2TokenSequence cs2ts=new SGML2TokenSequence();------->3
		 Instance i2=cs2ts.pipe(i1);
			 
		 
		 TokenSequence2FeatureSequence ts2fs=new TokenSequence2FeatureSequence();
		 Instance i3=ts2fs.pipe(i2);
				 
		
          FeatureSequence2AugmentableFeatureVector fs2afv=new FeatureSequence2AugmentableFeatureVector();
		Instance i6=fs2afv.pipe(i3);
		
		
		PrintInputAndTarget piat=new PrintInputAndTarget();
		Instance i5=piat.pipe(i6);
		
	}

}
```

  * 1)A TreeSet is created to store the tags which will come in Labels
  * 2)An object of SelectiveSGML2TokenSequence is created.
  * 3)A commented line which will show the output when we use SGMLTokenSequence instead of
SelectiveSGML2TokenSequence.

### Output ###
```
name: Instance-1
input: on(0)=1.0
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
a(5)=2.0
lions123(6)=1.0
roa(7)=1.0

target: TokenSequence [abc, abc, abc, abc, pallet, pallet, pallet, pallet, pallet, pallet]
Token#0:abc   
Token#1:abc
Token#2:abc
Token#3:abc ---------->Only the allowed tags and Background tag comes in the Label 
Token#4:pallet        Sequence. "abc" is allowed tag and "pallet" is the background tag.
Token#5:pallet        "a" tag doesn't come in the Label Sequence.  
Token#6:pallet
Token#7:pallet
Token#8:pallet
Token#9:pallet

If we had used SGML2TokenSequence instead of SelectiveSGML2TokenSequence than the output
would have been 

name: Instance-1
input: on(0)=1.0
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
lions123(5)=1.0
roar(6)=1.0

target: TokenSequence [abc, abc, abc, abc, pallet, pallet, a, pallet]
Token#0:abc
Token#1:abc
Token#2:abc
Token#3:abc
Token#4:pallet
Token#5:pallet
Token#6:a  ----------->tag a comes to the Label Sequence.
Token#7:pallet

```


## Simple-Tagger-Sentence-2-String-Tokenization ##
This pipe extends SimpleTaggerSentence2TokenSequence to use {Slink StringTokenization} for use with the extract  package.

### Implementation ###

  * 1)SimpleTaggerSentence2StringTokenization(): Creates a new SimpleTaggerSentence2StringTokenization instance.
  * 2)SimpleTaggerSentence2StringTokenization(boolean inc):creates a new SimpleTaggerSentence2StringTokenization instance which includes tokens as features if the supplied argument is true.

### Sample Code ###
```

import cc.mallet.pipe.*;
import cc.mallet.types.*;
public class ImportExample938 {
public static void main(String args[])
{
String trainingdata="on the plains of africa the lions roar";
Instance ob2=new Instance(trainingdata,null,"array-1",null);
SimpleTaggerSentence2TokenSequence ob31=new SimpleTaggerSentence2TokenSequence();--->1
Instance i1=ob31.pipe(ob2);
PrintInputAndTarget piat=new PrintInputAndTarget();
Instance i6=piat.pipe(i1);
System.out.println("\n\n");
Instance ob3=new Instance(trainingdata,null,"array-1",null);
SimpleTaggerSentence2StringTokenization sts2t=new SimpleTaggerSentence2StringTokenization(true);-------->2
Instance i2=sts2t.pipe(ob3);
PrintInputAndTarget piat1=new PrintInputAndTarget();
Instance i61=piat1.pipe(i2);
}
}
```
1)Firstly SimpleTaggerSentence2TokenSequence is implemented
2)Then SimpleTaggerSentence2StringTokenization is implemented.

Note: Both pipes are implemented to show the difference between the two pipes.

### Output ###
```
name: array-1
input: TokenSequence [on feature(lions)=1.0 feature(the)=1.0 feature(africa)=1.0 feature(of)=1.0 feature(plains)=1.0 feature(the)=1.0 feature(on)=1.0]
Token#0:on feature(lions)=1.0 feature(the)=1.0 feature(africa)=1.0 feature(of)=1.0 feature(plains)=1.0 feature(the)=1.0 feature(on)=1.0

target: 0: roar (0)


cc.mallet.types.Instance@1571886
name: array-1
input: TokenSequence [on feature(lions)=1.0 feature(the)=1.0 feature(africa)=1.0 feature(of)=1.0 feature(plains)=1.0 feature(the)=1.0 feature(on)=1.0  span[0..2]]
Token#0:on feature(lions)=1.0 feature(the)=1.0 feature(africa)=1.0 feature(of)=1.0 feature(plains)=1.0 feature(the)=1.0 feature(on)=1.0  span[0..2]

target: 0: roar (0)

```

## Token-2-Feature-Vector ##
It converts the property list on a token into feature vector.

### Implementation ###

  * 1)Token2FeatureVector(): It converts the Token to feature Vector  using already defined data dictionary.
  * 2)Token2FeatureVector(Alphabet dataDict): If user want to provide data dictionary explicitly then should use this constructor.
  * 3)Token2FeatureVector(Alphabet dataDict, boolean binary, boolean augmentable)
  * 4)Token2FeatureVector(boolean binary, boolean augmentable)

  * binary : If it is true then binary (Augmentable)FeatureVector's in the sequence is created
  * augmentable: If it is true then Augmentable FeatureVector's in the sequence is created.

### Sample Code ###

```
import java.util.regex.*;
import cc.mallet.pipe.*;
import cc.mallet.types.*;

public class token{
	static Pipe pipe=null;
       public static void main(String args[])
	{
	 String trainingdata="width=10 height=11 length=12";
   Instance i=new Instance(trainingdata,"africa","Instance-1",null);
    Input2CharSequence i2cs=new Input2CharSequence();
	Instance i0=i2cs.pipe(i);
Pattern tokenPattern =
	            Pattern.compile("[\\p{L}\\p{N}_\\=]+");
  CharSequence2TokenSequence cs2ts=  new CharSequence2TokenSequence(tokenPattern);
		  Instance i2=cs2ts.pipe(i0);
	TokenSequence ts=new TokenSequence();----------->1
	ts=(TokenSequence)i2.getData();------------------>2
TokenSequenceParseFeatureString tspf=new TokenSequenceParseFeatureString(true,true,"=");
		Instance i120=tspf.pipe(i2);

PrintInputAndTarget piat=new PrintInputAndTarget();--------->3
	Instance i5=piat.pipe(i120);
			
System.out.println("\n\n");
			
	Token2FeatureVector t2fv=new Token2FeatureVector();-------->4
	for(Object o: ts)
		{   
                      Token t=(Token)o;
	Instance i34=new Instance(t,t.getText(),"Instance-2",t.getFeatures());----->5
			Instance i20=t2fv.pipe(i34);---------->6
			PrintInputAndTarget piat1=new PrintInputAndTarget();
			Instance i51=piat1.pipe(i20);------->7
		}}
}
```

  * 1)An instance of the type TokenSequence is created.
  * 2)The tokens from Instance i2 is taken and assigned to the tokensequence. So tokensequence is referring to all the tokens.
  * 3)The data is shown after processing through TokenSequenceParseFeatureString.It is to be noted that the data shown after line 3 gets executed is not processed through Token2FeatureVector.
  * 4)An instance of Token2FeatureVector is created.
  * 5)Each token taken from tokensequence is taken.
  * 6)A new Instance is created which takes the data from the token and then processed through Token2FeatureVector.
  * 7)The individual token which is converted into Feature Vector is printed on the screen.

### Output ###

name: Instance-1
input: TokenSequence [width=10 feature(width)=10.0  span[0..8], height=11 feature(height)=11.0  span[9..18], length=12 feature(length)=12.0  span[19..28]]
Token#0:width=10 feature(width)=10.0  span[0..8]
Token#1:height=11 feature(height)=11.0  span[9..18]
Token#2:length=12 feature(length)=12.0  span[19..28]-------->The output when Token2Feature
> vector is not applied.
target: africa



name: Instance-2     ------------->Each individual token is converted into Feature Vector.
input: width(0)=10.0

target: width=10

name: Instance-2
input: height(1)=11.0

target: height=11

name: Instance-2
input: length(2)=12.0

target: length=12

## Token-Sequence-2-Token-Instances ##
It is pipe which converts the token sequence to token instances.

### Implementation ###

  * 1)TokenSequence2TokenInstances(): It is simple constructor which creates the object of TokenSequence2TokenInstances.

Note: This pipe also contain the newIteratorFrom method which takes an Instance Iterator and return an Instance Iterator(whose instances are processed through this pipe).

### Sample Code ###
```

import java.util.Iterator;
import cc.mallet.pipe.*;
import cc.mallet.types.*;

public class ImportExample100{
	static Pipe pipe=null;
	
	public static void main(String args[])
	{
		
		
		String trainingdata="on the plains of africa the lions123  roar";
		Instance i=new Instance(trainingdata,"africa","Instance-1",null);
		Input2CharSequence i2cs=new Input2CharSequence();
		Instance i1=i2cs.pipe(i);
				
SGML2TokenSequence cs2ts=new SGML2TokenSequence("[\\p{L}\\p{N}_]+","pallet");
		Instance i2=cs2ts.pipe(i1);
		
		Target2LabelSequence t2ls=new Target2LabelSequence();
		Instance i3=t2ls.pipe(i2);
		 
		 InstanceList inst=new InstanceList();
		 inst.add(i3);
		 Iterator<Instance> i11=inst.iterator();
		
		 TokenSequence2TokenInstances ts2ti=new TokenSequence2TokenInstances();
		 Iterator <Instance>i78=ts2ti.newIteratorFrom(i11);
		 
		 System.out.println("\n\n");
try
{
		 while(i78.hasNext())
		 {
			 System.out.println(i78.next().getData());
			 
		 }
		 
}
catch(Exception e)
{
	
}
				
}
}
```
### Output ###
```
on  span[0..2]
the  span[3..6]
plains  span[7..13]
of  span[14..16]
africa  span[17..23]
the  span[24..27]
lions123  span[28..36]
roar  span[38..42]
```

## Augmentable-Feature-Vector-Add-Conjunction ##
It is simple pipe which add specified conjunction to the instances.

### Implementation ###

  * 1)AugmentableFeatureVectorAddConjunctions(): It is a simple constructor with no argument which creates the object of AugmentableFeatureVectorAddConjunctions.

### Method ###

  * 1)AugmentableFeatureVectorAddConjunctions addConjunction(java.lang.String name, Alphabet v, int[.md](.md) features, boolean[.md](.md) negations) : This method is used to add the specified conjunction to the instances.

  * 1) Instance 	pipe(Instance carrier) : This method is used to process the data.

## Branching-Pipe ##
Thie pipe is deprecated from the Mallet-2.0-RC2.

## Directory-2-File-Iterator ##
This pipe takes File object which refers to directory and return a File Iterator which iterates over the files in a directory matching a pattern.It extract Label from each file path to become the target field of the instance.

### Implementation ###

  * 1)Directory2FileIterator() : It is simple constructor which does nothing except creating the object of Directory2FileIterator.
  * 2)Directory2FileIterator(java.io.FileFilter fileFilter, java.util.regex.Pattern labelRegex):
    * fileFilter: This arguments tells that what kind of files will have to be taken care of for eg fileFilter can be set to taken care of only ".txt" files.
    * labelRegex: This tells what will be the label of the instance.
  * 3)Directory2FileIterator(java.util.regex.Pattern absolutePathRegex, java.util.regex.Pattern filenameRegex, java.util.regex.Pattern labelRegex)
  * 4)Directory2FileIterator(java.lang.String filenameRegex)

### Method ###

  * 1) java.util.Iterator pipe(java.io.File directory) : Given a file object which refers to directory, will return Iterator which can iterates through files.
  * 2) Instance 	pipe(Instance carrier): Given an Instance which contains data in file format,will return Instance which contains File Iterator.
  * 3) java.util.Iterator pipe(java.lang.String directory) : Create the file object of the name String provided by user.
  * 4) java.util.Iterator pipe(java.net.URI directory) :Create a file object of the name java.net.URI  directory.

### Sample Code ###
```

import cc.mallet.pipe.*;
import cc.mallet.types.*;
import java.io.*;
import java.util.Iterator;

import cc.mallet.pipe.iterator.*;

class TxtFilter implements FileFilter {

    public boolean accept(File file) {
        return file.toString().endsWith(".txt");
    }
}

public class ImportExample1000{
	static Pipe pipe=null;
	
	
	
	
	public static void main(String args[])
	{
      File f=new File("/home/pralabh/workspace1/sample-data");
Directory2FileIterator i1=new Directory2FileIterator(new TxtFilter(),FileIterator.LAST_DIRECTORY);-----> 1
		Iterator<Instance> i2=i1.pipe(f);
		while(i2.hasNext())
		{  
			System.out.println(i2.next().getName());---->2
	
		}	
	}

}
```

1)An instance of Directory2FileIterator is created which filter the text fine only and the Label would be the Path to the file.
2)Each file is processed and it's name is taken as Label

Note: sample-data is the name of the directory.

### Output ###
```
file:/home/pralabh/workspace1/sample-data/web/de/t40.txt
file:/home/pralabh/workspace1/sample-data/web/de/apollo8.txt
file:/home/pralabh/workspace1/sample-data/web/de/wildenstein.txt
file:/home/pralabh/workspace1/sample-data/web/de/hoechst.txt
file:/home/pralabh/workspace1/sample-data/web/de/ulrich.txt
file:/home/pralabh/workspace1/sample-data/web/de/rostock.txt
file:/home/pralabh/workspace1/sample-data/web/de/habichtsadler.txt
file:/home/pralabh/workspace1/sample-data/web/de/fiv.txt
file:/home/pralabh/workspace1/sample-data/web/de/sadat.txt
file:/home/pralabh/workspace1/sample-data/web/de/indogermanische.txt
file:/home/pralabh/workspace1/sample-data/web/de/marcellinus.txt
file:/home/pralabh/workspace1/sample-data/web/de/konrad.txt
file:/home/pralabh/workspace1/sample-data/web/en/thylacine.txt
file:/home/pralabh/workspace1/sample-data/web/en/elizabeth_needham.txt
file:/home/pralabh/workspace1/sample-data/web/en/uranus.txt
file:/home/pralabh/workspace1/sample-data/web/en/hawes.txt
file:/home/pralabh/workspace1/sample-data/web/en/yard.txt
file:/home/pralabh/workspace1/sample-data/web/en/gunnhild.txt
file:/home/pralabh/workspace1/sample-data/web/en/hill.txt
file:/home/pralabh/workspace1/sample-data/web/en/zinta.txt--->The path will be considered
file:/home/pralabh/workspace1/sample-data/web/en/thespis.txt   as Labels of Instances.
file:/home/pralabh/workspace1/sample-data/web/en/shiloh.txt
file:/home/pralabh/workspace1/sample-data/web/en/equipartition_theorem.txt
file:/home/pralabh/workspace1/sample-data/web/en/sunderland_echo.txt
```

## Serial Pipes ##
This pipe convert an instance through a sequence of pipes. We have used this pipe in the sample code of many other pipes.We added all the Pipes in the ArrayList and then pass the ArrayList as its arguments.

### Implementation ###
```
 * 1)SerialPipes() : It is simple constructor with no arguments.
 * 2)SerialPipes(java.util.Collection<Pipe> pipeList) : It takes the argument of the  object of the Collection type.
 * 3)SerialPipes(Pipe[] pipes): It is similar to the above constructor but takes the array of Pipe.
```
### Method ###
```

 *  Pipe getPipe(int index)
           
 * java.util.Iterator<Instance> newIteratorFrom(java.util.Iterator<Instance> source)
Given an InstanceIterator, return a new InstanceIterator whose instances have also been 
processed by this pipe.

 * SerialPipes newSerialPipesFromRange(int start, int end)
           
 * SerialPipes 	newSerialPipesFromSuffix(SerialPipes.Predicate testForStartingNewPipes)
           
 * java.util.ArrayList<Pipe> pipes()Allows access to the underlying collection of Pipes.

 * void setTargetProcessing(boolean lookForAndProcessTarget)
   Set whether input is taken from target field of instance during processing.

 * int 	size()
           
 * java.lang.String 	toString() 
```
Note: This pipe is used in sample code of many other pipes.
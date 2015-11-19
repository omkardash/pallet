Table of Contents


# Introduction #

This Tutorial is in the continuation of the Data\_Import Tutorial 1 and Data\_Import
Tutorial 2. This tutorial further explores the Data Import features of the Mallet.

# Details #
##### Note: Please go through the [Tutorial\_1](http://code.google.com/p/pallet/wiki/Tutorial_Data_Import) and [Tutorial\_2](http://code.google.com/p/pallet/w/edit/Data_Import_Tutorial_2) starting Tutorial 2. #####
```

The pipes which are going to discuss here are
 1) Filename2CharSequence
 2) CharSequenceArray2TokenSequence
 3) Csv2Array
 4) Array2FeatureVector
 5) Csv2FeatureVector
 6) TargetStringToFeatures
 7) SimpleTaggerSentence2TokenSequence
 8) SimpleTokenizer
 9) Pipeutils
 10) StringAddNewLineDelimiter
 11) CharSequenceRemoveUUEncodedBlocks
 12) StringList2FeatureSequence
 13) FeatureSequenceConvolution
 14) FeatureVectorConjunction
```
## File-name-2-Char-Sequence ##

The user provides the name of the input file in the form of string, it takes the data from the specified file and converts it into the character sequence.

### Implementation ###

  * A)Filename2CharSequence() : It has one constructor with no arguments.

### Sample code ###
```
Instance i=new Instance("sci_spec.txt","sci_spec","Instance-1",null); ------>1
Filename2CharSequence i2cs=new Filename2CharSequence();----------------->2
Instance i1=i2cs.pipe(i);------->3
```
  * 1)An instance is created by passing four arguments(Remember ,a Mallet instance is always consist of four things)
    * a)sci\_spec.txt : The first argument is the name of the file which contains the input data.
    * b)sci\_spec: The name of the target of the Instance.
    * c)Instance-1: The name of the instance.
    * b)null: The value of the source
  * 2)An object of Filename2CharSequence is created with no argument constructor.
  * 3)The created instance “i” is passed through the Filename2CharSequence Pipe.

### Output ###

The data of the given file is converted to the char sequence

## Char-Sequence-Array-2-Token-Sequence ##

It transforms the array of character sequences into a token sequence.

### Implementation ###

  * A)CharSequenceArray2TokenSequence(): This pipe also contains only the single constructor with no arguments.

### Sample Code ###
```
     import cc.mallet.pipe.*;
     import cc.mallet.types.*;
     public class ImportExample2 {
     public static void main(String args[])
     {
     CharSequence[] dataWithTags = new CharSequence[] {
     "on the plains of africa the lions roar",
     "in swahili ngoma means to dance",
     "the saraha dessert saraha expanding",
     Instance ob2=new Instance(dataWithTags,null,"array-1",null);
     CharSequenceArray2TokenSequence ob3=new CharSequenceArray2TokenSequence();
     Instance i=ob3.pipe(ob2);
     Object o=i.getData();
     System.out.println(o.toString());
     }
        }
```

### Output ###
```
   TokenSequence [on the plains of africa the lions roar, in swahili ngoma means to 
                  dance,the saraha dessert saraha expanding]
    Token#0:on the plains of africa the lions roar
    Token#1:in swahili ngoma means to dance --------------------->Tokens
    Token#2:the saraha dessert saraha expanding

Here ,the characterSequenceArray is converted into tokens
```

## Csv-2-Array ##
It converts the string of comma separated values(CSV) to an array where each index is a feature name. It will be used prior to Array2FeatureVector.The string must contain only numbers.

### Implementation ###
  * 1)Csv2Array(): This constructor will call the constructor of the charSequenceLexer  with this ([^,]+) parameter. This means process the input string except the “,”(which is used as a separator).
  * 2)Csv2Array(charSequenceLexer l) : If user wants to provide its own set of Pattern then directly pass the reference of charSequenceLexer.
  * 3)Csv2Array(java.lang.String regex) : If user wants to process the string in its own   way than provide the matching pattern in form of String.

  * Pattern : Pattern is the java inbuild class. For further explanation about Pattern
[please\_see](http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Pattern.html)

Note: The sample code and sample output of this pipe is explained after describing Array2FeatureVector.

## Array-2-Feature-Vector ##
This pipe is used to convert the Java array of numerical type to feature vector(as the
name signifies).

### Implementation ###
  * 1)Array2FeatureVector(): This pipe has just one constructor with no arguments.

### Sample Code ###
```
            import java.util.*;
            import cc.mallet.pipe.*;
            import cc.mallet.pipe.iterator.*;
            import cc.mallet.types.*;
             public class ImportExample3 {
                 static Pipe pipe=null;
                 static InstanceList instancelist = null;
                 public Pipe buidPipe()
                 {
                 ArrayList pipeList = new ArrayList();
                 pipeList.add(new Csv2Array());------->1
                 pipeList.add(new Target2Label());---->2
                 pipeList.add(new Array2FeatureVector());---->3
                 pipeList.add(new PrintInputAndTarget());
                 return(new SerialPipes(pipeList));
                 }
                public static void main(String args[])
                 {
                 String [][][] trainingdata = new String [][][] {
                     {{ "1,0,1,1,1,1",
                     "1,1,1,1,1,1",
                     "1,1,1,1,1,1",
                     "1,1,1,1,1,1"}, {"data_bmp"}},
                     {{ "0,0,0,0,0,0",
                     "0,0,0,0,0,0",
                     "0,0,0,0,0,0"}, {"data_jpeg"}},
                     {{ "1,1,1,1,1,1",
                        "1,1,1,1,1,0",
                        "1,1,1,0,1,0"}, {"data_gif"}}
                       };
               ImportExample3 ob1=new ImportExample3();
                pipe=ob1.buidPipe();
              InstanceList instances = new InstanceList(pipe);
               for (int i = 0; i < 3; i++) {
                  try {
              instances.addThruPipe (new ArrayIterator
                        (trainingdata[i][0],trainingdata[i][1][0]));
                    }
                    catch (Exception e) { System.out.println(e);}
                      }
                       }
                         }
```
  * 1) Creates the Csv2Array with default constructor. This pipe will convert the provided data into Array
  * 2) All the provided targets like “data\_bmp”,”data\_jpeg” and “data\_gif” must be converted to Labels before applying Array2FeatureVector pipe.
  * 3) Array2FeatureVector will convert the array given by Csv2Array into Feature Vector.

### Output ###

```
name: array:0---------->Instance name
input: 0(0)=1.0--------->provided data
1(1)=0.0
2(2)=1.0
3(3)=1.0
4(4)=1.0
5(5)=1.0
target: data_bmp-------->Target or Label
name: array:1
input: 0(0)=1.0
1(1)=1.0
2(2)=1.0
3(3)=1.0
4(4)=1.0
5(5)=1.0
target: data_bmp
name: array:2
input: 0(0)=1.0
1(1)=1.0
2(2)=1.0
3(3)=1.0
4(4)=1.0
5(5)=1.0
target: data_bmp
name: array:3
input: 0(0)=1.0
1(1)=1.0
2(2)=1.0
3(3)=1.0
4(4)=1.0
5(5)=1.0
target: data_bmp
name: array:0
input: 0(0)=0.0
1(1)=0.0
2(2)=0.0
3(3)=0.0
4(4)=0.0
5(5)=0.0
target: data_jpeg
name: array:1
input: 0(0)=0.0
1(1)=0.0
2(2)=0.0
3(3)=0.0
4(4)=0.0
5(5)=0.0
target: data_jpeg
name: array:2
input: 0(0)=0.0
1(1)=0.0
2(2)=0.0
3(3)=0.0
4(4)=0.0
5(5)=0.0
target: data_jpeg
name: array:0
input: 0(0)=1.0
1(1)=1.0
2(2)=1.0
3(3)=1.0
4(4)=1.0
5(5)=1.0
target: data_gif
name: array:1
input: 0(0)=1.0
1(1)=1.0
2(2)=1.0
3(3)=1.0
4(4)=1.0
5(5)=0.0
target: data_gif
name: array:2
input: 0(0)=1.0
1(1)=1.0
2(2)=1.0
3(3)=0.0
4(4)=1.0
5(5)=0.0
target: data_gif
```

## Csv-2-Feature-Vector ##
It converts a string of the form feature\_1:val\_1 feature\_2 : val\_2 into a feature
vector.

### Implementation ###
  * 1)Csv2FeatureVector(): This constructor creates the object of Csv2FeatureVector and    sets the capacity of dataAlphabet to the 1000 words.
  * 2) Csv2FeatureVector(int capacity): This constructor allows user to set the capacity of dataAlphabet.


### Sample Code ###
```
Suppose the input data provided by the user is

          String [][][] trainingdata = new String [][][] {
                  {{ "1:1 0:1 1:1 1:1",
                  "1:2 1:3 1:4 1:5 1:7 1:7",
                   "1:3 1:3 1:4 1:5 1:6 1:7",
                  "1:2 1:6 1:4 1:5 1:6 1:8"}, {"data_bmp"}},
                  {{ "1:2 1:3 1:4 1:5 1:5 1:8",
                     "1:2 1:3 0:4 1:6 1:6 1:8",
                      "1:2 1:3 1:4 0:5 1:6 1:8"}, {"data_jpeg"}},
                     {{ "1:5 1:3 0:5 1:6 1:5 1:7",
                         "1:5 1:3 1:5 1:6 1:5 1:7",
                     "1:5 1:3 1:5 1:6 1:5 1:7"}, {"data_gif"}}
                     };

Note: 1:1 represents here the feature “1” occurs 1 time.

The code is similar to the code provided for the Csv2Array. Here we only added the Csv2FeatureVector
           pipeList.add(new Target2Label());
           pipeList.add(new Csv2FeatureVector());
           pipeList.add(new PrintInputAndTarget());

```

### Output ###
```
name: array:0 ---->Name of the Instance
input: 1(0)=3.0---> feature 1 occurred 3 times in array 0 and indexed at 0.
0(1)=1.0--->feature 0 occurred one time and indexed at 1
target: Africa----->target of the instance
name: array:1
input: 1(0)=28.0
target: africa
name: array:2
input: 1(0)=28.0
target: africa
name: array:3
input: 1(0)=31.0
target: africa
name: array:0
input: 1(0)=27.0
target: asia
name: array:1
input: 1(0)=21.0
0(1)=8.0
target: asia
name: array:2
input: 1(0)=20.0
0(1)=8.0
target: asia
name: array:0
input: 1(0)=24.0
0(1)=7.0
target: australia
name: array:1
input: 1(0)=31.0
target: australia
name: array:2
input: 1(0)=31.0
target: australia
```

## Target-String-To-Features ##

So far we have seen the pipes which converts the data into feature vector.But this pipe will convert the target field of the instance into feature vector.

### Implementation ###

  * 1)TargetStringToFeatures(): This pipe has just one constructor with no argument.

### Sample Code ###
```
   So far ,if we have converted the data into feature vector then the output will be    
   something like this

name: array:3
target: africa -->Target is not converted into feature vector here like data.
input: saraha(11)=2
dessert(12)=1.0
expanding(13)=1.0
So pipeList.add(new TargetStringToFeatures()) will convert the target to
```

### OUTPUT ###
```

target: africa(0)=1.0 -->Target is also converted into feature vector.
```

## Simple-Tagger-Sentence-2-Token-Sequence ##

It Converts an external encoding of a sequence of elements with binary features to a TokenSequence. If target processing is on (training or labeled test data),it extracts element labels from the external encoding to create a target LabelSequence.
Two external encodings are supported:
  * 1. A String containing lines of whitespace-separated tokens.
  * 2. a String[.md](.md)[.md](.md).
Both represent rows of tokens. When target processing is on, the last token in each rows the label of the sequence element represented by this row. All other tokens in the row, or all tokens in the row if not target processing, are the names of features that are on for the sequence element described by the row.(Source: Mallet javadoc)

### Implementation ###
  * 1) SimpleTaggerSentence2TokenSequence(): It is simple constructor which creates the object of the pipe.


### Sample Code ###
```
import cc.mallet.pipe.*;
import cc.mallet.types.*;
public class SimpleTaggerSentence2TokenSequence {
public static void main(String args[])
{
String trainingdata="on the plains of africa the lions roar";
Instance ob2=new Instance(trainingdata,null,"array-1",null);------>1
SimpleTaggerSentence2TokenSequence ob3=new SimpleTaggerSentence2TokenSequence();--->2
Instance i=ob3.pipe(ob2);---->3
PrintInputAndTarget piat=new PrintInputAndTarget();----->4
Instance i5=piat.pipe(i);
}
}
```
  * 1)A Mallet instance is created with data as the array trainingdata, Target as null(target processing is on),Instance name is “array-1” and Source is null.
  * 2)An object of the desired pipe is created.
  * 3)The already created instance is passed through the SimpleTaggerSentence2TokenSequence pipe.
  * 4)PrintInputAndTarget pipe is used to display the output.

### OUTPUT ###
```
name: array-1
input: TokenSequence [on feature(lions)=1.0 feature(the)=1.0 feature(africa)=1.0 
feature(of)=1.0 feature(plains)=1.0
feature(the)=1.0 feature(on)=1.0]---->1
Token#0:on feature(lions)=1.0 feature(the)=1.0 feature(africa)=1.0 feature(of)=1.0 
feature(plains)=1.0 feature(the)=1.0
feature(on)=1.0---->2
target: 0: roar (0)---->3
```
  * 1)The feature which comes first while reading the string(on) , put last in the token.
  * 2)The output Token 0 is generated which contains all the features provided by the string
  * 3)The target is always the last word of the provided string(roar)

Note: Here target is not explicitly provided by the user but is generated by the pipe
itself.


## Simple-Tokenizer ##

This pipe simply converts the string into tokens and take it as its input. It means this pips accept sequence of letters as tokens.

### Implementation ###
  * 1) SimpleTokenizer (java.io.File stopfile) :User provides the list of stopwords here  in the form of file.
  * 2) SimpleTokenizer (java.util.HashSet<java.lang.String> stoplist) :User provides the   list of stopwords here in the form of HashSet.
  * 3) SimpleTokenizer (int languageFlag) : If user wants to use the default Mallet list   than use 1.

  * 1 represent default English stop word list and zero represent empty stop words list.

**stopwords are discussed in Tutorial 1.**

### Sample Code ###
```
If the input provided is in this form
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
And this input is passed through SimpleTokenizer
pipeList.add(new SimpleTokenizer(1)); 
1- signifies that we uses Mallet default english words stop list.
```

### OUTPUT ###
```
name: array:0---->Name of the instance.
input: [plains, africa, lions, roar]----> The string is converted to tokens and taken as  
                                         input by the pipe.
target: Africa---> Target
name: array:1
input: [swahili, ngoma, means, dance]
target: africa
name: array:2
input: [nelson, tag, mandela, became, tag, president, south, africa]
target: africa
name: array:3
input: [saraha, dessert, saraha, expanding]
target: africa
name: array:0
input: [panda, bears, eat, bamboo]
target: asia
name: array:1
input: [china, s, one, child, policy, has, resulted, surplus, boys]
target: asia
name: array:2
input: [tigers, live, jungle]
target: asia
name: array:0
input: [home, kangaroos]
target: australia
name: array:1
input: [Autralian, s, beer, Foster]
target: australia
name: array:2
input: [Steve, Irvin, herpetologist]
target: australia
```

## Pipe-utils ##

This is a very special type of pipe which is used to combine two different pipes. This pipe has one static function which is used for this purpose.

### Implementation ###
  * 1)static Pipe concatenatePipes(Pipe p1,Pipe p2): This method is called in order to concatenate two pipes.

### Sample Code ###
```
import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.*;
import cc.mallet.types.*;
public class ImportExample6 {
static Pipe pipe=null;
static InstanceList instancelist = null;
public static void main(String args[])
{
String trainingdata[]={"on the plains of africa the lions roar”, “in swahili ngoma means 
to dance”, “the saraha dessert saraha expanding"};
Pipe p1=new SimpleTaggerSentence2TokenSequence();------>1
Pipe p2=new PrintInputAndTarget();------------->2
pipe=PipeUtils.concatenatePipes(p1, p2);---------->3
InstanceList instances = new InstanceList(pipe);
try {
instances.addThruPipe (new ArrayIterator (trainingdata));
} catch (Exception e) { System.out.println(e);}
}
}
1) First Pipe is created.
2) Second Pipe is created.
3) Both the pipes are concatenated using PipeUtils.
```

### Output ###
The output is exactly similar to what we got in SimpleTaggerSentence2TokenSequence.

## String-Add-New-Line-Delimiter ##
It is a simple pipe which add special text between lines to explicitly represent line breaks.

### Implementation ###
  * 1) StringAddNewLineDelimiter(java.lang.String delim): This constructor is called with the string argument which will be act as new line delimiter.

### Sample Code ###
```

import cc.mallet.pipe.*;
import cc.mallet.types.*;
public class stringaddnewlinedelimiter {
static Pipe pipe=null;
public static void main(String args[])
{
String trainingdata="on the plains of africa \r\n the lions roar";
Instance ob2=new Instance(trainingdata,null,"array-1",null);
StringAddNewLineDelimiter ob3=new StringAddNewLineDelimiter("@");--->1
Instance i=ob3.pipe(ob2);
PrintInputAndTarget piat=new PrintInputAndTarget();
Instance i5=piat.pipe(i);
}
}
1) An object of StringAddNewDelimiter is created with parameter “@” ,which will represent 
as new line character(/r/n);
```

### OUTPUT ###
```
name: array-1
input: on the plains of africa @ the lions roar ----> @ Represent new line.
target: <null>
```

## Char-Sequence-Remove-UUEncoded-Blocks ##

This pipe is used to remove the UU Encoded blocks.

### Implementation ###
  * 1)CharSequenceRemoceUUEncodedBlocks(): The pipe is created by calling no argument constructor.

Note: It remove lines from the given input string that begin with M and are 61
characters long. The start of every UUEncoded block is started with M. For further
reading about UUEncodedBlocks [please\_read](http://www.skypoint.com/members/gimonca/uuencode.html).

## String-List-2-Feature-Sequence ##
It converts the stringList to feature Sequence. It is to be noted that the data which will be given to this pipe as input must be in the form of String List.

### Implementation ###
  * 1)StringList2FeatureSequence():-This constructor simply creates the pipe and the     StringList is provided by the user.
  * 2)StringList2FeatureSequence(Alphabet dataDict):If this pipe is used in conjunction   with someone than the Alphabet of the previous pipe is passed as the parameter to this pipe.

### Sample Code ###
```
import java.util.ArrayList;
import cc.mallet.pipe.*;
import cc.mallet.types.*;
public class ImportExample2 {
protected int a=10;
static Pipe pipe=null;
public static void main(String args[])
{
ArrayList<String>ob1=new ArrayList<String>();---->String List is created
ob1.add("on the plains of africa the lions roar");--->Elements are added to the String 
                                                      list
ob1.add("in swahili ngoma means to dance");
Instance ob2=new Instance(ob1,null,"array-1",null);---->Instance is created with the 
                                                        above data

StringList2FeatureSequence ob3=new StringList2FeatureSequence();----->Pipe is created
Instance i=ob3.pipe(ob2);--->Data is passed through pipe.
PrintInputAndTarget piat=new PrintInputAndTarget();
Instance i5=piat.pipe(i);
}
}
```
### OUTPUT ###
```
name: array-1
input: 0: on the plains of africa the lions roar (0)-->data is converted into feature sequence.
1: in swahili ngoma means to dance (1)
target: <null>
```

## Feature-Sequence-Convolution ##
It construct word co-occurrence features from the original sequence using cominatoric,choosing two at a time.

### Implementation ###
  * 1)FeatureSequenceConvolution():This pipe contains the simple constructor with no arguments.

### Sample Code ###
```
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.*;
import cc.mallet.types.*;
public class ImportExample45 {
static Pipe pipe=null;
static InstanceList instancelist = null;
static FeatureCountPipe ob11=null;
static TokenSequence2FeatureSequence ob10=null;
public static void main(String args[])
{
String trainingdata="on the plains of africa the lions roar";
Instance i=new Instance(trainingdata,"africa","Instance-1",null);
Input2CharSequence i2cs=new Input2CharSequence();
Instance i1=i2cs.pipe(i);
Pattern tokenPattern =
Pattern.compile("[\\p{L}\\p{N}_]+");
CharSequence2TokenSequence cs2ts=new CharSequence2TokenSequence(tokenPattern);
Instance i2=cs2ts.pipe(i1);
TokenSequenceRemoveStopwords tsrs=new TokenSequenceRemoveStopwords(false, false);
Instance i8=tsrs.pipe(i2);
TokenSequence2FeatureSequence ts2fs=new TokenSequence2FeatureSequence();
Instance i3=ts2fs.pipe(i8);
FeatureSequenceConvolution fcp=new FeatureSequenceConvolution();---->Pipe is created
Instance i4=fcp.pipe(i3);--->An instance is passed through this pipe.
FeatureSequence2AugmentableFeatureVector fs2afv=new
FeatureSequence2AugmentableFeatureVector();
Instance i6=fs2afv.pipe(i4);
PrintInputAndTarget piat=new PrintInputAndTarget();
Instance i5=piat.pipe(i6);
}}

Note: FeatureSequenceConvolution pipe need FeatureSequence as input that’s why we have 
used it after TokenSequence2FeatureSequence.
```
### OUTPUT ###
```
name: Instance-1
input: plains(0)=1.0
africa(1)=1.0
lions(2)=1.0
roar(3)=1.0
0_1(4)=1.0 ----> All the combination of zero features with the other feature vector
0_2(5)=1.0
0_3(6)=1.0
1_2(7)=1.0
1_3(8)=1.0
2_3(9)=1.0
target: africa
```

## Feature-Vector-Conjunction ##
It adds the conjunction(&) in the features of the Feature Vector.

### Implementation ###
  * 1)FeatureVectorConjunction():It contains the simple constructor which requires no      parameter.

### Sample Code ###

The sample code for this pipe is similar to the sample code for FeatureSequenceConvolution.

Instead of using FeatureSequenceConvolution after Token2FeatureSequence we uses
FeatureVectorConjunction after FeatureSequence2AugmentableFeatureVector

```
FeatureSequence2AugmentableFeatureVector fs2afv=new FeatureSequence2AugmentableFeatureVector();
Instance i6=fs2afv.pipe(i3);
FeatureVectorConjunctions fvc=new FeatureVectorConjunctions();
Instance i9=fvc.pipe(i6);
```

### OUTPUT ###
```
name: Instance-1
input: plains
africa
lions
roar
plains_&_africa---->Conjunction is added to the features of the original feature vector
plains_&_lions
plains_&_roar
africa_&_lions
africa_&_roar
lions_&_roar
target: africa
```
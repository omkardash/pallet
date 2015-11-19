Table of Contents



# Introduction #

This Tutorial is in the continuation of the Data\_Import Tutorial 1, Data\_Import Tutorial 2 and Data\_Import\_Tutorial 3.This tutorial further explores the Data Import features of the Mallet.


# Details #
##### Note: Please go through the [Tutorial\_1](http://code.google.com/p/pallet/wiki/Tutorial_Data_Import),[Tutorial\_2](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial_2) and [Tutorial\_3](http://code.google.com/p/pallet/wiki/Data_Import_Tutorial_3). #####



The following pipes we are going to discuss here

  * 1) TokenSequenceRemoveNonAlpha
  * 2) TokenSequenceNGrams
  * 3) SGML2TokenSequence
  * 4) Target2FeatureSequence
  * 5) Target2LabelSequence
  * 6) TokenSequenceParseFeatureString
  * 7) TokenSequenceMatchDataAndTarget
  * 8) TokenSequence2FeatureSequenceWithBigrams
  * 9) TargetRememberLastLabel
  * 10) SourceLocation2TokenSequence

## Token-Sequence-Remove-Non-Alpha ##

This pipe removes all the non Alphabet words from the Token Sequence. For eg if the
Token Sequence contains tokens like abc123,then these words are removed from the Token
Sequence.

### Implementation ###

  * 1) TokenSequenceRemoveNonAlpha(): This is simple constructor which creates the object of TokenSequenceRemoveNonAlpha . This object is then use to process the tokens.
  * 2) TokenSequenceRemoveNonAlpha(boolean markDeletions): If user wants to mark the deletions of the non Alphabet words to the serialized object,then call this constructor with true value.The deletion of the tokens is marked in the serialized object.

### Sample Code ###

Note: This pipe uses the Tokens as the input ,so please convert the raw into the Token
before applying this pipe.

```
String trainingdata="on the plains of africa the lions123 roar";---->1
TokenSequenceRemoveNonAlpha fcp=new TokenSequenceRemoveNonAlpha();--->2
Instance i4=fcp.pipe(i8);--->3
```

  * 1) String trainingdata represents the raw data.
  * 2) An object of TokenSequenceRemoveNonAlpha is created.
  * 3)The already processed Instance is passed through this pipe.

Note: Instance i8 in the sample code doesn’t contain the raw data . It contains the data
already processed in the form of Tokens by some pre used pipes.


### Sample Output ###
```
The output contains tokens
Plains
Africa
Roar
```

Note: we used TokenSequenceRemoveStopwords before using this pipe. Therefore stopwords
are not converted into the tokens. But the output of TokenSequenceRemoveStopwords
still contains token “lions123” which will be removed by this pipe.


## Token-Sequence-N-Grams ##

It converts the token sequence in the data fields to the token sequence of N grams.

### Implementation ###

  * 1) TokenSequenceNGrams (int [.md](.md) sizes): This pipe creates the object of the   TokenSequenceNGrams with the integer array.
    * A)int[.md](.md) sizes: User can specify here the size of the NGrams. If user wants the      Token sequence to be of the 2 and 3 grams then passed {2,3} to the constructor.

### Sample Code ###
```
String trainingdata="on the plains of africa the lions123 roar";---->1
int a[]={3};---->2
TokenSequenceNGrams tsng=new TokenSequenceNGrams(a);---->3
Instance i18=tsng.pipe(i4);------>4
```
  * 1)String trainingdata is the raw data
  * 2)User specified here the size of the grams .
  * 3)An object of the TokenSequenceNGrams is created
  * 4)The already process data is passed through this pipe.

Note: This pipe also expects input in the form of tokens so please do not use this pipe to process raw data. The data must be passed through some pipes which convert the raw data into the token sequence.


### Sample Output ###
```
plains_africa_roar
```

Note: We also used TokenSequenceRemoveStopwords and TokenSequenceRemoveNonAlpha before using this pipe. Therefore tokens don’t contain stopwords and non alphabetic words .

The input to this pipe was three tokens “plains”,”africa” and “roar”. This pipe processes the data and converts it into 3 grams tokens and hence the output is plains\_africa\_roar.

Suppose we want 2 grams tokens then the output is
```
plains_africa
africa_roar

```

## SGML-2-Token-Sequence ##
It converts a string containing simple SGML tags into a dta TokenSequence of words, paired with a target TokenSequence containing the SGML tags in effect for each word.It does not handle nested SGML tags, nor gracefully handle malformed SGML.**(Source : Mallet javadoc)**

### Implementation ###

  * 1) SGML2TokenSequence():This constructor process all the data and take backgroundTag as “0”
  * 2) SGML2TokenSequence(CharSequenceLexer lexer, String backgroundTag): This constructor takes the regular expression in the form of CharSequenceLexer to tell what kind of data must be taken as the input for this pipe.
    * ”backgroundTag” represents the tag which will be used as the Tag in the token     sequence of the target ,to represent the data which doesn’t come under SGML tags.

  * 3) SGML2TokenSequence(CharSequenceLexer lexer, String back, boolean saveSource): This constructor is almost similar to the above one except that is has one more arguments which must be true if you want to save the source fields (Mallet instances has four fields-one of them is a source field)

  * 4) SGML2TokenSequence(String regex,backgroundTag): This is similar to the constructor  2 except it takes regular expression in the form of String


### Sample Code ###
```
import cc.mallet.pipe.*;
import cc.mallet.types.*;
public class ImportExample45 {
static Pipe pipe=null;
public static void main(String args[])
{
String trainingdata="<abc> on the plains of </abc> africa the <a> lions123 </a> roar";-->1
Instance i=new Instance(trainingdata,"africa","Instance-1",null);
Input2CharSequence i2cs=new Input2CharSequence();
Instance i1=i2cs.pipe(i);
SGML2TokenSequence cs2ts=new SGML2TokenSequence("[\\p{L}\\p{N}_]+","pallet");---->2
//SGML2TokenSequence cs2ts=new SGML2TokenSequence();---->3
Instance i2=cs2ts.pipe(i1);
TokenSequence2FeatureSequence ts2fs=new TokenSequence2FeatureSequence();
Instance i3=ts2fs.pipe(i2);
FeatureSequence2AugmentableFeatureVector fs2afv=new
FeatureSequence2AugmentableFeatureVector();
Instance i6=fs2afv.pipe(i3);
PrintInputAndTarget piat=new PrintInputAndTarget();
Instance i5=piat.pipe(i6);}}
```

  * 1)Trainingdata is provided in the form of String and also processed through Input2charSequence.

  * 2)SGML constructor is used with two arguments "[\\p{L}\\p{N}_]+" : It means that take all the characters as the input.
    * Pallet: The data which is not inside SGML tags have a target tag as “Pallet”_

  * 3)It is commented statement but it can be used instead of above statement to show that how SGML2TokenSequence pipe behaves when no argument is provided by the user.


### Sample Output ###
```
name: Instance-1
input: on(0)=1.0---------------------->Data contains no SGML tags.
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
lions123(5)=1.0
roar(6)=1.0
target: TokenSequence [abc, abc, abc, abc, pallet, pallet, a, pallet]------------->Token Sequence
Token#0:abc
Token#1:abc
Token#2:abc
Token#3:abc
Token#4:pallet
Token#5:pallet
Token#6:a
Token#7:pallet
```
  * 1)Data which is inside SGML tags are has target sequence corresponding to the name of the tag.
```
For eg "<abc> on the plains of </abc>" is having ”abc” tag in the target field.
```
  * 2)The data which is not inside any of SGML tags have a background tag in the target field provided by the user(Pallet).

Note: If commented statement is used instead of what we have used in the sample source
code then the output is
```
name: Instance-1
input: on(0)=1.0
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
lions(5)=1.0
roar(6)=1.0
target: TokenSequence [abc, abc, abc, abc, O, O, a, O]
Token#0:abc
Token#1:abc
Token#2:abc
Token#3:abc
Token#4:O---------->Default background Tag
Token#5:O
Token#6:a
Token#7:O
```
## Target-2-Feature-Sequence ##

It converts the token sequence in the target field into the Feature Sequence.

### Implementation ###
  * 1)Target2FeatureSequence(): It is simple constructor which converts the token sequence in the target field into the Feature sequence.

Note: It is to be noted that ,this pipe expects that target string is in the form of token sequence.Please convert the target into token sequence before applying this pipe.


### Sample Code ###
Taking our previous sample code we add Target2FeatureSequence pipe after SGML2TokenSequence pipe.

```
import cc.mallet.pipe.*;
import cc.mallet.types.*;
public class ImportExample54{
static Pipe pipe=null;
public static void main(String args[])
{ 
String trainingdata="<abc> on the plains of </abc> africa the <a> lions123 roar</a>";
Instance i=new Instance(trainingdata,"africa","Instance-1",null);
Input2CharSequence i2cs=new Input2CharSequence();
Instance i1=i2cs.pipe(i);
SGML2TokenSequence cs2ts=new SGML2TokenSequence();
Instance i2=cs2ts.pipe(i1);
Target2FeatureSequence t2fs=new Target2FeatureSequence();
Instance i8=t2fs.pipe(i2);---->1
TokenSequence2FeatureSequence ts2fs=new TokenSequence2FeatureSequence();
Instance i3=ts2fs.pipe(i8);
FeatureSequence2AugmentableFeatureVector fs2afv=new
FeatureSequence2AugmentableFeatureVector();
Instance i6=fs2afv.pipe(i3);
PrintInputAndTarget piat=new PrintInputAndTarget();
Instance i5=piat.pipe(i6);}}
```
  * 1)Target2FeatureSequence pipe is created and data is processed through the pipe.


### Sample Output ###
```
name: Instance-1
input: on(0)=1.0
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
lions(5)=1.0
roar(6)=1.0
target: 0: abc (0)------------->Target is indexed and hence converted into
1: abc (0) feature sequence.
2: abc (0)
3: abc (0)
4: O (1)
5: O (1)
6: a (2)
7: a (2)
```
## Target-2-Label-Sequence ##
It a simple pipe which converts the data in the target field into LabelSequence. LabelSequence is similar to the feature sequence. Its usage is similar to Target2FeatureSequence.It has just one constructor with no arguments. Its output is also similar to the Target2FeatureSequence.

Note: Just replace the Target2FeatureSequence to Target2LabelSequence in the above code.

## Token-Sequence-Parse-Feature-String ##

It parses Token Sequence and convert it into Feature String. It adds each string as a Feature to the token.If your data consists of feature/value pairs (eg height=10.7 width=3.6 length=1.7), use new TokenSequenceParseFeatureString(true, true, "="). This format is typically used for sparse data,in which most features are equal to 0 in any given instance.

If your data consists only of values, and the position determines which feature the value is for (eg 10.7 3.6 1.7), use new TokenSequenceParseFeatureString(true). This format is typically used for data that has a small number of features that all have non-zero values most of the time.

If your data is in the form of named binary indicator variables (eg yellow quacks has\_webbed\_feet), use the constructor new TokenSequenceParseFeatureString(false). Each token will be interpreted as the name of a feature, whose value is 1.0.**(Source: Mallet javadoc)**

Things will get clearer when one sees the usage of the pipe.

### Implementation ###
  * 1) TokenSequenceParseFeatureString(boolean realvalued):
  * 2) TokenSequenceParseFeatureString(boolean realvalued, boolean featurename):
  * 3) TokenSequenceParseFeatureString(boolean realvalued, boolean
> > feature\_name,String namevalue Seperator):

If realValued is true ,then pipe treat the position in the list as the feature name and
value as double. Otherwise feature name is the String itself and the value is 1.0


### Sample Code ###
```
import java.util.regex.*;
import cc.mallet.pipe.*;
import cc.mallet.types.*;
public class ImportExample76{
static Pipe pipe=null;
public static void main(String args[])
{
//String trainingdata="width=10 height=11 length=12";------------------>1
String trainingdata="10 11 12 ";--------------------------------------------->2
Instance i=new Instance(trainingdata,"africa","Instance-1",null);
Input2CharSequence i2cs=new Input2CharSequence();
Instance i0=i2cs.pipe(i);
Pattern tokenPattern =
Pattern.compile("[\\p{L}\\p{N}_\\=]+");
CharSequence2TokenSequence cs2ts= new CharSequence2TokenSequence(tokenPattern);
Instance i2=cs2ts.pipe(i0);
TokenSequenceParseFeatureString tspf=new TokenSequenceParseFeatureString(false);----->3
// TokenSequenceParseFeatureString tspf=new TokenSequenceParseFeatureString(true,true,"=");------->4
Instance i120=tspf.pipe(i2);
PrintInputAndTarget piat=new PrintInputAndTarget();
Instance i5=piat.pipe(i120);
}
}
```

Here we use training data as “10 11 12” and TokenSequenceParseFeatureString tspf=new
TokenSequenceParseFeatureString(false);----->3

Now realvalued is false here ,so feature name is the string itself and the value is 1.0


### Sample Output ###
```
name: Instance-1
input: TokenSequence [10 feature(10)=1.0 span[0..2], 11 feature(11)=1.0
span[3..5], 12 feature(12)=1.0 span[6..8]]
Token#0:10 feature(10)=1.0 span[0..2]
Token#1:11 feature(11)=1.0 span[3..5]
Token#2:12 feature(12)=1.0 span[6..8]
target: africa


Note: Now here if you see 10 is a feature name(String itself) and the value given to it 
is 1.0.If we have used realValued as true then 

TokenSequenceParseFeatureString tspf=new TokenSequenceParseFeatureString(true);
then the output will be

name: Instance-1
input: TokenSequence [10 feature(Feature#0)=10.0 span[0..2], 11
feature(Feature#0)=11.0 span[3..5], 12 feature(Feature#0)=12.0 span[6..8]]
Token#0:10 feature(Feature#0)=10.0 span[0..2]
Token#1:11 feature(Feature#0)=11.0 span[3..5]
Token#2:12 feature(Feature#0)=12.0 span[6..8]
target: africa

Note: Now here if you see the feature name is Feature#0 and the value is the string 
provided by the user.Now if we have used the trainingdata as

String trainingdata="width=10 height=11 length=12";
TokenSequenceParseFeatureString tspf=new TokenSequenceParseFeatureString(true,true,"=");
Then it means realValued is true and specifyFeature name is true and name value separator is “=”

The output would be
name: Instance-1
input: TokenSequence [width=10 feature(width)=10.0 span[0..8], height=11
feature(height)=11.0 span[9..18], length=12 feature(length)=12.0
span[19..28]]
Token#0:width=10 feature(width)=10.0 span[0..8]
Token#1:height=11 feature(height)=11.0 span[9..18]
Token#2:length=12 feature(length)=12.0 span[19..28]
target: africa

Now here string is converted into featurename and value is allotted to them (what they 
had in the original string(separated by equal “=”).)
```

## Token-Sequence-Match-Data-And-Target ##

It runs a regular expression on the text of each token .The part of the string which matches will be converted into Target (or data) and the rest of the string will be data(or Token).

### Implementation ###
  * 1)TokenSequenceMatchDataAndTarget(java.util.regex.Pattern, int datagroup ,int targetGroup):java.util.regex.Pattern: Regular expression which is used to process the data.
    * Datagroup:Data group is set on the basis of “matcher.group(datagroup)”.
    * Targetgroup: Target group is set on the basis of “matcher.group(Targetgroup)”.

Note: group is the predefined java function associated with Matcher class.
  * 2) TokenSequenceMatchDataAndTarget(java.lang.String regex, int datagroup, int
targetGroup): It user wants to provide regex Pattern in the form of string then use
this constructor.

### Sample Code ###
```
import java.util.regex.*;
import cc.mallet.pipe.*;
import cc.mallet.types.*;
public class ImortExample89 {
static Pipe pipe=null;
public static void main(String args[])
{
CharSequence[] dataWithTags = new CharSequence[] {
"PLAINS of africa the lions roar",
"SWAHII ngoma means to dance",
"SARAHA dessert saraha expanding"};
Instance i=new Instance(dataWithTags,null,"Instance-1",null);
CharSequenceArray2TokenSequence i2cs=new CharSequenceArray2TokenSequence ();
Instance i78=i2cs.pipe(i);
TokenSequenceMatchDataAndTarget tsmdt=new
TokenSequenceMatchDataAndTarget(Pattern.compile ("([A-Z]+) (.*)"),2,1); ----->1
Instance i2=tsmdt.pipe(i78);-------->2
TokenSequence2FeatureSequence ts2fs=new TokenSequence2FeatureSequence();
Instance i3=ts2fs.pipe(i2);
FeatureSequence2AugmentableFeatureVector fs2afv=new
FeatureSequence2AugmentableFeatureVector();
Instance i6=fs2afv.pipe(i3);
PrintInputAndTarget piat=new PrintInputAndTarget();
Instance i5=piat.pipe(i6);
}
}
```
  * 1)Show the implementation of the TokenSequenceMatchDataAndTarget pipe. The constructor is called with
    * a) ("([A-Z]+) (.**)") : It return everything which comes in capital letter.
    * b)matcher.group(2): This group will be converted into data.
    * c)matcher.group(1): This group will be converted into Target**


### Sample Output ###
```
name: Instance-1
input: of africa the lions roar(0)=1.0
ngoma means to dance(1)=1.0
dessert saraha expanding(2)=1.0
target: TokenSequence [PLAINS, SWAHII, SARAHA]
Token#0:PLAINS--Capital letter words are converted into Tokens and rest will
Token#1:SWAHII remain as data.
Token#2:SARAHA
```
## Token-Sequence-2-Feature-Sequence-With-Bigrams ##

This pipe converts the token sequence in the data field of each instance to a feature
sequence that preserves bigram information.

### Implementation ###
  * A)TokenSequence2FeatureSequenceWithBigrams():It creates the new instance of the Data
alphabet.
  * B) TokenSequence2FeatureSequenceWithBigrams(Alphabet datadict):If user already have
Data alphabet than should use this constructor.
  * C)TokenSequence2FeatureSequenceWithBigrams(Alphabet dataDict,Alphabet bigramAlphabet): If user is already having bigram Alphabet than should go for this constructor

This pipe also contain one method name as getBigramAlphabet which is used to get the
current Bigram Alphabet


### Sample Code ###

we can used this pipe just after the TokenSequenceMatchDataAndTarget(which we described
earlier) with the same training data. For eg.
```
TokenSequenceMatchDataAndTarget tsmdt=new TokenSequenceMatchDataAndTarget(Pattern.compile 
("([A-Z]+)(.*)"),2,1);

Instance i2=tsmdt.pipe(i78);
TokenSequence2FeatureSequenceWithBigrams ts2fs=new
TokenSequence2FeatureSequenceWithBigrams();
Instance i3=ts2fs.pipe(i2);
Alphabet A=ts2fs.getBigramAlphabet();
System.out.println(A);
```

### Sample Output ###
```
The output would be

of africa the lions roar_ngoma means to dance
ngoma means to dance_dessert saraha expanding
dessert saraha expanding_is the bad diesease

Note: The output from the TokenSequenceMatchDataAndTarget is
input: of africa the lions roar---->Token1
ngoma means to dance----->Token2
dessert saraha expanding----->Token3
TokenSequence2FeatureSequenceWithBigrams pipe will combine the consecutive words by “_”
in order to preserve the bigram information.
```

## Target-Remember-Last-Label ##

It remembers the last non background label,for each position in the target. It replaces the target with a LabelSequence where column 0 is the original labels and column1 is the last label.

### Implementation ###
  * 1)TargetRememberLastLabel (): It simply creates the object of TargetRememberLastLabel and replaces the target with a LabelSequence where coloumn0 is original label and coloum1 is the label of the of the last row.
  * 2)TargetRememberLastLabel(java.lang.String backgroundlabel , Boolean offset):
java.lang.String backgroundlabel : The user can enter the background label here.
    * Boolean offset: It determines how the memory and base sequence will be aligned.


### Sample Code ###
```
import cc.mallet.pipe.*;
import cc.mallet.types.*;
public class ImportExample92 {
static Pipe pipe=null;
public static void main(String args[])
{
String trainingdata="<abc> on the plains of </abc> africa the <a> lions123 </a> roar";
Instance i=new Instance(trainingdata,"africa","Instance-1",null);
Input2CharSequence i2cs=new Input2CharSequence();
Instance i1=i2cs.pipe(i);
SGML2TokenSequence cs2ts=new SGML2TokenSequence("[\\p{L}\\p{N}_]+","pallet");
Instance i2=cs2ts.pipe(i1);
Target2LabelSequence t2ls=new Target2LabelSequence();
Instance i045=t2ls.pipe(i2);
TargetRememberLastLabel trll=new TargetRememberLastLabel() ; //Pipe is used.
Instance i055=trll.pipe(i045);
TokenSequence2FeatureSequence ts2fs=new TokenSequence2FeatureSequence();
Instance i3=ts2fs.pipe(i055);
FeatureSequence2AugmentableFeatureVector fs2afv=new
FeatureSequence2AugmentableFeatureVector();
Instance i6=fs2afv.pipe(i3);
PrintInputAndTarget piat=new PrintInputAndTarget();
Instance i5=piat.pipe(i6);
}
}
```

### Sample Output ###
```
name: Instance-1
input: on(0)=1.0
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
lions123(5)=1.0
roar(6)=1.0
target: LabelsSequence:
0: abc abc
1: abc abc
2: abc abc
3: abc abc
4: pallet abc
5: pallet pallet
6: a pallet
7: pallet a


Note: Here in label Sequence of target column 1 represents the original target of the 
data and column 2 represents the last data’s labels.

If instead of no argument constructor of the pipe ,the following constructor is used TargetRememberLastLabel trll=new TargetRememberLastLabel("pallet", true);
then the output would be

name: Instance-1
input: on(0)=1.0
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
lions123(5)=1.0
roar(6)=1.0
target: LabelsSequence:
0: abc abc
1: abc abc
2: abc abc
3: abc abc
4: pallet abc
5: pallet abc------>”Pallet” is not used as the last label.
6: a abc
7: pallet a

Note: Here the background label “Pallet” is not used as the last remembered label.
If the pipe is used as 
TargetRememberLastLabel trll=new TargetRememberLastLabel("pallet", false);

name: Instance-1
input: on(0)=1.0
the(1)=2.0
plains(2)=1.0
of(3)=1.0
africa(4)=1.0
lions123(5)=1.0
roar(6)=1.0
target: LabelsSequence:
0: abc abc
1: abc abc
2: abc abc
3: abc abc
4: pallet abc
5: pallet abc
6: a a --------->See the change from the previous output. Here also “Pallet” is not used 
                 as label. As soon as new label encountered the LastRememberTable is 
                 changed to the new label.
7: pallet a  
```

## Source-Location-2-Token-Sequence ##

It reads data from the file or BufferedRead and produces the TokenSequence.

===Implementation
  * 1)SourceLocation2TokenSequence(CharSequenceLexer lexer): User should provide the
regular expression in this constructor in the form of CharSequenceLexer instance.


> There are couple of important methods in this pipe.

  * 1)public TokenSequence pipe(java.io.File file) throws java.io.FileNotFoundException,
java.io.IOException : It takes the file name on which the pipe has to operate.
  * 2)public TokenSequence pipe(java.io.BufferedReader br) throws java.io.IOException :
: It takes the BufferedReader on which the Pipe has to operate.


### Sample Code ###
```
import java.io.*;
import cc.mallet.pipe.*;
import cc.mallet.types.*;
import cc.mallet.util.CharSequenceLexer;
public class ImportExample94 {
static Pipe pipe=null;
public static void main(String args[])
{
File ob1=new File("sci_spec.txt");
CharSequenceLexer csl=new CharSequenceLexer("[\\p{L}\\p{N}_]+");
Instance i=new Instance(ob1,"sci_spec.txt","Instance-1",null);
SourceLocation2TokenSequence i2cs=new SourceLocation2TokenSequence(csl);
Instance i1=i2cs.pipe(i);
TokenSequence2FeatureSequence ts2fs=new TokenSequence2FeatureSequence();
Instance i3=ts2fs.pipe(i1);
FeatureSequence2AugmentableFeatureVector fs2afv=new
FeatureSequence2AugmentableFeatureVector();
Instance i6=fs2afv.pipe(i3);
PrintInputAndTarget piat=new PrintInputAndTarget();
Instance i5=piat.pipe(i6);
}
}

```
### Sample Output ###
The output will contain the raw data contain in the file”sci\_spec.txt “in token sequence
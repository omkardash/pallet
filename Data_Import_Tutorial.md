Table of Contents



# Introduction #

Mallet Uses different types of pipes to process the raw data and convert it into Mallet instances. Later on, Mallet uses these instances to train the classifiers. Converting the raw data into Mallet instances is the first step towards classification of data. Here in this Tutorial we are going to see different pipes and their working, provided by Mallet.


### Sample Code ###
```
The pipeList is created in Mallet by calling CreatePipe method. The CreatePipe method 
must be called in the following way.

CreatePipe(new Input2CharSequence("UTF-8"), 
				new CharSequence2TokenSequence(tokenPattern), 
				true, 
				new TokenSequenceRemoveStopwords(true, true), 
				new TokenSequence2FeatureSequence(), 
				new Target2Label(), 
				new FeatureSequence2FeatureVector(false), 
				true);


This function will create the Pipelist by adding the instances of all the pipes.
_________________________________________________________________________________________

```
Note: The first and second argument are must for this method. We can provide null for 4,5,6 and 7 arguments. This depends on the user ,that which pipes he/she wants to use in the application.


The following pipes are used by the application. We will look into the each of the pipe
one by one and explains what each one do.


## Input-2-Char-Sequence ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Input2CharSequence.html)

It is a Pipe that can read data from various kinds  of sources and convert the given input into character sequence.

### Implementation ###

  * a) public Input2CharSequence (): It is default constructor.If user does not specify any encoding then this constructor will be called.

  * b)public Input2CharSequence( String encoding ) : Specifies the encoding form of the data.

For e.g.: Input2CharSequence("UTF-8"),tell the data to be read is in UTF-8  format.



## Char-Sequence-2-Token-Sequence ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/CharSequence2TokenSequence.html)

It is pipe that tokenizes a character sequence. It expects a character sequence and
converts into tokens. For e.g. after passing through Input2CharSequence you have following data.
```
Der T-40 war ein sowjetischer leichter Schwimmpanzer zur Zeit des Zweiten Weltkrieges. 


This data will be converted into following tokens after passing through CharSequence2TokenSequenc 
             

Token#0:Der  span[0..3] 
Token#1:T  span[4..5]
Token#2:40  span[6..8]
Token#3:war  span[9..12]
Token#4:ein  span[13..16]
Token#5:sowjetischer  span[17..29]
Token#6:leichter  span[30..38]
Token#7:Schwimmpanzer  span[39..52]
Token#8:zur  span[53..56]
Token#9:Zeit  span[57..61]
Token#10:des  span[62..65]
Token#11:Zweiten  span[66..73]
Token#12:Weltkrieges  span[74..85]
```

### Implementations ###
```
Pattern tokenPattern = Pattern.compile("[\\p{L}\\p{N}_]+")

This Pattern will select all the words which includes alphabets and Numericals.
```

For Pattern class please look **[here](http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Pattern.html)**

The Pattern parameter which is passed as argument is used as qualifier. Only those words which can qualify for the Pattern parameter is converted into the tokens.


  * a) public CharSequence2TokenSequence (String regex):-The string parameter will be    converted into Pattern. If one wants to provide the string directly into the   CharSequence2TokenSequence constructor without using Pattern.compile then should use this constructor. For e.g.
```
   CharSequence2TokenSequence("[\\p{L}\\p{N}_]+") 
```


  * b) public CharSequence2TokenSequence (Pattern regex):-We used this constructor of    CharSequence2TokenSequence in our application.
```
   Pattern tokenPattern = Pattern.compile("[\\p{L}\\p{N}_]+");
   CharSequence2TokenSequence(tokenPattern)
```

  * c) public CharSequence2TokenSequence ():- If no pattern is given by the user ,then   the default Pattern will be taken by the pipe.  The default Pattern is \\p{Alpha}+ which means any alphabet which can occur one or more times.


  * d) public CharSequence2TokenSequence (CharSequenceLexer lexer):- CharSequence2TokenSequence constructor in reality calls the constructor of CharSequenceLexer for deciding which word will be converted into token.
```
    Pattern tokenPattern = Pattern.compile("[\\p{L}\\p{N}_]+");
    CharSequenceLexer ob1=new CharSequenceLexer(tokenPattern);
    CharSequence2TokenSequenc(tokenPattern);

```

## Token-Sequence-Lower-case ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TokenSequenceLowercase.html)

It is pipe which converts all the tokens received through previous pipes to lower case.
For e.g.
```
      Token#0:Der  span[0..3]     ------>     Token#0:der  span[0..3]
      Token#1:T  span[4..5]                   Token#1:t  span[4..5]    
```
### Implementations ###
If you want your sequence to be converted into lowercase then pass true. If true is passed then the following code is executed internally.
```
TokenSequenceLowercase lowercase =  new TokenSequenceLowercase ();
```
The object of TokenSequenceLowercase class is created and is added to the pipe list

  * a) public TokenSequenceLowercase(): It simply create the object of the TokeSequenceLowercase which is added to the pipe list.

## Token-Sequence-Remove-Stopwords ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TokenSequenceRemoveStopwords.html)

It is the pipe which is used to remove the stop words from the tokens. The program
contains a list of stopword(which are basically useless). If tokens contains these  stopwords than that tokens are removed from  the TokenList. For e.g. the stopword list of English language contains
```
                “a",
		"able",
		"about",
		"above",
		"according",
		"accordingly",
		"across",
		"actually",
		"after",
		"afterwards",
		"again",
		"against",
		"all"
       //stopwords for French, added by Limin Yao
		"fut",
		"S",
		"ces",
		"ral",
		"new",
		"tr",
		"apr",
		"sous",
		"ans""
```
Note: This is not complete list which is used by Mallet.

If our TokenList contains words which are also there in stopword list than that  words are removed from the TokenList.

### Implementations ###

  * 1)public TokenSequenceRemoveStopwords (boolean caseSensitive,boolean markDeletions) TokenSequenceRemoveStopwords(true, true)):-
    * CaseSensitve: If this is true than the removing of the stopwords are CaseSensitive.
    * MarkDeletions: If it is true then it marks the token which is deleted.

  * 2)public TokenSequenceRemoveStopwords (boolean caseSensitive):- If this is true than the removing of the stopwords are CaseSensitive

  * 3)public TokenSequenceRemoveStopwords():-This will in turn call the above constructor with the value false.

  * 4)public TokenSequenceRemoveStopwords(File stoplistFile, String encoding, boolean includeDefault,boolean caseSensitive, boolean markDeletions):-
    * stopListFile: If users want to provide own set of stopwords list then pass the name of file which contain the list of stopwords.
    * Encoding: Pass the encoding of the file for e.g. (“UTF-8”)
    * includeDefault: If user also wants to use the Mallet default stopword list then pass true else false.
    * CaseSensitve: If this is true than the removing of the stopwords are caseSensitive.Otherwise the stopwords are removed from the tokens irrespective of their cases.
    * MarkDeletions: If it is true then it marks the token which is deleted.


## Token-Sequence-2-Feature-Sequence ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TokenSequence2FeatureSequence.html)

It converts the Token Sequence to FeatureSequence.It indexes the each token.
```
0: der (0)------>         Index of the Word. If this word occur somewhere in this  
1: 40 (1)                 file or some other file than it is also indexed with 0. 
2: war (2)                 
3: ein (3)
4: sowjetischer (4)
5: leichter (5)
6: schwimmpanzer (6)
7: zur (7)
8: zeit (8)
9: zweiten (9)
10: weltkrieges (10)
11: die (11)
12: damalige (12)
13: sowjetische (13)
14: klassifikation (14)
15: ordnete (15)
16: ihn (16)
17: als (17)
18: kleinen (18)
19: panzer (19)
20: ein (3)
21: das (20)
22: konstruktionsbÃ¼ro (21)
23: werks (22)
24: nr (23)
25: 37 (24)
26: moskau (25)
27: entwickelte (26)
28: den (27)
29: 40 (1)                                         
30: der (0)  ------------------- "Der" is again indexed with 0.   
31: ersten (28)
32: hÃ¤lfte (29)
```
### Implementations ###

  * 1) public TokenSequence2FeatureSequence ():-Create an instance of Alphabet class by itslef.Integers are assigned consecutively, starting at zero ,as objects(tokens) are added to the Alphabet.
Objects cannot be deleted from the Alphabet and thus the integers are never reused. In a simple document classification usage ,each unique word in a  document would be unique entry in the Alphabet with unique features added to it.

Therefore TokenSequence2featureSequence assigned unique index to each unique words.


  * 2) public TokenSequence2FeatureSequence (Alphabet dataDict):-If user wants to use     own Alphabet instance then uses this constructor.


## Target-2-Label ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Target2Label.html)

Convert Object in the target field into the label. For e.g.
```
    The object into the target field for sample code is 

    Labels              Data(which in reality is in the forms of Tokens)

    "africa"    for      "on the plains of africa the lions roar",
		       "in swahili ngoma means to dance",
	                   "nelson mandela became president of south africa",
		       "the saraha dessert is expanding"

 
    "asia"     for        "panda bears eat bamboo",
	                    "china's one child policy has resulted 
                                 in a surplus of boys",
		          "tigers live in the jungle"



   "australia"   for      "home of kangaroos",
		          "Autralian's for beer - Foster",
		          "Steve Irvin is a herpetologist"
           
     Note: Labels of instances are used at the time of classification.  

```
### Implementations ###

  * 1) public Target2Label (): Calls the Third constructor with the values as  dataAlphabet as  null  and an instance of LabelAlphabet created implicitly. Here we assumes that an object of  dataAlphabet is already created by previous pipes.

  * 2)public Target2Label (LabelAlphabet labelAlphabet): If user want to create it is own instance of LabelAlphabet (which indexes Labels)then use this constructor.

  * 3)public Target2Label (Alphabet dataAlphabet, LabelAlphabet labelAlphabet): If user  want to create it is  own objects of DataAlphabet and LabelAlphabet then uses this constructor.

Note in this case user assumes that dataAlpabet object is not already created implicitly
by the Mallet.

LabelAlphabet is similar class like Alphabet which is used to map arbitrary objects like
Labels object into integers and back. So Labels are also indexed like the data.

## Feature-Sequence-2-Feature-Vector ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/FeatureSequence2FeatureVector.html)

Convert a data field from Feature Sequence to Feature vector. This class does not insist
on getting its own Alphabet  because it rely on  getting from the FeatureSequence input.
```
For e.g.  Feature vector counts the number of times index 0  occurred in a featureSequence
 . 

  [0]=11.0 

Note : The index 0 is related to the word “der”. So it means that “der” occurred 11 times in a feature sequence.
```

### Implementations ###

  * 1) public FeatureSequence2FeatureVector (): It calls the below constructor passing the false value.
  * 2) public FeatureSequence2FeatureVector (boolean binary):
  * 3) PrintInputAndTarget: Print the data and Target field of each instance. For e.g.   The output of the sample code.

```

   
name: array:0 -------------> Represent Name of the Instance
target: africa-------------> Represent target or label of the Instance
input: plains(0)=1.0 ------->Represent Data in the Instance.              
africa(1)=1.0
lions(2)=1.0
roar(3)=1.0

name: array:1
target: africa
input: swahili(4)=1.0
ngoma(5)=1.0
means(6)=1.0
dance(7)=1.0-------------->Number of times index(7) or Word(dance) occurs in the data.
                 
name: array:2
target: africa
input: africa(1)=1.0
nelson(8)=1.0
mandela(9)=1.0
president(10)=1.0
south(11)=1.0------------->A Unique index bounded with each Unique Word.
               
                                          
name: array:3
target: africa
input: saraha(12)=1.0                          
dessert(13)=1.0
expanding(14)=1.0

name: array:0
target: asia
input: panda(15)=1.0
bears(16)=1.0
eat(17)=1.0
bamboo(18)=1.0

name: array:1
target: asia
input: china(19)=1.0
child(20)=1.0
policy(21)=1.0
resulted(22)=1.0
surplus(23)=1.0
boys(24)=1.0

name: array:2
target: asia
input: tigers(25)=1.0
live(26)=1.0
jungle(27)=1.0

name: array:0
target: australia
input: home(28)=1.0
kangaroos(29)=1.0

name: array:1
target: australia
input: autralian(30)=1.0
beer(31)=1.0
foster(32)=1.0

name: array:2
target: australia
input: steve(33)=1.0
irvin(34)=1.0
herpetologist(35)=1.0
```

## Print-Input-And-Target ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/PrintInputAndTarget.html)

If you passed true in createPipe as last argument then constructor of PrintInputAndTarget is called.

### Implementations ###

  * 1) public PrintInputAndTarget (String prefix): If user want to add some prefix before the actual output then should cal this constructor.

  * 2) PrintInputAndTarget(): This is the constructor which simply creates the object of PrintInputAndTarget without any prefix.



```
Sample Code  
_________________________________________________________________________________________

mc.CreatePipe (  new Input2CharSequence("UTF-8"),
                 new CharSequenceReplace(tokenPattern, "Mallet"),
                 new CharSequenceRemoveHTML(),
                 new CharSequence2TokenSequence(),
                 new TokenSequenceRemoveStopwords(true, true),
                 new MakeAmpersandXMLFriendly(),
                 true,
                 new TokenSequence2FeatureSequence(), 
                 new Target2Label(),
                 new FeatureSequence2AugmentableFeatureVector(false),
                 new AugmentableFeatureVectorLogScale(), 
                 true
               )
This function will create the Pipelist by adding the instances of all the pipes.

_________________________________________________________________________________________
```
The following pipes are used by the application. We will look into the each of the pipe
one by one and explains what each one do.

The purpose of the each pipe is explained below.
  * 1)Input-2-Char-Sequence: Convert Input data to character sequence(Already Explained)
  * 2)Char-Sequence-Replace: Replaces the String from the character sequence.
  * 3)Char-Sequence-Remove-HTML: Removes the HTML content from the character sequence
  * 4)Char-Sequence-2-Token-Sequence: Convert char sequence to token sequence(Already Explained)
  * 5)Token-Sequence-Remove-Stopwords: Remove stop words (Already Explained)
  * 6)Make-Ampersand-XML-Friendly: convert & to &amp in tokens of a token sequence
  * 7)Token-Sequence-2-Feature-Sequence: converts the Token Sequence to FeatureSequence(Already Explained)
  * 8)Target-2-Label: Convert object in the target field into label in the target field. Already Explained)
  * 9)Feature-Seqence-2-Augmentable-Feature-Vector: Convert Feature sequence into Augmentable Feature Vector.
  * 10)Augmentable-Feature-Vector-Log-Scale: Given an AugmentableFeatureVector, set those values greater than or equal to 1 to log(value)+1.

Some of the other pipes which are used in Mallet and also explained here.

  * 11) Charsequence2charngrams
  * 12) Char-Sequence-Lowercase
  * 13) Char-Subsequence
  * 14) Line-Group-String-2-Token-Sequence

Now we will look into each pipe

## Char-Sequence-Replace ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/CharSequenceReplace)


It is used to find the words which satisfy the given regex expression and then replace
it with the given String.

### Implementation ###
  * 1)CharSequenceReplace(java.util.regex.Pattern regex, java.lang.String replacement):-
    * regex : Provides the regex expression for the words user want to replace.
    * replacement: Provide the string with which user wants to replace the words in the training data set.
```
For eg. Suppose training Data set contains 
           on the plains of AFRICA the lions roar

    //Pattern is created to filter all the words which contain capital letters.
    
        Pattern tokenPattern=Pattern.compile(“[\\p{Lu}_]+”);
        CharSequenceReplace(tokenPattern,”Pallet”);

      The data is converted to on the plains of Pallet the lions roar

```
## Char-Sequence-Remove-HTML ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/CharSequenceRemoveHTML.html)

This pipe is used to remove the HTML contents from the training data set.

### Implementation ###

  * 1) CharSequenceRemoveHTML()
```
     For eg. Suppose training Data set contains
             on the plains of <HTML> the lions roar
                   CharSequenceReplace(); //Pipe constructor is called.
             The data is converted to
             on the plains of the lions roar
```

## Make-Ampersand-XML-Friendly ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/MakeAmpersandXMLFriendly.html)

This pipe converts & to &amp in tokens of a token sequence.

### Implementation ###
  1. MakeAmpersandXMLFriendly(): It will create the object of the MakeAmpersandXMLFriendly which will convert the & to &amp in tokens of a token Sequence


## Feature-Sequence-2-Augmentable-Feature-Vector ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/FeatureSequence2AugmentableFeatureVector.html)


It converts the data field from the feature vector to Augmentable Feature Vector.

Note: FeatureSequence2FeatureVector is a different pipe from FeatureSequence2Augmentable
FeatureVector.

FeatureSequence2FeatureVector: It converts the feature sequence to feature vector and not
into augmentable feature vector. There are certain important pipes( explained below)
which can only be applied to augmentable feature vector.

### Implementation ###
  * 1) FeatureSequence2AugmentableFeatureVector() : It will convert the FeatureSequence 2AugumentableFeatureVector which in almost every aspect is same a FeatureSequence2 FeatureVector. But, there are some pipes which can only be applied to Augmentable FeatureVector( AugmentableFeatureVectorLogScale can only be applied to Augmentable Feature Vector)

  * 2) FeatureSequence2AugmentableFeatureVector(Boolean binary):


## Augmentable-Feature-Vector-Log-Scale ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/AugmentableFeatureVectorLogScale.html)

The tokens which are occurring once or more than once ,set their value to 1+log(value).
For eg if token Pallet occurs two times than its value of occurrence is changed to 1+log(2). This thing is important if multiple counts should not be treated as independent evidence.

### Implementation ###
  * 1) AugumentableFeatureVectorLogScale():There is only one constructor in the class and which must be invoked without parameter.
```
For eg AugumentableFeature vector is
name: array:3
target: africa
input: saraha(11)=2 (sahara is occurring two times in a training set)
dessert(12)=1.0
expanding(13)=1.0

AugumentableFeatureVectorLogScale will convert the input into following form

name: array:3
target: africa
input: saraha(11)=1.6931471805599454(ln2 +1)
dessert(12)=1.0
expanding(13)=1.0

Note: The value 1.69 is certainly less than 2 .We are decreasing its value so that 
classifier will not be affected much by the multiple occurrence of the token.
```

## Char-Sequence-2-char-n-grams ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/CharSequence2CharNGrams.html)

This pipe is used to convert the character into a token sequence of N gram. It means , if
N is three than the token which is created by this pipe has length 3.

### Implementation ###
  * 1)CharSequence2CharNGrams(int n,Boolean distinguishBorders):
    * A)n: Gives the size of the tokens.
    * B)distinguishBorder: If it is true add “>” at the beginning of each line,basically used to distinguish the border
```
For eg ,suppose we have training data which looks like this
     "on the plains of AFRICA the lions roar",
         "in swahili ngoma means to dance",

CharSequence2CharNGrams(3,true) will convert the input into the following form.
abcname: array:0
target: africa
input: >on(0)=1.0 (Start of the border represented by >)
on (1)=1.0
n t(2)=1.0
th(3)=1.6931471805599454 (AugumentableFeatureVectorLogScale pipe is also used)
he (4)=1.6931471805599454
e p(5)=1.0
pl(6)=1.0
pla(7)=1.0
lai(8)=1.0
ain(9)=1.0
ins(10)=1.0
ns (11)=1.6931471805599454
s o(12)=1.0
of(13)=1.0
of (14)=1.0
f a(15)=1.0
af(16)=1.0
afr(17)=1.0
fri(18)=1.0
ric(19)=1.0
ica(20)=1.0
ca (21)=1.0
a t(22)=1.0
e l(23)=1.0
li(24)=1.0
lio(25)=1.0
ion(26)=1.0
ons(27)=1.0
s r(28)=1.0
ro(29)=1.0
roa(30)=1.0
oar(31)=1.0
abcname: array:1
target: africa
input: ns (11)=1.0
>in(32)=1.0 (Beginning of the second line)
in (33)=1.0
n s(34)=1.0
sw(35)=1.0
swa(36)=1.0
wah(37)=1.0
ahi(38)=1.0
hil(39)=1.0
ili(40)=1.0
li (41)=1.0
i n(42)=1.0
ng(43)=1.0
ngo(44)=1.0
gom(45)=1.0
oma(46)=1.0
ma (47)=1.0
a m(48)=1.0
me(49)=1.0
mea(50)=1.0
ean(51)=1.0
ans(52)=1.0
s t(53)=1.0
to(54)=1.0
to (55)=1.0
o d(56)=1.0
da(57)=1.0
dan(58)=1.0
anc(59)=1.0
nce(60)=1.0
CharSequence2CharNGrams(3,false) will produce exactly the same output but will not show 
the starting of the border with (>).
```
## Char-Sequence-Lower-case ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/CharSequenceLowercase.html)


It is simple pipe which will replace the data string (or the character Sequence) with its lowercased version.

### Implementation ###

  * 1)CharSequenceLowercase(): It’s a simple constructor with no arguments which will
> > convert the Character sequence to its lower case.


## Char-Sub-sequence ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/CharSubsequence.html)

Given a string ,it returns only the portion of the string which satisfies the regex parenthesized group.

### Implementation ###
  * 1)CharSubsequence(java.util.regex.Pattern regex): It will return only those parts of the string which satisfies the given regex Pattern. It will call the group function of the Matcher class with argument 1.

  * 2)CharSubsequence(java.util.regex.Pattern regex ,int groupIndex) : It calls the group function of the Matcher class with argument groupIndex.Please look following [site](http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Pattern.html) to know more about Pattern class of java.


## Line-Group-String-2-Token-Sequence ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/LineGroupString2TokenSequence.html)

It is a pipe which takes multi-line String and creates one token for each line.

### Implementation ###

  * 1) LineGroupString2TokenSequence(): The constructor of the class must be called without
any parameter. This will convert the whole line into one token.
```
For eg : Suppose the training data is provided in the following form.
"on the plains of africa the lions roar",
"in swahili ngoma",
"the saraha dessert is expanding"
Africa is the label of the data on the basis of which classification happens.

Call to LineGroupString2TokenSequence() will convert the given input into following form

name: array:0
target: africa
input: on the plains of africa the lions roar
(0)=1.0
name: array:1
target: africa
input: in swahili ngoma
to dance
(1)=1.0
name: array:2
target: africa
input: the saraha dessert is expanding
(2)=1.0


```

## File-name-2-Char-Sequence ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Filename2CharSequence.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/CharSequenceArray2TokenSequence.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Csv2Array.html)


It converts the string of comma separated values(CSV) to an array where each index is a feature name. It will be used prior to Array2FeatureVector.The string must contain only numbers.

### Implementation ###
  * 1)Csv2Array(): This constructor will call the constructor of the charSequenceLexer  with this ([^,]+) parameter. This means process the input string except the “,”(which is used as a separator).
  * 2)Csv2Array(charSequenceLexer l) : If user wants to provide its own set of Pattern then directly pass the reference of charSequenceLexer.
  * 3)Csv2Array(java.lang.String regex) : If user wants to process the string in its own   way than provide the matching pattern in form of String.

  * Pattern : Pattern is the java inbuild class. For further explanation about Pattern
[please\_see](http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Pattern.html)

Note: The sample code and sample output of this pipe is explained after describing Array2FeatureVector.

## Array-2-Feature-Vector ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Array2FeatureVector.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Csv2FeatureVector.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TargetStringToFeatures.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/SimpleTaggerSentence2TokenSequence.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/SimpleTokenizer.html)


This pipe simply converts the string into tokens and take it as its input. It means this pips accept sequence of letters as tokens.

### Implementation ###
  * 1) SimpleTokenizer (java.io.File stopfile) :User provides the list of stopwords here  in the form of file.
  * 2) SimpleTokenizer (java.util.HashSet<java.lang.String> stoplist) :User provides the   list of stopwords here in the form of HashSet.
  * 3) SimpleTokenizer (int languageFlag) : If user wants to use the default Mallet list   than use 1.

  * 1 represent default English stop word list and zero represent empty stop words list.

**stopwords are discussed already.**

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/PipeUtils.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/StringAddNewLineDelimiter.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/CharSequenceRemoveUUEncodedBlocks.html)

This pipe is used to remove the UU Encoded blocks.

### Implementation ###
  * 1)CharSequenceRemoceUUEncodedBlocks(): The pipe is created by calling no argument constructor.

Note: It remove lines from the given input string that begin with M and are 61
characters long. The start of every UUEncoded block is started with M. For further
reading about UUEncodedBlocks [please\_read](http://www.skypoint.com/members/gimonca/uuencode.html).

## String-List-2-Feature-Sequence ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/StringList2FeatureSequence.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/FeatureSequenceConvolution.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/FeatureVectorConjunctions.html)


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


## Token-Sequence-Remove-Non-Alpha ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TokenSequenceRemoveNonAlpha.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TokenSequenceNGrams.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/SGML2TokenSequence.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Target2FeatureSequence.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Target2LabelSequence.html)


It a simple pipe which converts the data in the target field into LabelSequence. LabelSequence is similar to the feature sequence. Its usage is similar to Target2FeatureSequence.It has just one constructor with no arguments. Its output is also similar to the Target2FeatureSequence.

Note: Just replace the Target2FeatureSequence to Target2LabelSequence in the above code.

## Token-Sequence-Parse-Feature-String ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TokenSequenceParseFeatureString.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TokenSequenceMatchDataAndTarget.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TokenSequence2FeatureSequenceWithBigrams.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TargetRememberLastLabel.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/SourceLocation2TokenSequence.html)

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

## Feature-Count-Pipe ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/FeatureCountPipe.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/FeatureValueString2FeatureVector.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TokenSequence2FeatureVectorSequence.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/FeatureVectorSequence2FeatureVectors.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/FilterEmptyFeatureVectors.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/InstanceListTrimFeaturesByCount.html)


This pipe is unimplemented in the Mallet as of now.

## Noop ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Noop.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Pipe.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/PrintInput.html)

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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/PrintTokenSequenceFeatures.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/SaveDataInSource.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/SelectiveSGML2TokenSequence.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/SimpleTaggerSentence2StringTokenization.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Token2FeatureVector.html)
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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/TokenSequence2TokenInstances.html)


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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/AugmentableFeatureVectorAddConjunctions.html)


It is simple pipe which add specified conjunction to the instances.

### Implementation ###

  * 1)AugmentableFeatureVectorAddConjunctions(): It is a simple constructor with no argument which creates the object of AugmentableFeatureVectorAddConjunctions.

### Method ###

  * 1)AugmentableFeatureVectorAddConjunctions addConjunction(java.lang.String name, Alphabet v, int[.md](.md) features, boolean[.md](.md) negations) : This method is used to add the specified conjunction to the instances.

  * 1) Instance 	pipe(Instance carrier) : This method is used to process the data.

## Branching-Pipe ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/BranchingPipe.html)
Thie pipe is deprecated from the Mallet-2.0-RC2.

## Directory-2-File-Iterator ##
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/Directory2FileIterator.html)
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
[Mallet javadoc](http://mallet.cs.umass.edu/api/cc/mallet/pipe/SerialPipes.html)


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
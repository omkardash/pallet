Table of Contents



# Introduction #


## Sample Code ##
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

# Details #

Now we will look into the each pipe.

## Input-2-Char-Sequence ##

It is a Pipe that can read data from various kinds  of sources and convert the given input into character sequence.

### Implementation ###

  * a) public Input2CharSequence (): It is default constructor.If user does not specify any encoding then this constructor will be called.

  * b)public Input2CharSequence( String encoding ) : Specifies the encoding form of the data.

For e.g.: Input2CharSequence("UTF-8"),tell the data to be read is in UTF-8  format.



## Char-Sequence-2-Token-Sequence ##

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

If you passed true in createPipe as last argument then constructor of PrintInputAndTarget is called.

### Implementations ###

  * 1) public PrintInputAndTarget (String prefix): If user want to add some prefix before the actual output then should cal this constructor.

  * 2) PrintInputAndTarget(): This is the constructor which simply creates the object of PrintInputAndTarget without any prefix.
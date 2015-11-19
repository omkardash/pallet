Table of contents
> 

# Introduction #

Mallet uses many pipes in order to process the training data. Tutorial 1 explained some of those pipes and how they can be used. The application which is showing the demonstration of Tutorial 1 is TestMalletClassifier.java.

Tutorial 2 will also explain some of those pipes. The application which is showing the
demonstration of the Tutorial 2 is Data\_Import.java



# Details #

##### Note: Please go through the [Tutorial 1](http://code.google.com/p/pallet/wiki/Tutorial_Data_Import) before starting Tutorial 2. #####
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

Note: The pipes which are already explained in Tutorial 1 are not explained her.
Some of the pipes which are not shown above are also explained here.
_________________________________________________________________________________________
```
The following pipes are used by the application. We will look into the each of the pipe
one by one and explains what each one do.

The purpose of the each pipe is explained below.
  * 1)Input-2-Char-Sequence: Convert Input data to character sequence(Explained in Tutorial 1)
  * 2)Char-Sequence-Replace: Replaces the String from the character sequence.
  * 3)Char-Sequence-Remove-HTML: Removes the HTML content from the character sequence
  * 4)Char-Sequence-2-Token-Sequence: Convert char sequence to token sequence(Explained in Tutorial 1)
  * 5)Token-Sequence-Remove-Stopwords: Remove stop words (Explained in Tutorial 1)
  * 6)Make-Ampersand-XML-Friendly: convert & to &amp in tokens of a token sequence
  * 7)Token-Sequence-2-Feature-Sequence: converts the Token Sequence to FeatureSequence(Exp in Tut1)
  * 8)Target-2-Label: Convert object in the target field into label in the target field. Explained in Tutorial 1)
  * 9)Feature-Seqence-2-Augmentable-Feature-Vector: Convert Feature sequence into Augmentable Feature Vector.
  * 10)Augmentable-Feature-Vector-Log-Scale: Given an AugmentableFeatureVector, set those values greater than or equal to 1 to log(value)+1.

Some of the other pipes which are used in Mallet and also explained here.

  * 11) Charsequence2charngrams
  * 12) Char-Sequence-Lowercase
  * 13) Char-Subsequence
  * 14) Line-Group-String-2-Token-Sequence

Now we will look into each pipe

## Char-Sequence-Replace ##
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

This pipe converts & to &amp in tokens of a token sequence.

### Implementation ###
  1. MakeAmpersandXMLFriendly(): It will create the object of the MakeAmpersandXMLFriendly which will convert the & to &amp in tokens of a token Sequence


## Feature-Sequence-2-Augmentable-Feature-Vector ##
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
It is simple pipe which will replace the data string (or the character Sequence) with its lowercased version.

### Implementation ###

  * 1)CharSequenceLowercase(): It’s a simple constructor with no arguments which will
> > convert the Character sequence to its lower case.


## Char-Sub-sequence ##

Given a string ,it returns only the portion of the string which satisfies the regex parenthesized group.

### Implementation ###
  * 1)CharSubsequence(java.util.regex.Pattern regex): It will return only those parts of the string which satisfies the given regex Pattern. It will call the group function of the Matcher class with argument 1.

  * 2)CharSubsequence(java.util.regex.Pattern regex ,int groupIndex) : It calls the group function of the Matcher class with argument groupIndex.Please look following [site](http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Pattern.html) to know more about Pattern class of java.


## Line-Group-String-2-Token-Sequence ##

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


# Introduction #


We Lance,Pralabh and Sharath has decided number of steps to Import the "Montery2RDF.rdf" into Mallet instances so that incidents can be classified on the basis of there types (Hoax/Prank, threat only, possession only, use of agent).


# Details #

## Steps to Import RDF Data into Mallet Instances ##


There are basically two steps
  * 1)Train the Classifier with training data set
  * 2)Classify the Data with the help of Trained Classifier.

### Train the Classifier ###
  * A) Take the RDF data into Jena Model
  * B) List all Statements for that resource where the object is literal
  * C) As, training Data is considered to be the perfect Data Set, it contains two tags
    * 1)	

<BB:STAT\_EVENT>

: It tells the type of the incident for e.g. (Hoax/Prank, threat only, possession only, use of agent).
    * 2)

&lt;bb:incidentDescription&gt;

: It tells the description of the event which we can associate with the type of the event.
  * D)The Data in RDF format is converted into Mallet instances by the use of pipes.So finally the data which looks like
```
<rdf:Description rdf:about="urn:monterey:incident9">
        <dc:identifier>9</dc:identifier>
        <rdfs:label>incident</rdfs:label>
        <rdf:type rdf:resource = "urn:monterey:incident" />
        <event:startDate>1999-02-05</event:startDate>
        <vCard:ORG rdf:resource="urn:monterey:organization9" />
        <BB:STAT_EVENT>hoax/prank</BB:STAT_EVENT>
<bb:incidentDescription>On 5 February 1999, Main Street School in East Aurora, New York, 
was closed following the discovery of an anthrax threat written on the front door. The 
warning was found by a member of the night staff just before 5 a.m.. East Aurora Police 
Chief William Nye stated that authorities were planning to check the building to be sure 
that the incident was a hoax.[A] The following week, the school board cited the 
disturbance caused by the incident and asked parents to convey to their children the 
seriousness of hoaxes, particularly about subjects like anthrax. Board President Anne 
Wadsworth added that such an action could have serious implications on one's future.[B] A 
17 February 1999 Buffalo news article reported that a seventh-grade East Aurora boy had 
been implicated in the event and charged with falsely reporting an incident and 
aggravated harassment. It also mentioned that his case was petitioned to Family Court.
[C]</bb:incidentDescription>

                 
                 Will get converted into Mallet instances which looks like


                           Name : urn:monterey:incident9
                           Target: hoax/prank
               Data: The important words from the incident description tags for e.g. 
                     Main Street School
                     East Aurora
                     Anthrax
                           Source: Name of the RDF.


```
### Classify the Data ###
    * 1) Take the data which is to be classified in jena Model
    * 2) Passes the data (description of the incident) through the trained Classifier.  Classifier will return the type of the incident (Hoax/Prank, threat only, possession only, use of agent).
    * 3) The Predicate 

<BB:STAT\_EVENT>

 with the type of the incident is added.
    * 4)The whole incident is reified with the predicate 

&lt;dc:creator&gt;

 and would say that the “statement was created by Mallet”
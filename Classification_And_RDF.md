#Classifying RDF Resources.

Table of contents
> 

# Introduction #

Usually classification occurs on un-structured or semi-structured text.  RDF/XML is structured.  However in general we should focus on the RDF, and not as much on the XML.

# RDF Breakdown #
RDF triples are statements in the form:
  * Resource (otherwise known as a Subject)
  * Property (otherwise known as a Predicate)
  * Object

So logically we can say any statement:
incident9 (Resource/Subject)
occured (Property/Predicate)
"1999-02-05" (Object)

incident9
was organized by
organization9

The first statement has a literal object "1999-02-05".  These are the kinds of objects we want to focus on initially.  They are the unstructured text values in RDF.

The second statement has a resource object "organization9" and probably wouldn't be a literal object in RDF.  Instead, organization9 would be another Resource, just as incident9 is a Resource.  Statements like these provide relationship information between resources.  At first we will ignore objects like these as future research tasks will tackle machine learning in RDF.

Using the example RDF that we will be testing against, the RDF for these two would look like the following:

```
<?xml version="1.0"?>
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
    xmlns:dc="http://purl.org/dc/elements/1.1/"
    xmlns:event="http://ebiquity.umbc.edu/ontology/event.owl#"
    xmlns:bb="http://blackbook.com/terms#"
    xmlns:geo="http://www.w3.org/2003/01/geo/wgs84_pos#"
    xmlns:vCard="http://www.w3.org/2001/vcard-rdf/3.0#">

    <rdf:Description rdf:about="urn:monterey:incident9">
        <dc:identifier>9</dc:identifier>
        <rdfs:label>incident</rdfs:label>
        <rdf:type rdf:resource = "urn:monterey:incident" />
        <event:startDate>1999-02-05</event:startDate>
        <vCard:ORG rdf:resource="urn:monterey:organization9" />
        <vCard:ADR rdf:parseType="Resource">
            <vCard:Locality>East Aurora</vCard:Locality>
            <vCard:Region>New York</vCard:Region>
            <vCard:Country>United States</vCard:Country>
            <geo:Point rdf:parseType="Resource">
                <geo:lat>42.767902</geo:lat>
                <geo:long>-78.612604</geo:long>
            </geo:Point>
        </vCard:ADR>
        <dc:source>[A] "East Aurora School Shut After Anthrax Warning," The Buffalo News (5 February 1999).
    [B] T.J. Pignataro, "Success Seen in Meeting New Standards," The Buffalo News (12 February 1999).
    [C] "Boy Pe</dc:source>
        <bb:incidentDescription>On 5 February 1999, Main Street School in East Aurora, New York, was closed following the discovery of an anthrax threat written on the front door. The warning was found by a member of the night staff just before 5 a.m.. East Aurora Police Chief William Nye stated that authorities were planning to check the building to be sure that the incident was a hoax.[A] The following week, the school board cited the disturbance caused by the incident and asked parents to convey to their children the seriousness of hoaxes, particularly about subjects like anthrax. Board President Anne Wadsworth added that such an action could have serious implications on one's future.[B] A 17 February 1999 Buffalo news article reported that a seventh-grade East Aurora boy had been implicated in the event and charged with falsely reporting an incident and aggravated harassment. It also mentioned that his case was petitioned to Family Court.[C]</bb:incidentDescription>
    </rdf:Description>

    <rdf:Description rdf:about="urn:monterey:organization9">
        <rdfs:label>organization</rdfs:label>
        <rdf:type rdf:resource = "urn:monterey:organization" />
        <dc:source>unknown</dc:source>
        <vCard:ORG rdf:parseType="Resource">
            <vCard:Orgunit>Lone actor (s)</vCard:Orgunit>
            <bb:Orgunit_soundsLike>LNKT</bb:Orgunit_soundsLike>
        </vCard:ORG>
    </rdf:Description>

</rdf>
```

# Initial Goal #
The initial goal would be to train the classification system on a data set similar to the above example.  Then when we apply the training model with the classification algorithm, we hope to have the original RDF data as well as additional RDF data stating confidence values for each classification.  So if we are trying to classify all incidents in terms of their type:
  * Hoax/Prank
  * Threat Only
  * Possession Only
  * Use of Agent

Then perhaps we would output the original model with additional statements stating our confidence of the type of incident:

```
<rdf:Description rdf:about="urn:monterey:incident9">
        <dc:identifier>9</dc:identifier>
        <rdfs:label>incident</rdfs:label>
        <rdf:type rdf:resource = "urn:monterey:incident" />
        <event:startDate>1999-02-05</event:startDate>
        <vCard:ORG rdf:resource="urn:monterey:organization9" />
        <BB:STAT_EVENT>hoax/prank</BB:STAT_EVENT>
        <BB:STAT_EVENT>threat only</BB:STAT_EVENT>
        <BB:STAT_EVENT>possession only</BB:STAT_EVENT>
        <BB:STAT_EVENT>use of agent</BB:STAT_EVENT>
        <vCard:ADR rdf:parseType="Resource">
            <vCard:Locality>East Aurora</vCard:Locality>
            <vCard:Region>New York</vCard:Region>
            <vCard:Country>United States</vCard:Country>
            <geo:Point rdf:parseType="Resource">
                <geo:lat>42.767902</geo:lat>
                <geo:long>-78.612604</geo:long>
            </geo:Point>
        </vCard:ADR>
        <dc:source>[A] "East Aurora School Shut After Anthrax Warning," The Buffalo News (5 February 1999).
    [B] T.J. Pignataro, "Success Seen in Meeting New Standards," The Buffalo News (12 February 1999).
    [C] "Boy Pe</dc:source>
        <bb:incidentDescription>On 5 February 1999, Main Street School in East Aurora, New York, was closed following the discovery of an anthrax threat written on the front door. The warning was found by a member of the night staff just before 5 a.m.. East Aurora Police Chief William Nye stated that authorities were planning to check the building to be sure that the incident was a hoax.[A] The following week, the school board cited the disturbance caused by the incident and asked parents to convey to their children the seriousness of hoaxes, particularly about subjects like anthrax. Board President Anne Wadsworth added that such an action could have serious implications on one's future.[B] A 17 February 1999 Buffalo news article reported that a seventh-grade East Aurora boy had been implicated in the event and charged with falsely reporting an incident and aggravated harassment. It also mentioned that his case was petitioned to Family Court.[C]</bb:incidentDescription>
    </rdf:Description>


  <rdf:Statement rdf:about="#reificationTriple1">
     <rdf:subject rdf:resource="urn:monterey:incident9"/>
     <rdf:predicate rdf:resource="BB:STAT_EVENT"/>
     <rdf:object>hoax/prank</rdf:object>

     <mallet:confidence>.90</mallet:confidence>
  </rdf:Statement>

  <rdf:Statement rdf:about="#reificationTriple2">
     <rdf:subject rdf:resource="urn:monterey:incident9"/>
     <rdf:predicate rdf:resource="BB:STAT_EVENT"/>
     <rdf:object>threat only</rdf:object>

     <mallet:confidence>.20</mallet:confidence>
  </rdf:Statement>

  <rdf:Statement rdf:about="#reificationTriple3">
     <rdf:subject rdf:resource="urn:monterey:incident9"/>
     <rdf:predicate rdf:resource="BB:STAT_EVENT"/>
     <rdf:object>possession only</rdf:object>

     <mallet:confidence>.10</mallet:confidence>
  </rdf:Statement>

  <rdf:Statement rdf:about="#reificationTriple4">
     <rdf:subject rdf:resource="urn:monterey:incident9"/>
     <rdf:predicate rdf:resource="BB:STAT_EVENT"/>
     <rdf:object>use of agent</rdf:object>

     <mallet:confidence>.10</mallet:confidence>
  </rdf:Statement>

```

# Reification #
Notice that all four types were listed in the new RDF model.  And then we added statements about those types below.  This is called reification (statements or metadata about statements).  It is very useful in this case since the classifyer doesn't know for sure what the type of incident is (even though it is 90% sure that it is a hoax/prank).  So all four types are included but we associate confidence values for each of those statements.  So the classifier is 90% sure it is a hoax/prank, 20% sure it is a threat only, and 10% sure it is a possession only or use of agent.

If the classifyer had known for sure what type of incident it was, then it would have only added the correct BB:STAT\_EVENT statement and would not need an associated reified statement (although there probably should be reification statement saying that a Mallet classifier added the statement, for provenance purposes).
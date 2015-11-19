#Utilities that MALLET offers.

# Introduction #

Utilities for lexing, logging and Math functions

# contents #


> 
---

> 
---


# Interfaces #

> 
---

> 
---


# Classes #
## Property-List ##
Property Lists are tabular data structure containing zero or more entries, each entry is associated with a key, which is called value or property. Important property of the property list is, any update to it is a destructive(alters the property list rather than making a new one). A list contains an even number of elements that are alternating names (sometimes called indicators or keys) and values (sometimes called properties). When there is more than one name and value pair with the identical name in a property list, the first such pair determines the property.

Mallet Property List works with Object and Numerical (Double) objects.

public class PropertyList
extends java.lang.Object
implements [java.io.Serializable](http://mallet.cs.umass.edu/api/serialized-form.html#cc.mallet.util.PropertyList)

### Nested Classes supported ###
  * PropertyList.Iterator
  * PropertyList.NumericIterator
  * PropertyList.ObjectIterator
> > <To be done>
### Property-List-Constructors ###
  * [protected PropertyList()](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html)

> Does not allow zero parameter constructor, throws [IllegalArgumentException](http://java.sun.com/j2se/1.4.2/docs/api/java/lang/IllegalArgumentException.html)

  * [protected PropertyList(java.lang.String key, PropertyList rest)](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html)
> #### Parameters ####
> key - Key Indicator to be used.
> rest - Reference to PropertyList object.

> Since, both the constructors are protected Property List objects are instantiated using add methods (explained below).

[Contents](MalletUtils#Contents.md)

### Property-List-Methods ###
  * [static PropertyList add(java.lang.String key, double value, PropertyList rest)](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#add(java.lang.String,%20double,%20cc.mallet.util.PropertyList))
> Creates a property list entry and adds its at the front of the property list. this methods creates numerical entry and adds it to the plist.
> #### Parameters ####
  1. key - Indicator of the entry. type - String.
  1. value - Value of the entry. type - double.
  1. rest - Reference of the existing plist to which the entry needs to be added or null if the first entry needs to be created.
> #### Return Value ####
> PropertyList - Updated plist or newly created plist

> Code snippet for instantiating plist
```
PropertyList pl = null;
pl = pl.add ("key", 1.0, pl);
```

  * [static PropertyList add(java.lang.String key, java.lang.Object value, PropertyList rest)](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#add(java.lang.String,%20java.lang.Object,%20cc.mallet.util.PropertyList))
> Creates a property list entry and adds its at the front of the property list. this methods creates numerical entry and adds it to the plist.

> #### Parameters ####
  1. key - Indicator of the entry. type - String.
  1. value - Value of the entry. type - Object.
  1. rest - Reference of the existing plist to which the entry needs to be added or null if the first entry needs to be created.

> #### Return Value ####
  1. PropertyList - Updated plist or newly created plist

> Code snippet for instantiating plist
```
PropertyList pl = null;
String str = "value";
pl = pl.add ("key", (Object)str, pl);
```

  * [static PropertyList add(java.lang.String key, java.lang.String value, PropertyList rest)](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#add(java.lang.String,%20java.lang.String,%20cc.mallet.util.PropertyList))
> Creates a property list entry and adds its at the front of the property list. this methods creates numerical entry and adds it to the plist.

> #### Parameters ####
    1. key - Indicator of the entry. type - String.
    1. value - Value of the entry. type - String.
    1. rest - Reference of the existing plist to which the entry needs to be added or null if the first entry needs to be created.

> #### Return Value ####
    1. PropertyList - Updated plist or newly created plist

> Code snippet for instantiating plist
```
PropertyList pl = null;
pl = pl.add ("key", "value", pl);
```
  * [public static PropertyList remove (String key, PropertyList rest)](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#remove(java.lang.String,%20cc.mallet.util.PropertyList))
> The name remove is really an irony here!!! All it does is create a new entry with with value set to null. Strange isn't it? I was astonished too, but look at the definition of plist it makes sense.
> #### Parameters ####
    1. key - Indicator of the entry that needs to be removed. type - String.
    1. rest - Reference of the plist from which the entry needs to be deleted.

> #### Return Value ####
    1. PropertyList - Updated plist or newly created plist.
> Code snippet
```
PropertyList pl = null;
pl = pl.add ("key", "value", pl);
pl = pl.remove("key",pl);
```

  * [Object lookupObject (String key)](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#lookupObject(java.lang.String))
> Searches for the property entry's value having the specified key.
> #### Parameters ####
  1. key - key of the entry to be searched, type - String
> #### Return Value ####
  1. Entry's value if found else null

  * [double lookupNumber (String key)](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#lookupNumber(java.lang.String))
> Searches for the property entry's value having the specified key, works on numerical plist.
> #### Parameters ####
  1. key - key of the entry to be searched, type - String
> #### Return Value ####
  1. Entry's value if found else null

  * [boolean hasProperty (String key)](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#hasProperty(java.lang.String))
> #### Parameters ####
> #### Return Value ####
  * [Iterator iterator ()](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#iterator())
> Returns iterator to plist.
> #### Return Value ####
> Iterator to plist

  * [static PropertyList sumDuplicateKeyValues (PropertyList pl)](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#sumDuplicateKeyValues(cc.mallet.util.PropertyList))
> If the plist is of numerical type then returns the updated plist with summing up the duplicate values of a key.
> #### Parameters ####
  1. plist for which the duplicates values need to summed up.
> #### Return Value ####
  1. Updated plist.

  * [static PropertyList sumDuplicateKeyValues (PropertyList pl, boolean ignoreZeros)](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#sumDuplicateKeyValues(cc.mallet.util.PropertyList,%20boolean))
> #### Parameters ####
> Same as the previous one but does not consider entries having zero values.

  * [Iterator numericIterator ()](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#numericIterator())
> Same as previous Iterator method but works for Numerical plist.

  * [Iterator objectIterator ()](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#objectIterator())
> #### Parameters ####
> #### Return Value ####
  * [void print ()](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#print())
> Prints all the entries in plist to the standard output
  * [int size ()](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#size())
> #### Return Value ####
> Number of entries in the plist.

  * [PropertyList last()](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#last())
> #### Parameters ####

> #### Return Value ####
  * [PropertyList append(PropertyList nextPl) throws UnsupportedOperationException](http://mallet.cs.umass.edu/api/cc/mallet/util/PropertyList.html#append(cc.mallet.util.PropertyList))
> #### Parameters ####
> #### Return Value ####

[Contents](MalletUtils#Contents.md)

> 
---

> 
---

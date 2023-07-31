class PlaceHold {
  public String addAttributeAndCheck(Attribute attribute) throws ManifestException {
    if ((attribute.getName() == null) || (attribute.getValue() == null)) {
      throw new BuildException("Attributes must have name and value");
    }
    if (attribute.getKey().equalsIgnoreCase(ATTRIBUTE_NAME)) {
      warnings.addElement(
          ((((((("\"" + ATTRIBUTE_NAME) + "\" attributes ")
                                  + "should not occur in the main section and must be the ")
                              + "first element in all other sections: \"")
                          + attribute.getName())
                      + ": ")
                  + attribute.getValue())
              + "\"");
      return attribute.getValue();
    }
    if (attribute.getKey().startsWith(ATTRIBUTE_FROM.toLowerCase())) {
      warnings.addElement(
          (((((("Manifest attributes should not start " + "with \"") + ATTRIBUTE_FROM) + "\" in \"")
                          + attribute.getName())
                      + ": ")
                  + attribute.getValue())
              + "\"");
    } else {
      String attributeKey = attribute.getKey();
      if (attributeKey.equals(ATTRIBUTE_CLASSPATH)) {
        Attribute classpathAttribute = ((Attribute) (attributes.get(attributeKey)));
        if (classpathAttribute == null) {
          storeAttribute(attribute);
        } else {
          Enumeration e = attribute.getValues();
          while (e.hasMoreElements()) {
            String value = ((String) (e.nextElement()));
            classpathAttribute.addValue(value);
          }
        }
      } else if (attributes.containsKey(attributeKey)) {
        throw new ManifestException(
            (("The attribute \"" + attribute.getName()) + "\" may not occur more ")
                + "than once in the same section");
      } else {
        storeAttribute(attribute);
      }
    }
    return null;
  }
}

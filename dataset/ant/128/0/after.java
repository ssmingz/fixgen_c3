class PlaceHold {
  public void validate() throws BuildException {
    if (token == null) {
      String message = "token is a mandatory attribute " + "of replacefilter.";
      throw new BuildException(message);
    }
    if ("".equals(token)) {
      String message = "The token attribute must not be an empty string.";
      throw new BuildException(message);
    }
    if ((value != null) && (property != null)) {
      String message =
          ("Either value or property " + "can be specified, but a replacefilter ")
              + "element cannot have both.";
      throw new BuildException(message);
    }
    if (property != null) {
      if (propertyFile == null) {
        String message =
            ("The replacefilter's property attribute " + "can only be used with the replacetask's ")
                + "propertyFile attribute.";
        throw new BuildException(message);
      }
      if ((properties == null) || (properties.getProperty(property) == null)) {
        String message =
            (("property \"" + property) + "\" was not found in ") + propertyFile.getPath();
        throw new BuildException(message);
      }
    }
  }
}

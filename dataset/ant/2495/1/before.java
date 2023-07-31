class PlaceHold {
  public static Parser getParser() throws BuildException {
    try {
      return newSAXParser().getParser();
    } catch (SAXException e) {
      throw convertToBuildException(e);
    }
  }
}

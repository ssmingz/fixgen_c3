class PlaceHold {
  public static Parser getParser() throws BuildException {
    try {
      return newSAXParser(getParserFactory()).getParser();
    } catch (SAXException e) {
      throw convertToBuildException(e);
    }
  }
}

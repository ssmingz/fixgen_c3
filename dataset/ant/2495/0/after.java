class PlaceHold {
  public static XMLReader getXMLReader() throws BuildException {
    try {
      return newSAXParser(getParserFactory()).getXMLReader();
    } catch (SAXException e) {
      throw convertToBuildException(e);
    }
  }
}

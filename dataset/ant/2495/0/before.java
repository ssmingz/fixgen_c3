class PlaceHold {
  public static XMLReader getXMLReader() throws BuildException {
    try {
      return newSAXParser().getXMLReader();
    } catch (SAXException e) {
      throw convertToBuildException(e);
    }
  }
}

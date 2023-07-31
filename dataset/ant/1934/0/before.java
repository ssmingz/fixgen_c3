class PlaceHold {
  private static String getXMLParserLocation() {
    SAXParser saxParser = getSAXParser();
    if (saxParser == null) {
      return null;
    }
    String location = getClassLocation(saxParser.getClass());
    return location;
  }
}

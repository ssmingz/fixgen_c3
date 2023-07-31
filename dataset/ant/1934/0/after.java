class PlaceHold {
  private static String getXMLParserLocation() {
    SAXParser saxParser = getSAXParser();
    if (saxParser == null) {
      return null;
    }
    URL location = getClassLocation(saxParser.getClass());
    return location != null ? location.toString() : null;
  }
}

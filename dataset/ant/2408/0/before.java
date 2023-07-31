class PlaceHold {
  public void characters(char[] buf, int start, int count) throws SAXParseException {
    String s = new String(buf, start, count).trim();
    if (s.length() > 0) {
      throw new SAXParseException(("Unexpected text \"" + s) + "\"", locator);
    }
  }
}

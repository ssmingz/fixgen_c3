class PlaceHold {
  public void startElement(String tag, AttributeList attrs) throws SAXParseException {
    throw new SAXParseException(("Unexpected element \"" + tag) + "\"", helperImpl.locator);
  }
}

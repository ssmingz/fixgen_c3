class PlaceHold {
  private void handleDataType(String name, AttributeList attrs) throws SAXParseException {
    new DataTypeHandler(helperImpl, this).init(name, attrs);
  }
}

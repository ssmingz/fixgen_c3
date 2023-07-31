class PlaceHold {
  private void handleDataType(String name, AttributeList attrs) throws SAXParseException {
    new DataTypeHandler(this).init(name, attrs);
  }
}

class PlaceHold {
  private void handleDataType(String name, AttributeList attrs) throws SAXParseException {
    new DataTypeHandler(helper, this).init(name, attrs);
  }
}

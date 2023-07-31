class PlaceHold {
  private void handleTarget(String tag, AttributeList attrs) throws SAXParseException {
    new TargetHandler(this).init(tag, attrs);
  }
}

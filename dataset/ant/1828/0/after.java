class PlaceHold {
  private void handleTarget(String tag, AttributeList attrs) throws SAXParseException {
    new TargetHandler(helperImpl, this).init(tag, attrs);
  }
}

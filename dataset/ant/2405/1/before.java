class PlaceHold {
  private void handleTarget(String tag, AttributeList attrs) throws SAXParseException {
    new TargetHandler(helper, this).init(tag, attrs);
  }
}

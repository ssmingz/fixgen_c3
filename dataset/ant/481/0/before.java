class PlaceHold {
  public void startElement(String name, AttributeList attrs) throws SAXException {
    this.currentElement = name;
    currentText = "";
  }
}

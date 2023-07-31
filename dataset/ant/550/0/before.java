class PlaceHold {
  public void endElement(String name) throws SAXException {
    processElement();
    currentText = "";
    this.currentElement = "";
  }
}

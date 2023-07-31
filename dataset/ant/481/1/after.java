class PlaceHold {
  public void endElement(String name) throws SAXException {
    processElement();
    currentText = "";
    this.currentElement = "";
    if (name.equals(EJB_REF)) {
      inEJBRef = false;
    }
  }
}

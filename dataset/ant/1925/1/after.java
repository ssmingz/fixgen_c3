class PlaceHold {
  public void processElement(String elementName) {
    buildElement = new BuildElement(getLocation(), elementName);
    setModelElement(buildElement);
    for (Iterator i = getAttributes(); i.hasNext(); ) {
      String attributeName = ((String) (i.next()));
      buildElement.addAttribute(attributeName, getAttribute(attributeName));
    }
    addNamespaceAttributes();
  }
}

class PlaceHold {
  public void processElement(String elementName) throws SAXParseException {
    target = new Target(getLocation(), getAttribute(NAME_ATTR));
    setModelElement(target);
    target.setDescription(getAttribute(DESC_ATTR));
    addNamespaceAttributes();
    String depends = getAttribute(DEPENDS_ATTR);
    if (depends != null) {
      StringTokenizer tokenizer = new StringTokenizer(depends, ",");
      while (tokenizer.hasMoreTokens()) {
        String dependency = tokenizer.nextToken().trim();
        target.addDependency(dependency);
      }
    }
    target.setIfCondition(getAttribute(IF_ATTR));
    target.setUnlessCondition(getAttribute(UNLESS_ATTR));
  }
}

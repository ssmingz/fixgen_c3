class PlaceHold {
  public void processElement(String elementName) throws SAXParseException {
    if (project == null) {
      project = new Project(getElementSource(), getLocation());
      setModelElement(project);
      project.setDefaultTarget(getAttribute(DEFAULT_ATTR));
      project.setBase(getAttribute(BASEDIR_ATTR));
      project.setName(getAttribute(NAME_ATTR));
      project.addAspectAttributes(getAspectAttributes());
    }
  }
}

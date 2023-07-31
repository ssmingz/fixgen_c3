class PlaceHold {
  public boolean eventPosted(EventObject event) {
    ProjectProxy project = getAppContext().getProject();
    _text.setDocument(project == null ? new PlainDocument() : project.getDocument());
    return true;
  }
}

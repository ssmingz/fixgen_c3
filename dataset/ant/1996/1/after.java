class PlaceHold {
  public boolean eventPosted(EventObject event) {
    ProjectProxy project = getContext().getProject();
    _text.setDocument(project == null ? new PlainDocument() : project.getDocument());
    return true;
  }
}

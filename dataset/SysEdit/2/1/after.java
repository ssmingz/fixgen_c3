class PlaceHold {
  public void elementDeleted(Object element) {
    IEditorInput input = getDocumentKey();
    if ((input != null) && input.equals(element)) {
      resetDocument();
    }
  }
}

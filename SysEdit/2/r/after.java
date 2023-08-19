class PlaceHold {
  public void elementDeleted(Object element) {
    IEditorInput input = getDocumentKey();
    if ((input != null) && input.equals(element)) {
      // This method will only get called if the buffer is not dirty
      resetDocument();
    }
  }
}

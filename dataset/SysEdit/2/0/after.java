class PlaceHold {
  public void elementMoved(Object originalElement, Object movedElement) {
    IEditorInput input = getDocumentKey();
    if ((input != null) && input.equals(originalElement)) {
      resetDocument();
    }
  }
}

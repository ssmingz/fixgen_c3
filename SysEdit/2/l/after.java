class PlaceHold {
  public void elementMoved(Object originalElement, Object movedElement) {
    IEditorInput input = getDocumentKey();
    if ((input != null) && input.equals(originalElement)) {
      // This method will only get called if the buffer is not dirty
      resetDocument();
    }
  }
}

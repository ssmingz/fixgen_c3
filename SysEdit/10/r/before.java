class PlaceHold {
  public void elementContentReplaced(Object element) {
    IEditorInput input = getDocumentKey();
    if (input != null && input.equals(element)) {
      this.fViewer.updateDirtyState(input, getDocumentProvider(), fLeg);
    }
  }
}

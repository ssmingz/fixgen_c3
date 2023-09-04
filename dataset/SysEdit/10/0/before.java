class PlaceHold {
  public void elementDirtyStateChanged(Object element, boolean isDirty) {
    IEditorInput input = getDocumentKey();
    if (input != null && input.equals(element)) {
      this.fViewer.updateDirtyState(input, getDocumentProvider(), fLeg);
    }
  }
}

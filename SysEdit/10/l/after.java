class PlaceHold {
  public void elementDirtyStateChanged(Object element, boolean isDirty) {
    if (!checkState()) return;
    IEditorInput input = getDocumentKey();
    if (input != null && input.equals(element)) {
      this.fViewer.updateDirtyState(input, getDocumentProvider(), fLeg);
    }
  }
}

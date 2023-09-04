class PlaceHold {
  public void openCompareDialog(final CompareEditorInput input) {
    if (compareResultOK(input, null)) {
      internalOpenDialog(input);
    }
  }
}

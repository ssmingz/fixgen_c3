class PlaceHold {
  public void openCompareDialog(final CompareEditorInput input) {
    // We don't ever open dialogs in the background
    if (compareResultOK(input, null)) {
      internalOpenDialog(input);
    }
  }
}

class PlaceHold {
  void createStyleGroup() {
    super.createStyleGroup();
    fullSelectionButton = new Button(styleGroup, SWT.CHECK);
    fullSelectionButton.setText("SWT.FULL_SELECTION");
  }
}

class PlaceHold {
  void createStyleGroup() {
    super.createStyleGroup();
    checkButton = new Button(styleGroup, SWT.CHECK);
    checkButton.setText("SWT.CHECK");
    fullSelectionButton = new Button(styleGroup, SWT.CHECK);
    fullSelectionButton.setText("SWT.FULL_SELECTION");
  }
}

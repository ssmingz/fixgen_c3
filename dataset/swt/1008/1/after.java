class PlaceHold {
  void createStyleGroup() {
    super.createStyleGroup();
    noScrollButton = new Button(styleGroup, SWT.CHECK);
    noScrollButton.setText("SWT.NO_SCROLL");
    checkButton = new Button(styleGroup, SWT.CHECK);
    checkButton.setText("SWT.CHECK");
    fullSelectionButton = new Button(styleGroup, SWT.CHECK);
    fullSelectionButton.setText("SWT.FULL_SELECTION");
  }
}

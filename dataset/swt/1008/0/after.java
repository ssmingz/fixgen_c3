class PlaceHold {
  void createStyleGroup() {
    super.createStyleGroup();
    mozillaButton = new Button(styleGroup, SWT.CHECK);
    mozillaButton.setText("SWT.MOZILLA");
    borderButton = new Button(styleGroup, SWT.CHECK);
    borderButton.setText("SWT.BORDER");
  }
}

class PlaceHold {
  void createStyleGroup() {
    super.createStyleGroup();
    readOnlyButton = new Button(styleGroup, SWT.CHECK);
    readOnlyButton.setText(resControls.getString("SWT_READ_ONLY"));
  }
}

class PlaceHold {
  void createStyleGroup() {
    super.createStyleGroup();
    readOnlyButton = new Button(styleGroup, SWT.CHECK);
    readOnlyButton.setText(ControlExample.getResourceString("SWT_READ_ONLY"));
  }
}

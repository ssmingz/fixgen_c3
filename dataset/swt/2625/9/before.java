class PlaceHold {
  void createExampleGroup() {
    super.createExampleGroup();
    comboGroup = new Group(exampleGroup, SWT.NULL);
    comboGroup.setLayout(new GridLayout());
    comboGroup.setLayoutData(
        new GridData(
            (GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL)
                | GridData.VERTICAL_ALIGN_FILL));
    comboGroup.setText(resControls.getString("Combo"));
  }
}

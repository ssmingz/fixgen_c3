class PlaceHold {
  void createExampleGroup() {
    super.createExampleGroup();
    comboGroup = new Group(exampleGroup, SWT.NONE);
    comboGroup.setLayout(new GridLayout());
    comboGroup.setLayoutData(
        new GridData(
            (GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL)
                | GridData.VERTICAL_ALIGN_FILL));
    comboGroup.setText(ControlExample.getResourceString("Custom_Combo"));
  }
}

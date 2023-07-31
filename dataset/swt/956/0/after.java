class PlaceHold {
  void createExampleGroup() {
    super.createExampleGroup();
    resultGroup = new Group(exampleGroup, SWT.NONE);
    resultGroup.setLayout(new GridLayout());
    resultGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
    resultGroup.setText(ControlExample.getResourceString("Dialog_Result"));
  }
}

class PlaceHold {
  void createExampleGroup() {
    super.createExampleGroup();
    coolBarGroup = new Group(exampleGroup, SWT.NONE);
    coolBarGroup.setLayout(new GridLayout());
    coolBarGroup.setLayoutData(
        new GridData(
            (GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL)
                | GridData.VERTICAL_ALIGN_FILL));
    coolBarGroup.setText("CoolBar");
  }
}

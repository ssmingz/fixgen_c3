class PlaceHold {
  void createExampleGroup() {
    super.createExampleGroup();
    tableGroup = new Group(exampleGroup, SWT.NONE);
    tableGroup.setLayout(new GridLayout());
    tableGroup.setLayoutData(
        new GridData(
            (GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL)
                | GridData.VERTICAL_ALIGN_FILL));
    tableGroup.setText("Table");
  }
}

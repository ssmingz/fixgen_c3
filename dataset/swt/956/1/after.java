class PlaceHold {
  void createExampleGroup() {
    super.createExampleGroup();
    listGroup = new Group(exampleGroup, SWT.NONE);
    listGroup.setLayout(new GridLayout());
    listGroup.setLayoutData(
        new GridData(
            (GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL)
                | GridData.VERTICAL_ALIGN_FILL));
    listGroup.setText("List");
  }
}

class PlaceHold {
  void createExampleGroup() {
    super.createExampleGroup();
    textLabelGroup = new Group(exampleGroup, SWT.NONE);
    GridLayout gridLayout = new GridLayout();
    textLabelGroup.setLayout(gridLayout);
    gridLayout.numColumns = 3;
    textLabelGroup.setLayoutData(
        new GridData(
            (GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL)
                | GridData.VERTICAL_ALIGN_FILL));
    textLabelGroup.setText(ControlExample.getResourceString("Custom_Labels"));
  }
}

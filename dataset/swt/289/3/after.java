class PlaceHold {
  void createExampleGroup() {
    super.createExampleGroup();
    progressBarGroup = new Group(exampleGroup, SWT.NONE);
    progressBarGroup.setLayout(new GridLayout());
    progressBarGroup.setLayoutData(
        new GridData(
            (GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL)
                | GridData.VERTICAL_ALIGN_FILL));
    progressBarGroup.setText("ProgressBar");
  }
}

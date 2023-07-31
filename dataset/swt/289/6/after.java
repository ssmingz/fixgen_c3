class PlaceHold {
  void createControlGroup() {
    super.createControlGroup();
    allignmentGroup = new Group(controlGroup, SWT.NONE);
    allignmentGroup.setLayout(new GridLayout());
    allignmentGroup.setLayoutData(
        new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
    allignmentGroup.setText(ControlExample.getResourceString("Alignment"));
    leftButton = new Button(allignmentGroup, SWT.RADIO);
    leftButton.setText(ControlExample.getResourceString("Left"));
    centerButton = new Button(allignmentGroup, SWT.RADIO);
    centerButton.setText(ControlExample.getResourceString("Center"));
    rightButton = new Button(allignmentGroup, SWT.RADIO);
    rightButton.setText(ControlExample.getResourceString("Right"));
    SelectionListener selectionListener =
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            if (!((Button) (event.widget)).getSelection()) {
              return;
            }
            setExampleWidgetAlignment();
          }
        };
    leftButton.addSelectionListener(selectionListener);
    centerButton.addSelectionListener(selectionListener);
    rightButton.addSelectionListener(selectionListener);
  }
}

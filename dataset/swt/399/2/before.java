class PlaceHold {
  void createBackgroundModeGroup() {
    if (getExampleWidgets().length == 0) {
      return;
    }
    backgroundModeGroup = new Group(controlGroup, SWT.NONE);
    backgroundModeGroup.setLayout(new GridLayout());
    backgroundModeGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
    backgroundModeGroup.setText(ControlExample.getResourceString("Background_Mode"));
    backgroundModeCombo = new Combo(backgroundModeGroup, SWT.READ_ONLY);
    backgroundModeCombo.setItems(
        new String[] {"SWT.INHERIT_NONE", "SWT.INHERIT_DEFAULT", "SWT.INHERIT_FORCE"});
    backgroundModeImageButton = new Button(backgroundModeGroup, SWT.CHECK);
    backgroundModeImageButton.setText(ControlExample.getResourceString("BackgroundImage"));
    backgroundModeColorButton = new Button(backgroundModeGroup, SWT.CHECK);
    backgroundModeColorButton.setText(ControlExample.getResourceString("BackgroundColor"));
    backgroundModeCombo.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setExampleGroupBackgroundMode();
          }
        });
    backgroundModeImageButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setExampleGroupBackgroundImage();
          }
        });
    backgroundModeColorButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setExampleGroupBackgroundColor();
          }
        });
    backgroundModeCombo.setText(backgroundModeCombo.getItem(0));
    backgroundModeImageButton.setSelection(false);
    backgroundModeColorButton.setSelection(false);
  }
}

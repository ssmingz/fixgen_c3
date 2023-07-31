class PlaceHold {
  void createDisplayGroup() {
    super.createDisplayGroup();
    lockedButton = new Button(displayGroup, SWT.CHECK);
    lockedButton.setText(ControlExample.getResourceString("Locked"));
    lockedButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setWidgetLocked();
          }
        });
  }
}

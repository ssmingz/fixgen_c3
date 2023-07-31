class PlaceHold {
  void createOtherGroup() {
    super.createOtherGroup();
    lockedButton = new Button(otherGroup, SWT.CHECK);
    lockedButton.setText(ControlExample.getResourceString("Locked"));
    lockedButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setWidgetLocked();
          }
        });
  }
}

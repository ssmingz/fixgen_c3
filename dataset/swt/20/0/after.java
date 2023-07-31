class PlaceHold {
  void createOtherGroup() {
    super.createOtherGroup();
    titleButton = new Button(otherGroup, SWT.CHECK);
    titleButton.setText(ControlExample.getResourceString("Title_Text"));
    titleButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setTitleText();
          }
        });
  }
}

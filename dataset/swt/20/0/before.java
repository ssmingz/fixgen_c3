class PlaceHold {
  void createDisplayGroup() {
    super.createDisplayGroup();
    titleButton = new Button(displayGroup, SWT.CHECK);
    titleButton.setText(ControlExample.getResourceString("Title_Text"));
    titleButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setTitleText();
          }
        });
  }
}

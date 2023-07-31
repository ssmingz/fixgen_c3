class PlaceHold {
  void createPageIncrementGroup() {
    Group pageIncrementGroup = new Group(controlGroup, SWT.NULL);
    pageIncrementGroup.setLayout(new GridLayout());
    pageIncrementGroup.setText(ControlExample.getResourceString("Page_Increment"));
    pageIncrementScale = new Scale(pageIncrementGroup, SWT.NULL);
    pageIncrementScale.setMaximum(100);
    pageIncrementScale.setSelection(10);
    pageIncrementScale.setPageIncrement(10);
    pageIncrementScale.setIncrement(5);
    pageIncrementScale.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setWidgetIncrement();
          }
        });
  }
}

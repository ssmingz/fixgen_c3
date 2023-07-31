class PlaceHold {
  void createPageIncrementGroup() {
    Group pageIncrementGroup = new Group(controlGroup, SWT.NONE);
    pageIncrementGroup.setLayout(new GridLayout());
    pageIncrementGroup.setText(ControlExample.getResourceString("Page_Increment"));
    pageIncrementGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    pageIncrementSpinner = new Spinner(pageIncrementGroup, SWT.BORDER);
    pageIncrementSpinner.setMaximum(100);
    pageIncrementSpinner.setSelection(10);
    pageIncrementSpinner.setPageIncrement(10);
    pageIncrementSpinner.setIncrement(5);
    pageIncrementSpinner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    pageIncrementSpinner.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setWidgetPageIncrement();
          }
        });
  }
}

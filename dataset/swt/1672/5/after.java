class PlaceHold {
  void createMinimumGroup() {
    Group minimumGroup = new Group(controlGroup, SWT.NONE);
    minimumGroup.setLayout(new GridLayout());
    minimumGroup.setText(ControlExample.getResourceString("Minimum"));
    minimumGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    minimumSpinner = new Spinner(minimumGroup, SWT.BORDER);
    minimumSpinner.setMaximum(100);
    minimumSpinner.setSelection(0);
    minimumSpinner.setPageIncrement(10);
    minimumSpinner.setIncrement(5);
    minimumSpinner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    minimumSpinner.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setWidgetMinimum();
          }
        });
  }
}

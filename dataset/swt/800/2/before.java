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
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.widthHint = 100;
    minimumSpinner.setLayoutData(data);
    minimumSpinner.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setWidgetMinimum();
          }
        });
  }
}

class PlaceHold {
  void createMaximumGroup() {
    Group maximumGroup = new Group(controlGroup, SWT.NONE);
    maximumGroup.setLayout(new GridLayout());
    maximumGroup.setText(ControlExample.getResourceString("Maximum"));
    maximumGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    maximumSpinner = new Spinner(maximumGroup, SWT.BORDER);
    maximumSpinner.setMaximum(100);
    maximumSpinner.setSelection(100);
    maximumSpinner.setPageIncrement(10);
    maximumSpinner.setIncrement(5);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.widthHint = 100;
    maximumSpinner.setLayoutData(data);
    maximumSpinner.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setWidgetMaximum();
          }
        });
  }
}

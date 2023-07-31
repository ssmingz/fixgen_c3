class PlaceHold {
  void createIncrementGroup() {
    Group incrementGroup = new Group(controlGroup, SWT.NONE);
    incrementGroup.setLayout(new GridLayout());
    incrementGroup.setText(ControlExample.getResourceString("Increment"));
    incrementGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    incrementSpinner = new Spinner(incrementGroup, SWT.BORDER);
    incrementSpinner.setMaximum(100);
    incrementSpinner.setSelection(1);
    incrementSpinner.setPageIncrement(10);
    incrementSpinner.setIncrement(5);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.widthHint = 100;
    incrementSpinner.setLayoutData(data);
    incrementSpinner.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            setWidgetIncrement();
          }
        });
  }
}

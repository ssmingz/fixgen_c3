class PlaceHold {
  void createIncrementGroup() {
    Group incrementGroup = new Group(controlGroup, SWT.NONE);
    incrementGroup.setLayout(new GridLayout());
    incrementGroup.setText(ControlExample.getResourceString("Increment"));
    incrementGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    incrementSpinner = new Spinner(incrementGroup, SWT.BORDER);
    incrementSpinner.setMaximum(100);
    incrementSpinner.setSelection(5);
    incrementSpinner.setPageIncrement(10);
    incrementSpinner.setIncrement(5);
    incrementSpinner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    incrementSpinner.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            setWidgetIncrement();
          }
        });
  }
}

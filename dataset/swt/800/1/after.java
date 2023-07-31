class PlaceHold {
  void createDigitsGroup() {
    Group digitsGroup = new Group(controlGroup, SWT.NONE);
    digitsGroup.setLayout(new GridLayout());
    digitsGroup.setText(ControlExample.getResourceString("Digits"));
    digitsGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    digitsSpinner = new Spinner(digitsGroup, SWT.BORDER);
    digitsSpinner.setMaximum(8);
    digitsSpinner.setSelection(0);
    digitsSpinner.setPageIncrement(10);
    digitsSpinner.setIncrement(5);
    digitsSpinner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    digitsSpinner.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            setWidgetDigits();
          }
        });
  }
}

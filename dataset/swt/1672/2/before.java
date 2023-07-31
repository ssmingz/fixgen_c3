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
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.widthHint = 100;
    digitsSpinner.setLayoutData(data);
    digitsSpinner.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            setWidgetDigits();
          }
        });
  }
}

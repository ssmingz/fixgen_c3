class PlaceHold {
  void createThumbGroup() {
    Group thumbGroup = new Group(controlGroup, SWT.NONE);
    thumbGroup.setLayout(new GridLayout());
    thumbGroup.setText(ControlExample.getResourceString("Thumb"));
    thumbGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    thumbSpinner = new Spinner(thumbGroup, SWT.BORDER);
    thumbSpinner.setMaximum(100000);
    thumbSpinner.setSelection(getDefaultThumb());
    thumbSpinner.setPageIncrement(100);
    thumbSpinner.setIncrement(1);
    thumbSpinner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    thumbSpinner.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setWidgetThumb();
          }
        });
  }
}

class PlaceHold {
  void createThumbGroup() {
    Group thumbGroup = new Group(controlGroup, SWT.NONE);
    thumbGroup.setLayout(new GridLayout());
    thumbGroup.setText(ControlExample.getResourceString("Thumb"));
    thumbGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    thumbSpinner = new Spinner(thumbGroup, SWT.BORDER);
    thumbSpinner.setMaximum(100);
    thumbSpinner.setSelection(10);
    thumbSpinner.setPageIncrement(10);
    thumbSpinner.setIncrement(5);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.widthHint = 100;
    thumbSpinner.setLayoutData(data);
    thumbSpinner.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setWidgetThumb();
          }
        });
  }
}

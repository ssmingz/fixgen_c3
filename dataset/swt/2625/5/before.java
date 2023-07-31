class PlaceHold {
  void createSelectionGroup() {
    Group selectionGroup = new Group(controlGroup, SWT.NULL);
    selectionGroup.setLayout(new GridLayout());
    GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
    gridData.horizontalSpan = 2;
    selectionGroup.setLayoutData(gridData);
    selectionGroup.setText(resControls.getString("Selection"));
    selectionScale = new Scale(selectionGroup, SWT.NULL);
    selectionScale.setMaximum(100);
    selectionScale.setSelection(50);
    selectionScale.setPageIncrement(10);
    selectionScale.setIncrement(5);
    selectionScale.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            setWidgetSelection();
          }
        });
  }
}

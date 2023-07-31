class PlaceHold {
  void createStyleGroup() {
    super.createStyleGroup();
    topButton = new Button(styleGroup, SWT.RADIO);
    topButton.setText("SWT.TOP");
    topButton.setSelection(true);
    bottomButton = new Button(styleGroup, SWT.RADIO);
    bottomButton.setText("SWT.BOTTOM");
    borderButton = new Button(styleGroup, SWT.CHECK);
    borderButton.setText("SWT.BORDER");
    SelectionListener selectionListener =
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            if (!((Button) (event.widget)).getSelection()) {
              return;
            }
            recreateExampleWidgets();
          }
        };
    topButton.addSelectionListener(selectionListener);
    bottomButton.addSelectionListener(selectionListener);
  }
}

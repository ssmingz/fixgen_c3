class PlaceHold {
  void createControlWidgets() {
    noParentButton = new Button(parentStyleGroup, SWT.RADIO);
    noParentButton.setText(ControlExample.getResourceString("No_Parent"));
    parentButton = new Button(parentStyleGroup, SWT.RADIO);
    parentButton.setText(ControlExample.getResourceString("Parent"));
    noTrimButton = new Button(styleGroup, SWT.CHECK);
    noTrimButton.setText("SWT.NO_TRIM");
    closeButton = new Button(styleGroup, SWT.CHECK);
    closeButton.setText("SWT.CLOSE");
    titleButton = new Button(styleGroup, SWT.CHECK);
    titleButton.setText("SWT.TITLE");
    minButton = new Button(styleGroup, SWT.CHECK);
    minButton.setText("SWT.MIN");
    maxButton = new Button(styleGroup, SWT.CHECK);
    maxButton.setText("SWT.MAX");
    borderButton = new Button(styleGroup, SWT.CHECK);
    borderButton.setText("SWT.BORDER");
    resizeButton = new Button(styleGroup, SWT.CHECK);
    resizeButton.setText("SWT.RESIZE");
    onTopButton = new Button(styleGroup, SWT.CHECK);
    onTopButton.setText("SWT.ON_TOP");
    toolButton = new Button(styleGroup, SWT.CHECK);
    toolButton.setText("SWT.TOOL");
    modelessButton = new Button(modalStyleGroup, SWT.RADIO);
    modelessButton.setText("SWT.MODELESS");
    primaryModalButton = new Button(modalStyleGroup, SWT.RADIO);
    primaryModalButton.setText("SWT.PRIMARY_MODAL");
    applicationModalButton = new Button(modalStyleGroup, SWT.RADIO);
    applicationModalButton.setText("SWT.APPLICATION_MODAL");
    systemModalButton = new Button(modalStyleGroup, SWT.RADIO);
    systemModalButton.setText("SWT.SYSTEM_MODAL");
    imageButton = new Button(otherGroup, SWT.CHECK);
    imageButton.setText(ControlExample.getResourceString("Image"));
    createButton = new Button(controlGroup, SWT.NONE);
    GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
    createButton.setLayoutData(gridData);
    createButton.setText(ControlExample.getResourceString("Create_Shell"));
    closeAllButton = new Button(controlGroup, SWT.NONE);
    gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
    closeAllButton.setText(ControlExample.getResourceString("Close_All_Shells"));
    closeAllButton.setLayoutData(gridData);
    createButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            createButtonSelected(e);
          }
        });
    closeAllButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            closeAllShells();
          }
        });
    SelectionListener decorationButtonListener =
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            decorationButtonSelected(event);
          }
        };
    noTrimButton.addSelectionListener(decorationButtonListener);
    closeButton.addSelectionListener(decorationButtonListener);
    titleButton.addSelectionListener(decorationButtonListener);
    minButton.addSelectionListener(decorationButtonListener);
    maxButton.addSelectionListener(decorationButtonListener);
    borderButton.addSelectionListener(decorationButtonListener);
    resizeButton.addSelectionListener(decorationButtonListener);
    applicationModalButton.addSelectionListener(decorationButtonListener);
    systemModalButton.addSelectionListener(decorationButtonListener);
    noParentButton.setSelection(true);
    modelessButton.setSelection(true);
  }
}

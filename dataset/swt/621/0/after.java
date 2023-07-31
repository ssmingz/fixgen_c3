class PlaceHold {
  void createControlWidgets() {
    barButton = new Button(styleGroup, SWT.CHECK);
    barButton.setText("SWT.BAR");
    dropDownButton = new Button(styleGroup, SWT.CHECK);
    dropDownButton.setText("SWT.DROP_DOWN");
    popUpButton = new Button(styleGroup, SWT.CHECK);
    popUpButton.setText("SWT.POP_UP");
    noRadioGroupButton = new Button(styleGroup, SWT.CHECK);
    noRadioGroupButton.setText("SWT.NO_RADIO_GROUP");
    leftToRightButton = new Button(styleGroup, SWT.RADIO);
    leftToRightButton.setText("SWT.LEFT_TO_RIGHT");
    leftToRightButton.setSelection(true);
    rightToLeftButton = new Button(styleGroup, SWT.RADIO);
    rightToLeftButton.setText("SWT.RIGHT_TO_LEFT");
    cascadeButton = new Button(menuItemStyleGroup, SWT.CHECK);
    cascadeButton.setText("SWT.CASCADE");
    checkButton = new Button(menuItemStyleGroup, SWT.CHECK);
    checkButton.setText("SWT.CHECK");
    pushButton = new Button(menuItemStyleGroup, SWT.CHECK);
    pushButton.setText("SWT.PUSH");
    radioButton = new Button(menuItemStyleGroup, SWT.CHECK);
    radioButton.setText("SWT.RADIO");
    separatorButton = new Button(menuItemStyleGroup, SWT.CHECK);
    separatorButton.setText("SWT.SEPARATOR");
    enabledButton = new Button(otherGroup, SWT.CHECK);
    enabledButton.setText(ControlExample.getResourceString("Enabled"));
    enabledButton.setSelection(true);
    imagesButton = new Button(otherGroup, SWT.CHECK);
    imagesButton.setText(ControlExample.getResourceString("Images"));
    acceleratorsButton = new Button(otherGroup, SWT.CHECK);
    acceleratorsButton.setText(ControlExample.getResourceString("Accelerators"));
    mnemonicsButton = new Button(otherGroup, SWT.CHECK);
    mnemonicsButton.setText(ControlExample.getResourceString("Mnemonics"));
    subMenuButton = new Button(otherGroup, SWT.CHECK);
    subMenuButton.setText(ControlExample.getResourceString("SubMenu"));
    subSubMenuButton = new Button(otherGroup, SWT.CHECK);
    subSubMenuButton.setText(ControlExample.getResourceString("SubSubMenu"));
    new Label(controlGroup, SWT.NONE);
    createButton = new Button(controlGroup, SWT.NONE);
    createButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
    createButton.setText(ControlExample.getResourceString("Create_Shell"));
    closeAllButton = new Button(controlGroup, SWT.NONE);
    closeAllButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
    closeAllButton.setText(ControlExample.getResourceString("Close_All_Shells"));
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
    subMenuButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            subSubMenuButton.setEnabled(subMenuButton.getSelection());
          }
        });
    barButton.setSelection(true);
    dropDownButton.setSelection(true);
    popUpButton.setSelection(true);
    cascadeButton.setSelection(true);
    checkButton.setSelection(true);
    pushButton.setSelection(true);
    radioButton.setSelection(true);
    separatorButton.setSelection(true);
    subSubMenuButton.setEnabled(subMenuButton.getSelection());
  }
}

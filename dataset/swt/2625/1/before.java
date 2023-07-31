class PlaceHold {
  void createControlGroup() {
    controlGroup = new Group(tabFolderPage, SWT.NONE);
    GridLayout gridLayout = new GridLayout();
    controlGroup.setLayout(gridLayout);
    gridLayout.numColumns = 2;
    gridLayout.makeColumnsEqualWidth = true;
    controlGroup.setLayoutData(
        new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
    controlGroup.setText(resControls.getString("Parameters"));
    createStyleGroup();
    createDisplayGroup();
    createSizeGroup();
    SelectionListener selectionListener =
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            if ((event.widget.getStyle() & SWT.RADIO) != 0) {
              if (!((Button) (event.widget)).getSelection()) {
                return;
              }
            }
            recreateExampleWidgets();
          }
        };
    Control[] children = styleGroup.getChildren();
    for (int i = 0; i < children.length; i++) {
      if (children[i] instanceof Button) {
        Button button = ((Button) (children[i]));
        button.addSelectionListener(selectionListener);
      }
    }
  }
}

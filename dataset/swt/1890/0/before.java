class PlaceHold {
  void createExampleWidgets() {
    int style = SWT.NONE;
    if (singleButton.getSelection()) {
      style |= SWT.SINGLE;
    }
    if (multiButton.getSelection()) {
      style |= SWT.MULTI;
    }
    if (verticalButton.getSelection()) {
      style |= SWT.V_SCROLL;
    }
    if (horizontalButton.getSelection()) {
      style |= SWT.H_SCROLL;
    }
    if (checkButton.getSelection()) {
      style |= SWT.CHECK;
    }
    if (fullSelectionButton.getSelection()) {
      style |= SWT.FULL_SELECTION;
    }
    if (borderButton.getSelection()) {
      style |= SWT.BORDER;
    }
    table1 = new Table(tableGroup, style);
    for (int i = 0; i < TableTab.columnTitles.length; i++) {
      TableColumn tableColumn = new TableColumn(table1, SWT.NONE);
      tableColumn.setText(columnTitles[i]);
    }
    for (int i = 0; i < 16; i++) {
      TableItem item = new TableItem(table1, SWT.NONE);
      item.setImage(instance.images[i % 3]);
      switch (i % 3) {
        case 0:
          stringLine0[0] = ControlExample.getResourceString("Index") + i;
          item.setText(stringLine0);
          break;
        case 1:
          stringLine1[0] = ControlExample.getResourceString("Index") + i;
          item.setText(stringLine1);
          break;
        case 2:
          stringLine2[0] = ControlExample.getResourceString("Index") + i;
          item.setText(stringLine2);
          break;
      }
    }
    for (int i = 0; i < TableTab.columnTitles.length; i++) {
      TableColumn tableColumn = table1.getColumn(i);
      tableColumn.pack();
    }
  }
}

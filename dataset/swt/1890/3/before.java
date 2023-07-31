class PlaceHold {
  void createExampleWidgets() {
    int style = getDefaultStyle();
    if (topButton.getSelection()) {
      style |= SWT.TOP;
    }
    if (bottomButton.getSelection()) {
      style |= SWT.BOTTOM;
    }
    tabFolder1 = new TabFolder(tabFolderGroup, style);
    for (int i = 0; i < TabFolderTab.TabItems1.length; i++) {
      TabItem item = new TabItem(tabFolder1, SWT.NONE);
      item.setText(TabItems1[i]);
      Label label = new Label(tabFolder1, SWT.NONE);
      label.setText((ControlExample.getResourceString("TabItem_content") + ": ") + i);
      item.setControl(label);
    }
  }
}

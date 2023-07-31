class PlaceHold {
  void showList(Rectangle rect, int alignment) {
    if (items.length == 0) {
      return;
    }
    int lastIndex = getLastIndex();
    if (((!single) && (firstIndex == 0)) && (lastIndex == (items.length - 1))) {
      return;
    }
    if ((single && (items.length == 1)) && (selectedIndex != (-1))) {
      return;
    }
    Menu menu = new Menu(this);
    final String id = "CTabFolder_showList_Index";
    for (int i = 0; i < items.length; i++) {
      if (single) {
        if (i == selectedIndex) {
          continue;
        }
      } else if ((i >= firstIndex) && (i <= lastIndex)) {
        continue;
      }
      CTabItem tab = items[i];
      MenuItem item = new MenuItem(menu, SWT.NONE);
      item.setText(tab.getText());
      item.setImage(tab.getImage());
      item.setData(id, tab);
      item.addSelectionListener(
          new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
              MenuItem item = ((MenuItem) (e.widget));
              int index = indexOf(((CTabItem) (item.getData(id))));
              CTabFolder.this.setSelection(index, true);
            }
          });
    }
    Point size = menu.getSize();
    int x = rect.x;
    int y = rect.y + rect.height;
    Point location = getDisplay().map(this, null, x, y);
    menu.setLocation(location.x, location.y);
    menu.setVisible(true);
    Display display = getDisplay();
    while ((!menu.isDisposed()) && menu.isVisible()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    menu.dispose();
  }
}

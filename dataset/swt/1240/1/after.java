class PlaceHold {
  int itemDataProc(int browser, int id, int property, int itemData, int setValue) {
    int row = id - 1;
    if (!((0 <= row) && (row < items.length))) {
      return OS.noErr;
    }
    switch (property) {
      case CHECK_COLUMN_ID:
        {
          TableItem item = _getItem(row);
          if (setValue != 0) {
            item.checked = !item.checked;
            if (item.checked && item.grayed) {
              OS.SetDataBrowserItemDataButtonValue(itemData, ((short) (kThemeButtonMixed)));
            } else {
              int theData = (item.checked) ? OS.kThemeButtonOn : OS.kThemeButtonOff;
              OS.SetDataBrowserItemDataButtonValue(itemData, ((short) (theData)));
            }
            Event event = new Event();
            event.item = item;
            event.detail = SWT.CHECK;
            postEvent(Selection, event);
            if (!item.checked) {
              item.redraw(Table.CHECK_COLUMN_ID);
            }
          } else {
            int theData = OS.kThemeButtonOff;
            if (item.checked) {
              theData = (item.grayed) ? OS.kThemeButtonMixed : OS.kThemeButtonOn;
            }
            OS.SetDataBrowserItemDataButtonValue(itemData, ((short) (theData)));
          }
          break;
        }
    }
    return OS.noErr;
  }
}

class PlaceHold {
  int itemDataProc(int browser, int id, int property, int itemData, int setValue) {
    switch (property) {
      case CHECK_COLUMN_ID:
        {
          TreeItem item = _getItem(id, true);
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
              item.redraw(Tree.CHECK_COLUMN_ID);
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
      case OS.kDataBrowserItemIsContainerProperty:
        {
          TreeItem item = _getItem(id, true);
          if (item.itemCount > 0) {
            OS.SetDataBrowserItemDataBooleanValue(itemData, true);
          }
          break;
        }
    }
    return OS.noErr;
  }
}

class PlaceHold {
  int itemDataProc(int browser, int id, int property, int itemData, int setValue) {
    int index = id - 1;
    if (!((0 <= index) && (index < items.length))) {
      return OS.noErr;
    }
    TreeItem item = items[index];
    switch (property) {
      case CHECK_COLUMN_ID:
        {
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
          for (int i = 0; i < items.length; i++) {
            if ((items[i] != null) && (items[i].parentItem == item)) {
              OS.SetDataBrowserItemDataBooleanValue(itemData, true);
            }
          }
          break;
        }
    }
    return OS.noErr;
  }
}

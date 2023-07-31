class PlaceHold {
  void createItem(MenuItem item, int index) {
    checkWidget();
    int count = OS.CountMenuItems(handle);
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_INVALID_RANGE);
    }
    parent.add(item);
    boolean success = false;
    int attributes = 0;
    if ((item.style & SWT.SEPARATOR) != 0) {
      attributes = OS.kMenuItemAttrSeparator;
    }
    if (OS.InsertMenuItemTextWithCFString(handle, 0, ((short) (index)), attributes, item.id)
        == OS.noErr) {
      success = true;
    }
    if (!success) {
      parent.remove(item);
      error(ERROR_ITEM_NOT_ADDED);
    }
  }
}

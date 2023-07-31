class PlaceHold {
  public void clear(int index) {
    checkWidget();
    int count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
    if (!((0 <= index) && (index < count))) {
      error(ERROR_INVALID_RANGE);
    }
    TableItem item = items[index];
    if (item != null) {
      if (item != currentItem) {
        item.clear();
      }
      if (((style & SWT.VIRTUAL) == 0) && item.cached) {
        LVITEM lvItem = new LVITEM();
        lvItem.mask = OS.LVIF_TEXT | OS.LVIF_INDENT;
        lvItem.pszText = OS.LPSTR_TEXTCALLBACK;
        lvItem.iItem = index;
        OS.SendMessage(handle, LVM_SETITEM, 0, lvItem);
        item.cached = false;
      }
      if (((currentItem == null) && (drawCount == 0)) && OS.IsWindowVisible(handle)) {
        OS.SendMessage(handle, LVM_REDRAWITEMS, index, index);
      }
      setScrollWidth(item, false);
    }
  }
}

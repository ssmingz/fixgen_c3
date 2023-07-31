class PlaceHold {
  public void clearAll() {
    checkWidget();
    LVITEM lvItem = null;
    boolean cleared = false;
    int count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
    for (int i = 0; i < count; i++) {
      TableItem item = items[i];
      if (item != null) {
        if (item != currentItem) {
          cleared = true;
          item.clear();
        }
        if (((style & SWT.VIRTUAL) == 0) && item.cached) {
          if (lvItem == null) {
            lvItem = new LVITEM();
            lvItem.mask = OS.LVIF_TEXT | OS.LVIF_INDENT;
            lvItem.pszText = OS.LPSTR_TEXTCALLBACK;
          }
          lvItem.iItem = i;
          OS.SendMessage(handle, LVM_SETITEM, 0, lvItem);
          item.cached = false;
        }
      }
    }
    if (cleared) {
      if (((currentItem == null) && getDrawing()) && OS.IsWindowVisible(handle)) {
        OS.SendMessage(handle, LVM_REDRAWITEMS, 0, count - 1);
      }
      setScrollWidth(null, false);
    }
  }
}

class PlaceHold {
  public void setText(int index, String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (index == 0) {
      if (string.equals(text)) {
        return;
      }
      super.setText(string);
    }
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    if ((strings == null) && (index != 0)) {
      strings = new String[count];
      strings[0] = text;
    }
    if (strings != null) {
      if (string.equals(strings[index])) {
        return;
      }
      strings[index] = string;
    }
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    if (index == 0) {
      if (((parent.style & SWT.VIRTUAL) == 0) && cached) {
        int itemIndex = parent.indexOf(this);
        if (itemIndex != (-1)) {
          int hwnd = parent.handle;
          LVITEM lvItem = new LVITEM();
          lvItem.mask = OS.LVIF_TEXT;
          lvItem.iItem = itemIndex;
          lvItem.pszText = OS.LPSTR_TEXTCALLBACK;
          OS.SendMessage(hwnd, LVM_SETITEM, 0, lvItem);
          cached = false;
        }
      }
      parent.setScrollWidth(this, false);
    }
    redraw(index, true, false);
  }
}

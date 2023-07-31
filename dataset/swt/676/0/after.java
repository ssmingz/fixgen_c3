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
      if ((((parent.style & SWT.VIRTUAL) == 0) && (!cached)) && (!parent.painted)) {
        return;
      }
      if (this != parent.currentItem) {
        int hwnd = parent.handle;
        TVITEM tvItem = new TVITEM();
        tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_TEXT;
        tvItem.hItem = handle;
        tvItem.pszText = OS.LPSTR_TEXTCALLBACK;
        OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
      }
    } else {
      redraw(index, true, false);
    }
  }
}

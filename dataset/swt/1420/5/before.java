class PlaceHold {
  public void setImageIndent(int indent) {
    checkWidget();
    if (indent < 0) {
      return;
    }
    if (imageIndent == indent) {
      return;
    }
    imageIndent = indent;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    } else {
      int index = parent.indexOf(this);
      if (index != (-1)) {
        int hwnd = parent.handle;
        LVITEM lvItem = new LVITEM();
        lvItem.mask = OS.LVIF_INDENT;
        lvItem.iItem = index;
        lvItem.iIndent = indent;
        OS.SendMessage(hwnd, LVM_SETITEM, 0, lvItem);
      }
    }
    parent.setScrollWidth(this, false);
    redraw();
  }
}

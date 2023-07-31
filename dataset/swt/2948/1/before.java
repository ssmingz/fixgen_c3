class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    int topIndex = OS.SendMessage(handle, LVM_GETTOPINDEX, 0, 0);
    if (index == topIndex) {
      return;
    }
    if (OS.SendMessage(handle, LVM_GETCOUNTPERPAGE, 0, 0) <= 0) {
      OS.SendMessage(handle, LVM_ENSUREVISIBLE, index, 1);
      if (index != OS.SendMessage(handle, LVM_GETTOPINDEX, 0, 0)) {
        OS.SendMessage(handle, LVM_ENSUREVISIBLE, index, 1);
      }
      return;
    }
    RECT rect = new RECT();
    rect.left = OS.LVIR_BOUNDS;
    ignoreCustomDraw = true;
    OS.SendMessage(handle, LVM_GETITEMRECT, 0, rect);
    ignoreCustomDraw = false;
    int dy = (index - topIndex) * (rect.bottom - rect.top);
    OS.SendMessage(handle, LVM_SCROLL, 0, dy);
  }
}

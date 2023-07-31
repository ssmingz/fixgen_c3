class PlaceHold {
  public void setChecked(boolean checked) {
    checkWidget();
    if ((parent.style & SWT.CHECK) == 0) {
      return;
    }
    int hwnd = parent.handle;
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_STATE;
    tvItem.stateMask = OS.TVIS_STATEIMAGEMASK;
    tvItem.hItem = handle;
    OS.SendMessage(hwnd, TVM_GETITEM, 0, tvItem);
    int state = tvItem.state >> 12;
    if (checked) {
      if ((state & 0x1) != 0) {
        state++;
      }
    } else if ((state & 0x1) == 0) {
      --state;
    }
    state <<= 12;
    if (tvItem.state == state) {
      return;
    }
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    tvItem.state = state;
    OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
    if ((parent.style & SWT.VIRTUAL) != 0) {
      if ((parent.currentItem == this) && OS.IsWindowVisible(hwnd)) {
        RECT rect = new RECT();
        rect.left = handle;
        if (OS.SendMessage(hwnd, TVM_GETITEMRECT, 0, rect) != 0) {
          OS.InvalidateRect(hwnd, rect, true);
        }
      }
    }
  }
}

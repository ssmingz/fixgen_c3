class PlaceHold {
  void clear() {
    text = "";
    image = null;
    strings = null;
    images = null;
    if ((parent.style & SWT.CHECK) != 0) {
      int hwnd = parent.handle;
      TVITEM tvItem = new TVITEM();
      tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_STATE;
      tvItem.stateMask = OS.TVIS_STATEIMAGEMASK;
      tvItem.state = 1 << 12;
      tvItem.hItem = handle;
      OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
    }
    background = foreground = -1;
    font = null;
    cellBackground = cellForeground = null;
    cellFont = null;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = false;
    }
  }
}

class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if ((style & (SWT.BAR | SWT.DROP_DOWN)) != 0) {
      return;
    }
    int hwndParent = parent.handle;
    if (!visible) {
      OS.SendMessage(hwndParent, WM_CANCELMODE, 0, 0);
      return;
    }
    int flags = (OS.TPM_LEFTBUTTON | OS.TPM_RIGHTBUTTON) | OS.TPM_LEFTALIGN;
    int nX = x;
    int nY = y;
    if (!hasLocation) {
      int pos = OS.GetMessagePos();
      nX = ((short) (pos & 0xffff));
      nY = ((short) (pos >> 16));
    }
    boolean success = OS.TrackPopupMenu(handle, flags, nX, nY, 0, hwndParent, null);
    if ((!success) && (GetMenuItemCount(handle) == 0)) {
      OS.SendMessage(hwndParent, WM_MENUSELECT, 0xffff0000, 0);
    }
  }
}

class PlaceHold {
  void _setVisible(boolean visible) {
    if ((style & (SWT.BAR | SWT.DROP_DOWN)) != 0) {
      return;
    }
    long hwndParent = parent.handle;
    if (visible) {
      int flags = OS.TPM_LEFTBUTTON;
      if (OS.GetKeyState(VK_LBUTTON) >= 0) {
        flags |= OS.TPM_RIGHTBUTTON;
      }
      if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        flags |= OS.TPM_RIGHTALIGN;
      }
      if ((parent.style & SWT.MIRRORED) != 0) {
        flags &= ~OS.TPM_RIGHTALIGN;
        if ((style & SWT.LEFT_TO_RIGHT) != 0) {
          flags |= OS.TPM_RIGHTALIGN;
        }
      }
      int nX = x;
      int nY = y;
      if (!hasLocation) {
        int pos = OS.GetMessagePos();
        nX = OS.GET_X_LPARAM(pos);
        nY = OS.GET_Y_LPARAM(pos);
      }
      hasLocation = false;
      boolean success = OS.TrackPopupMenu(handle, flags, nX, nY, 0, hwndParent, null);
      if ((!success) && (GetMenuItemCount(handle) == 0)) {
        OS.SendMessage(hwndParent, WM_MENUSELECT, OS.MAKEWPARAM(0, 0xffff), 0);
      }
    } else {
      OS.SendMessage(hwndParent, WM_CANCELMODE, 0, 0);
    }
    long hFocus = OS.GetFocus();
    if (hFocus != 0) {
      OS.NotifyWinEvent(EVENT_OBJECT_FOCUS, hFocus, OBJID_CLIENT, 0);
    }
  }
}

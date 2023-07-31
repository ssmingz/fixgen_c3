class PlaceHold {
  LRESULT CDDS_POSTPAINT(NMLVCUSTOMDRAW nmcd, int wParam, int lParam) {
    if (ignoreCustomDraw) {
      return null;
    }
    if (((--customCount) == 0) && OS.IsWindowVisible(handle)) {
      if ((!explorerTheme) && ((style & SWT.FULL_SELECTION) != 0)) {
        if (((int) (OS.SendMessage(handle, LVM_GETBKCOLOR, 0, 0))) == OS.CLR_NONE) {
          int dwExStyle = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
          if ((dwExStyle & OS.LVS_EX_FULLROWSELECT) == 0) {
            int bits = OS.LVS_EX_FULLROWSELECT;
            if (OS.IsWinCE) {
              RECT rect = new RECT();
              boolean damaged = OS.GetUpdateRect(handle, rect, true);
              OS.SendMessage(handle, LVM_SETEXTENDEDLISTVIEWSTYLE, bits, bits);
              OS.ValidateRect(handle, null);
              if (damaged) {
                OS.InvalidateRect(handle, rect, true);
              }
            } else {
              int rgn = OS.CreateRectRgn(0, 0, 0, 0);
              int result = OS.GetUpdateRgn(handle, rgn, true);
              OS.SendMessage(handle, LVM_SETEXTENDEDLISTVIEWSTYLE, bits, bits);
              OS.ValidateRect(handle, null);
              if (result != OS.NULLREGION) {
                OS.InvalidateRgn(handle, rgn, true);
              }
              OS.DeleteObject(rgn);
            }
          }
        }
      }
    }
    return null;
  }
}

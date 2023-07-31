class PlaceHold {
  LRESULT CDDS_SUBITEMPOSTPAINT(NMLVCUSTOMDRAW nmcd, int wParam, int lParam) {
    if (ignoreCustomDraw) {
      return null;
    }
    if (nmcd.left == nmcd.right) {
      return new LRESULT(OS.CDRF_DODEFAULT);
    }
    int hDC = nmcd.hdc;
    if (ignoreDrawForeground) {
      OS.RestoreDC(hDC, -1);
    }
    if (OS.IsWindowVisible(handle)) {
      if (((int) (OS.SendMessage(handle, LVM_GETBKCOLOR, 0, 0))) != OS.CLR_NONE) {
        if ((sortDirection & (SWT.UP | SWT.DOWN)) != 0) {
          if ((sortColumn != null) && (!sortColumn.isDisposed())) {
            int oldColumn = ((int) (OS.SendMessage(handle, LVM_GETSELECTEDCOLUMN, 0, 0)));
            if (oldColumn == (-1)) {
              int newColumn = indexOf(sortColumn);
              int result = 0;
              int rgn = 0;
              if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
                rgn = OS.CreateRectRgn(0, 0, 0, 0);
                result = OS.GetUpdateRgn(handle, rgn, true);
              }
              OS.SendMessage(handle, LVM_SETSELECTEDCOLUMN, newColumn, 0);
              if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
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
      if (hooks(PaintItem)) {
        TableItem item = _getItem(((int) (nmcd.dwItemSpec)));
        sendPaintItemEvent(item, nmcd);
      }
    }
    return null;
  }
}

class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    if (hwndHeader != 0) {
      HDITEM hdItem = new HDITEM();
      hdItem.mask = OS.HDI_WIDTH;
      int count = ((int) (OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0)));
      for (int i = 0; i < count; i++) {
        OS.SendMessage(hwndHeader, HDM_GETITEM, i, hdItem);
        width += hdItem.cxy;
      }
      RECT rect = new RECT();
      OS.GetWindowRect(hwndHeader, rect);
      height += rect.bottom - rect.top;
    }
    RECT rect = new RECT();
    int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
    while (hItem != 0) {
      if (((style & SWT.VIRTUAL) == 0) && (!painted)) {
        TVITEM tvItem = new TVITEM();
        tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_TEXT;
        tvItem.hItem = hItem;
        tvItem.pszText = OS.LPSTR_TEXTCALLBACK;
        ignoreCustomDraw = true;
        OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
        ignoreCustomDraw = false;
      }
      if (OS.TreeView_GetItemRect(handle, hItem, rect, true)) {
        width = Math.max(width, rect.right);
        height += rect.bottom - rect.top;
      }
      hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_NEXTVISIBLE, hItem);
    }
    if (width == 0) {
      width = DEFAULT_WIDTH;
    }
    if (height == 0) {
      height = DEFAULT_HEIGHT;
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    int border = getBorderWidth();
    width += border * 2;
    height += border * 2;
    if ((style & SWT.V_SCROLL) != 0) {
      width += OS.GetSystemMetrics(SM_CXVSCROLL);
    }
    if ((style & SWT.H_SCROLL) != 0) {
      height += OS.GetSystemMetrics(SM_CYHSCROLL);
    }
    return new Point(width, height);
  }
}

class PlaceHold {
  void createParent() {
    forceResize();
    RECT rect = new RECT();
    OS.GetWindowRect(handle, rect);
    OS.MapWindowPoints(0, handle, rect, 2);
    int oldStyle = OS.GetWindowLong(handle, GWL_STYLE);
    int newStyle = super.widgetStyle() & (~OS.WS_VISIBLE);
    if ((oldStyle & OS.WS_DISABLED) != 0) {
      newStyle |= OS.WS_DISABLED;
    }
    hwndParent =
        OS.CreateWindowEx(
            super.widgetExtStyle(),
            super.windowClass(),
            null,
            newStyle,
            rect.left,
            rect.top,
            rect.right - rect.left,
            rect.bottom - rect.top,
            handle,
            0,
            OS.GetModuleHandle(null),
            null);
    if (hwndParent == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.SetWindowLong(hwndParent, GWL_ID, hwndParent);
    int bits = 0;
    if (OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
      bits |= OS.WS_EX_NOINHERITLAYOUT;
      if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        bits |= OS.WS_EX_LAYOUTRTL;
      }
    }
    hwndHeader =
        OS.CreateWindowEx(
            bits,
            new TCHAR(0, OS.WC_HEADER, true),
            null,
            (((OS.HDS_BUTTONS | OS.HDS_FULLDRAG) | OS.HDS_HIDDEN) | OS.WS_CHILD)
                | OS.WS_CLIPSIBLINGS,
            0,
            0,
            0,
            0,
            hwndParent,
            0,
            OS.GetModuleHandle(null),
            null);
    if (hwndHeader == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.SetWindowLong(hwndHeader, GWL_ID, hwndHeader);
    if (OS.IsDBLocale) {
      int hIMC = OS.ImmGetContext(handle);
      OS.ImmAssociateContext(hwndParent, hIMC);
      OS.ImmAssociateContext(hwndHeader, hIMC);
      OS.ImmReleaseContext(handle, hIMC);
    }
    if (!OS.IsPPC) {
      if ((style & SWT.BORDER) != 0) {
        int oldExStyle = OS.GetWindowLong(handle, GWL_EXSTYLE);
        oldExStyle &= ~OS.WS_EX_CLIENTEDGE;
        OS.SetWindowLong(handle, GWL_EXSTYLE, oldExStyle);
      }
    }
    int hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
    if (hFont != 0) {
      OS.SendMessage(hwndHeader, WM_SETFONT, hFont, 0);
    }
    int hImageList = OS.SendMessage(handle, TVM_GETIMAGELIST, TVSIL_NORMAL, 0);
    if (hImageList != 0) {
      OS.SendMessage(hwndHeader, HDM_SETIMAGELIST, 0, hImageList);
    }
    int hwndInsertAfter = OS.GetWindow(handle, GW_HWNDPREV);
    int flags = (OS.SWP_NOSIZE | OS.SWP_NOMOVE) | OS.SWP_NOACTIVATE;
    SetWindowPos(hwndParent, hwndInsertAfter, 0, 0, 0, 0, flags);
    SCROLLINFO info = new SCROLLINFO();
    info.cbSize = SCROLLINFO.sizeof;
    info.fMask = OS.SIF_RANGE | OS.SIF_PAGE;
    OS.GetScrollInfo(hwndParent, SB_HORZ, info);
    info.nPage = info.nMax + 1;
    OS.SetScrollInfo(hwndParent, SB_HORZ, info, true);
    OS.GetScrollInfo(hwndParent, SB_VERT, info);
    info.nPage = info.nMax + 1;
    OS.SetScrollInfo(hwndParent, SB_VERT, info, true);
    customDraw = true;
    deregister();
    if ((oldStyle & OS.WS_VISIBLE) != 0) {
      OS.ShowWindow(hwndParent, SW_SHOW);
    }
    int hwndFocus = OS.GetFocus();
    if (hwndFocus == handle) {
      OS.SetFocus(hwndParent);
    }
    OS.SetParent(handle, hwndParent);
    if (hwndFocus == handle) {
      OS.SetFocus(handle);
    }
    register();
  }
}

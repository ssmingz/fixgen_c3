class PlaceHold {
  public Rectangle getClientArea() {
    checkWidget();
    if (OS.IsHPC) {
      Rectangle rect = super.getClientArea();
      if (menuBar != null) {
        int hwndCB = menuBar.hwndCB;
        int height = OS.CommandBar_Height(hwndCB);
        rect.y += height;
        rect.height = Math.max(0, rect.height - height);
      }
      return rect;
    }
    if (!OS.IsWinCE) {
      if (OS.IsIconic(handle)) {
        WINDOWPLACEMENT lpwndpl = new WINDOWPLACEMENT();
        lpwndpl.length = WINDOWPLACEMENT.sizeof;
        OS.GetWindowPlacement(handle, lpwndpl);
        if ((lpwndpl.flags & OS.WPF_RESTORETOMAXIMIZED) != 0) {
          return new Rectangle(0, 0, oldWidth, oldHeight);
        }
        int width = lpwndpl.right - lpwndpl.left;
        int height = lpwndpl.bottom - lpwndpl.top;
        if (horizontalBar != null) {
          width -= OS.GetSystemMetrics(SM_CYHSCROLL);
        }
        if (verticalBar != null) {
          height -= OS.GetSystemMetrics(SM_CXVSCROLL);
        }
        RECT rect = new RECT();
        int bits1 = OS.GetWindowLong(handle, GWL_STYLE);
        int bits2 = OS.GetWindowLong(handle, GWL_EXSTYLE);
        boolean hasMenu = (OS.IsWinCE) ? false : OS.GetMenu(handle) != 0;
        OS.AdjustWindowRectEx(rect, bits1, hasMenu, bits2);
        width = Math.max(0, width - (rect.right - rect.left));
        height = Math.max(0, height - (rect.bottom - rect.top));
        return new Rectangle(0, 0, width, height);
      }
    }
    return super.getClientArea();
  }
}

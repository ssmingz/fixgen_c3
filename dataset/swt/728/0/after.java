class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((color != null) && (color.handle != parent.getForegroundPixel())) {
      parent.customDraw = true;
    }
    if (((color == null) && (foreground != (-1)))
        || ((color != null) && (foreground != color.handle))) {
      int hwnd = parent.handle;
      if (OS.IsWindowVisible(hwnd)) {
        int itemIndex = parent.indexOf(this);
        RECT rect = new RECT();
        rect.left = OS.LVIR_BOUNDS;
        OS.SendMessage(hwnd, LVM_GETITEMRECT, itemIndex, rect);
        int width = rect.right - rect.left;
        int height = rect.bottom - rect.top;
        if ((width > 0) || (height > 0)) {
          if (OS.IsWinCE) {
            OS.InvalidateRect(hwnd, rect, true);
          } else {
            int flags = ((OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE) | OS.RDW_ALLCHILDREN;
            OS.RedrawWindow(hwnd, rect, 0, flags);
          }
        }
      }
    }
    foreground = (color == null) ? -1 : color.handle;
  }
}

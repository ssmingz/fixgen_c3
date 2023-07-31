class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((color != null) && (color.handle != parent.getBackgroundPixel())) {
      parent.customDraw = true;
    }
    if (((color == null) && (background != (-1)))
        || ((color != null) && (background != color.handle))) {
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
    background = color.handle;
  }
}

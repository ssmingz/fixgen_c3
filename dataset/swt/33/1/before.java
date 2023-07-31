class PlaceHold {
  public void getClipping(Region region) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (region == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int result = OS.GetClipRgn(handle, region.handle);
    if (result != 1) {
      RECT rect = new RECT();
      OS.GetClipBox(handle, rect);
      OS.SetRectRgn(region.handle, rect.left, rect.top, rect.right, rect.bottom);
    }
    if (!OS.IsWinCE) {
      int flags = 0;
      if (((OS.WIN32_MAJOR << 16) | OS.WIN32_MINOR) >= ((4 << 16) | 10)) {
        flags = OS.GetLayout(handle);
      }
      int hwnd = data.hwnd;
      if (((hwnd != 0) && (data.ps != null)) && ((flags & OS.LAYOUT_RTL) == 0)) {
        int sysRgn = OS.CreateRectRgn(0, 0, 0, 0);
        if (OS.GetRandomRgn(handle, sysRgn, SYSRGN) == 1) {
          if (OS.IsWinNT) {
            POINT pt = new POINT();
            OS.MapWindowPoints(0, hwnd, pt, 1);
            OS.OffsetRgn(sysRgn, pt.x, pt.y);
          }
          OS.CombineRgn(region.handle, sysRgn, region.handle, RGN_AND);
        }
        OS.DeleteObject(sysRgn);
      }
    }
  }
}

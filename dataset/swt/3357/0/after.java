class PlaceHold {
  public void copyArea(int srcX, int srcY, int width, int height, int destX, int destY) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (OS.IsWinCE) {
      SWT.error(ERROR_NOT_IMPLEMENTED);
    }
    int hwnd = OS.WindowFromDC(handle);
    if (hwnd == 0) {
      OS.BitBlt(handle, destX, destY, width, height, handle, srcX, srcY, SRCCOPY);
    } else {
      RECT lprcClip = null;
      int hrgn = OS.CreateRectRgn(0, 0, 0, 0);
      if (OS.GetClipRgn(handle, hrgn) == 1) {
        lprcClip = new RECT();
        OS.GetRgnBox(hrgn, lprcClip);
      }
      OS.DeleteObject(hrgn);
      RECT lprcScroll = new RECT();
      OS.SetRect(lprcScroll, srcX, srcY, srcX + width, srcY + height);
      OS.ScrollWindowEx(
          hwnd,
          destX - srcX,
          destY - srcY,
          lprcScroll,
          lprcClip,
          0,
          null,
          OS.SW_INVALIDATE | OS.SW_ERASE);
    }
  }
}

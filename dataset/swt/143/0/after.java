class PlaceHold {
  public Rectangle getBounds() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    switch (type) {
      case SWT.BITMAP:
        BITMAP bm = new BITMAP();
        OS.GetObject(handle, sizeof, bm);
        return new Rectangle(0, 0, bm.bmWidth, bm.bmHeight);
      case SWT.ICON:
        ICONINFO info = new ICONINFO();
        if (OS.IsWinCE) {
          SWT.error(ERROR_NOT_IMPLEMENTED);
        }
        OS.GetIconInfo(handle, info);
        int hBitmap = info.hbmColor;
        if (hBitmap == 0) {
          hBitmap = info.hbmMask;
        }
        bm = new BITMAP();
        OS.GetObject(hBitmap, sizeof, bm);
        if (hBitmap == info.hbmMask) {
          bm.bmHeight /= 2;
        }
        if (info.hbmColor != 0) {
          OS.DeleteObject(info.hbmColor);
        }
        if (info.hbmMask != 0) {
          OS.DeleteObject(info.hbmMask);
        }
        return new Rectangle(0, 0, bm.bmWidth, bm.bmHeight);
      default:
        SWT.error(ERROR_UNSUPPORTED_FORMAT);
        return null;
    }
  }
}

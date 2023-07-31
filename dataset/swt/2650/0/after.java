class PlaceHold {
  LRESULT wmMeasureChild(int wParam, int lParam) {
    MEASUREITEMSTRUCT struct = new MEASUREITEMSTRUCT();
    OS.MoveMemory(struct, lParam, sizeof);
    if (image != null) {
      int hImage = image.handle;
      BITMAP bm = new BITMAP();
      switch (image.type) {
        case SWT.BITMAP:
          OS.GetObject(hImage, sizeof, bm);
          break;
        case SWT.ICON:
          ICONINFO info = new ICONINFO();
          if (OS.IsWinCE) {
            SWT.error(ERROR_NOT_IMPLEMENTED);
          }
          OS.GetIconInfo(hImage, info);
          int hBitmap = info.hbmColor;
          if (hBitmap == 0) {
            hBitmap = info.hbmMask;
          }
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
          break;
      }
      struct.itemWidth = bm.bmWidth + 4;
      struct.itemHeight = bm.bmHeight + 4;
    }
    OS.MoveMemory(lParam, struct, sizeof);
    return null;
  }
}

class PlaceHold {
  public Color getBackground() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (transparentPixel == (-1)) {
      return null;
    }
    int hDC = device.internal_new_GC(null);
    BITMAP bm = new BITMAP();
    OS.GetObject(handle, sizeof, bm);
    int hdcMem = OS.CreateCompatibleDC(hDC);
    int hOldObject = OS.SelectObject(hdcMem, handle);
    int red = 0;
    int green = 0;
    int blue = 0;
    if (bm.bmBitsPixel <= 8) {
      byte[] color = new byte[4];
      if (OS.IsWinCE) {
        SWT.error(ERROR_NOT_IMPLEMENTED);
      }
      int numColors = OS.GetDIBColorTable(hdcMem, transparentPixel, 1, color);
      blue = color[0] & 0xff;
      green = color[1] & 0xff;
      red = color[2] & 0xff;
    } else {
      switch (bm.bmBitsPixel) {
        case 16:
          blue = (transparentPixel & 0x1f) << 3;
          green = (transparentPixel & 0x3e0) >> 2;
          red = (transparentPixel & 0x7c00) >> 7;
          break;
        case 24:
          blue = (transparentPixel & 0xff0000) >> 16;
          green = (transparentPixel & 0xff00) >> 8;
          red = transparentPixel & 0xff;
          break;
        case 32:
          blue = (transparentPixel & 0xff000000) >>> 24;
          green = (transparentPixel & 0xff0000) >> 16;
          red = (transparentPixel & 0xff00) >> 8;
          break;
        default:
          return null;
      }
    }
    OS.SelectObject(hdcMem, hOldObject);
    OS.DeleteDC(hdcMem);
    device.internal_dispose_GC(hDC, null);
    return Color.win32_new(device, ((0x2000000 | (blue << 16)) | (green << 8)) | red);
  }
}

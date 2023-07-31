class PlaceHold {
  void init(Drawable drawable, GCData data, int hDC) {
    int foreground = data.foreground;
    if ((foreground != (-1)) && (OS.GetTextColor(hDC) != foreground)) {
      OS.SetTextColor(hDC, foreground);
      int newPen = OS.CreatePen(PS_SOLID, 0, foreground);
      OS.SelectObject(hDC, newPen);
      if (data.hPen != 0) {
        OS.DeleteObject(data.hPen);
      }
      data.hPen = newPen;
    }
    int background = data.background;
    if ((background != (-1)) && (OS.GetBkColor(hDC) != background)) {
      OS.SetBkColor(hDC, background);
      int newBrush = OS.CreateSolidBrush(background);
      OS.SelectObject(hDC, newBrush);
      if (data.hBrush != 0) {
        OS.DeleteObject(data.hBrush);
      }
      data.hBrush = newBrush;
    }
    int hFont = data.hFont;
    if (hFont != 0) {
      OS.SelectObject(hDC, hFont);
    }
    int hPalette = data.device.hPalette;
    if (hPalette != 0) {
      OS.SelectPalette(hDC, hPalette, true);
      OS.RealizePalette(hDC);
    }
    Image image = data.image;
    if (image != null) {
      data.hNullBitmap = OS.SelectObject(hDC, image.handle);
      image.memGC = this;
    }
    int layout = data.layout;
    if (layout != (-1)) {
      if ((!OS.IsWinCE) && (((OS.WIN32_MAJOR << 16) | OS.WIN32_MINOR) >= ((4 << 16) | 10))) {
        int flags = OS.GetLayout(hDC);
        if ((flags & OS.LAYOUT_RTL) != (layout & OS.LAYOUT_RTL)) {
          flags &= ~OS.LAYOUT_RTL;
          OS.SetLayout(hDC, flags | layout);
        }
        if ((data.style & SWT.RIGHT_TO_LEFT) != 0) {
          data.style |= SWT.MIRRORED;
        }
      }
    }
    this.drawable = drawable;
    this.data = data;
    handle = hDC;
  }
}

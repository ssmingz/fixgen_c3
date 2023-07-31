class PlaceHold {
  public Font getFont() {
    checkWidget();
    if (font != null) {
      return font;
    }
    long hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
    if (hFont == 0) {
      hFont = defaultFont();
    }
    return Font.win32_new(display, hFont);
  }
}

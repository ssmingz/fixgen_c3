class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    int hFont = 0;
    if (font != null) {
      hFont = font.handle;
    }
    if (hFont == 0) {
      hFont = defaultFont();
    }
    OS.SendMessage(handle, WM_SETFONT, hFont, 1);
  }
}

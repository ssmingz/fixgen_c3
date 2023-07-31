class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    this.font = font;
    if (isVisible && parent.hasFocus()) {
      int hFont = 0;
      if (font != null) {
        hFont = font.handle;
      }
      if (hFont == 0) {
        hFont = defaultFont();
      }
      saveIMEFont();
      setIMEFont(hFont);
    }
  }
}

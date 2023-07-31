class PlaceHold {
  public Font getFont() {
    checkWidget();
    if (font != null) {
      return font;
    }
    return Font.gtk_new(getDisplay(), defaultFont());
  }
}

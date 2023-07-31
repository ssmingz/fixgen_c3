class PlaceHold {
  GdkColor getTextColor() {
    int fontHandle = fontHandle();
    GdkColor color = new GdkColor();
    OS.gtk_style_get_text(OS.gtk_widget_get_style(fontHandle), 0, color);
    return color;
  }
}

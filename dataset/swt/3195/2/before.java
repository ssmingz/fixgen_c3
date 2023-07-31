class PlaceHold {
  int getFontDescription() {
    int fontHandle = fontHandle();
    int hStyle = OS.gtk_widget_get_style(fontHandle);
    GtkStyle style = new GtkStyle(hStyle);
    return style.font_desc;
  }
}

class PlaceHold {
  int getFontDescription() {
    int fontHandle = fontHandle();
    GtkStyle style = new GtkStyle();
    OS.memmove(style, OS.gtk_widget_get_style(fontHandle));
    return style.font_desc;
  }
}

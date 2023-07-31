class PlaceHold {
  GdkColor getBackgroundColor() {
    int fontHandle = fontHandle();
    GtkStyle style = new GtkStyle();
    OS.memmove(style, OS.gtk_widget_get_style(fontHandle));
    GdkColor color = new GdkColor();
    color.pixel = style.bg0_pixel;
    color.red = style.bg0_red;
    color.green = style.bg0_green;
    color.blue = style.bg0_blue;
    return color;
  }
}

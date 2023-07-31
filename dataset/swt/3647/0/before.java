class PlaceHold {
  GdkColor getFgColor() {
    int fontHandle = fontHandle();
    GtkStyle style = new GtkStyle();
    OS.memmove(style, OS.gtk_widget_get_style(fontHandle));
    GdkColor color = new GdkColor();
    color.pixel = style.fg0_pixel;
    color.red = style.fg0_red;
    color.green = style.fg0_green;
    color.blue = style.fg0_blue;
    return color;
  }
}

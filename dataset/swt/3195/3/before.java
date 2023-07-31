class PlaceHold {
  GdkColor getForegroundColor() {
    int fontHandle = fontHandle();
    int hStyle = OS.gtk_widget_get_style(fontHandle);
    GtkStyle style = new GtkStyle(hStyle);
    GdkColor color = new GdkColor();
    color.pixel = style.fg0_pixel;
    color.red = style.fg0_red;
    color.green = style.fg0_green;
    color.blue = style.fg0_blue;
    return color;
  }
}

class PlaceHold {
  GdkColor getBackgroundColor() {
    int fontHandle = fontHandle();
    int hStyle = OS.gtk_widget_get_style(fontHandle);
    GtkStyle style = new GtkStyle(hStyle);
    GdkColor color = new GdkColor();
    color.pixel = style.bg0_pixel;
    color.red = style.bg0_red;
    color.green = style.bg0_green;
    color.blue = style.bg0_blue;
    return color;
  }
}

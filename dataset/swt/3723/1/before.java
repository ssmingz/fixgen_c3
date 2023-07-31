class PlaceHold {
  GdkColor getBaseColor() {
    int fontHandle = fontHandle();
    GtkStyle style = new GtkStyle();
    OS.memmove(style, OS.gtk_widget_get_style(fontHandle));
    GdkColor color = new GdkColor();
    color.pixel = style.base0_pixel;
    color.red = style.base0_red;
    color.green = style.base0_green;
    color.blue = style.base0_blue;
    return color;
  }
}

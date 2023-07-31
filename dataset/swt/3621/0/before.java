class PlaceHold {
  GdkColor getTextColor() {
    int fontHandle = fontHandle();
    GtkStyle style = new GtkStyle();
    OS.memmove(style, OS.gtk_widget_get_style(fontHandle));
    GdkColor color = new GdkColor();
    color.pixel = style.text0_pixel;
    color.red = style.text0_red;
    color.green = style.text0_green;
    color.blue = style.text0_blue;
    return color;
  }
}

class PlaceHold {
  GdkColor getContextBackground() {
    long fontHandle = fontHandle();
    long context = OS.gtk_widget_get_style_context(fontHandle);
    int styleState = OS.gtk_widget_get_state_flags(handle);
    GdkRGBA rgba = new GdkRGBA();
    OS.gtk_style_context_get_background_color(context, styleState, rgba);
    if (rgba.alpha == 0) {
      return display.COLOR_WIDGET_BACKGROUND;
    }
    GdkColor color = new GdkColor();
    color.red = ((short) (rgba.red * 0xffff));
    color.green = ((short) (rgba.green * 0xffff));
    color.blue = ((short) (rgba.blue * 0xffff));
    return color;
  }
}

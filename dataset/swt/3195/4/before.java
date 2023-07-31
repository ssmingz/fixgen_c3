class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (data == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int window = paintWindow();
    int gdkGC = OS.gdk_gc_new(window);
    if (gdkGC == 0) {
      error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      int fontHandle = fontHandle();
      int hStyle = OS.gtk_widget_get_style(fontHandle);
      GtkStyle style = new GtkStyle(hStyle);
      GdkColor foreground = new GdkColor();
      foreground.pixel = style.fg0_pixel;
      foreground.red = style.fg0_red;
      foreground.green = style.fg0_green;
      foreground.blue = style.fg0_blue;
      GdkColor background = new GdkColor();
      background.pixel = style.bg0_pixel;
      background.red = style.bg0_red;
      background.green = style.bg0_green;
      background.blue = style.bg0_blue;
      data.drawable = window;
      data.device = getDisplay();
      data.background = background;
      data.foreground = foreground;
      data.font = style.font_desc;
    }
    return gdkGC;
  }
}

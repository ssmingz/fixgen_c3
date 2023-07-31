class PlaceHold {
  void draw(Theme theme, GC gc, Rectangle bounds) {
    long toolbarHandle = theme.toolbarHandle;
    long gtkStyle = OS.gtk_widget_get_style(toolbarHandle);
    long drawable = gc.getGCData().drawable;
    theme.transferClipping(gc, gtkStyle);
    int x = bounds.x;
    int y = bounds.y;
    int width = bounds.width;
    int height = bounds.height;
    byte[] detail = Converter.wcsToMbcs(null, "toolbar", true);
    gtk_render_box(
        gtkStyle,
        drawable,
        getStateType(WIDGET_WHOLE),
        GTK_SHADOW_NONE,
        null,
        toolbarHandle,
        detail,
        x,
        y,
        width,
        height);
    if (clientArea != null) {
      clientArea.x = bounds.x;
      clientArea.y = bounds.y;
      clientArea.width = bounds.width;
      clientArea.height = bounds.height;
    }
  }
}

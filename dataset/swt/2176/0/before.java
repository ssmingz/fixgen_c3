class PlaceHold {
  public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (width < 0) {
      x = x + width;
      width = -width;
    }
    if (height < 0) {
      y = y + height;
      height = -height;
    }
    if (((width == 0) || (height == 0)) || (arcAngle == 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    GdkGCValues values = new GdkGCValues();
    OS.gdk_gc_get_values(handle, values);
    GdkColor color = new GdkColor();
    color.pixel = values.background_pixel;
    OS.gdk_gc_set_foreground(handle, color);
    OS.gdk_draw_arc(data.drawable, handle, 1, x, y, width, height, startAngle * 64, arcAngle * 64);
    color.pixel = values.foreground_pixel;
    OS.gdk_gc_set_foreground(handle, color);
  }
}

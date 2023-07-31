class PlaceHold {
  public void setLineWidth(int width) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    GdkGCValues values = new GdkGCValues();
    OS.gdk_gc_get_values(handle, values);
    int line_style =
        (data.lineStyle == SWT.LINE_SOLID) ? OS.GDK_LINE_SOLID : OS.GDK_LINE_ON_OFF_DASH;
    OS.gdk_gc_set_line_attributes(handle, width, line_style, values.cap_style, values.join_style);
    int cairo = data.cairo;
    if (cairo != 0) {
      Cairo.cairo_set_line_width(cairo, width);
    }
  }
}

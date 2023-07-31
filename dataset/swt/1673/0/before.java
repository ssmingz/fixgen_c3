class PlaceHold {
  public void setLineWidth(int width) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int line_style =
        (data.lineStyle == SWT.LINE_SOLID) ? OS.GDK_LINE_SOLID : OS.GDK_LINE_ON_OFF_DASH;
    OS.gdk_gc_set_line_attributes(handle, width, line_style, GDK_CAP_BUTT, GDK_JOIN_MITER);
  }
}

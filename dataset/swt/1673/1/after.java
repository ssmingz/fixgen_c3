class PlaceHold {
  public void setLineStyle(int lineStyle) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int line_style = OS.GDK_LINE_ON_OFF_DASH;
    switch (lineStyle) {
      case SWT.LINE_SOLID:
        line_style = OS.GDK_LINE_SOLID;
        break;
      case SWT.LINE_DASH:
        OS.gdk_gc_set_dashes(handle, 0, new byte[] {6, 2}, 2);
        break;
      case SWT.LINE_DOT:
        OS.gdk_gc_set_dashes(handle, 0, new byte[] {3, 1}, 2);
        break;
      case SWT.LINE_DASHDOT:
        OS.gdk_gc_set_dashes(handle, 0, new byte[] {6, 2, 3, 1}, 4);
        break;
      case SWT.LINE_DASHDOTDOT:
        OS.gdk_gc_set_dashes(handle, 0, new byte[] {6, 2, 3, 1, 3, 1}, 6);
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    data.lineStyle = lineStyle;
    GdkGCValues values = new GdkGCValues();
    OS.gdk_gc_get_values(handle, values);
    OS.gdk_gc_set_line_attributes(
        handle, values.line_width, line_style, GDK_CAP_ROUND, GDK_JOIN_MITER);
  }
}

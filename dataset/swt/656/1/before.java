class PlaceHold {
  public int getLineJoin() {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    GdkGCValues values = new GdkGCValues();
    OS.gdk_gc_get_values(handle, values);
    int join = SWT.JOIN_MITER;
    switch (values.join_style) {
      case OS.GDK_JOIN_MITER:
        join = SWT.JOIN_MITER;
        break;
      case OS.GDK_JOIN_ROUND:
        join = SWT.JOIN_ROUND;
        break;
      case OS.GDK_JOIN_BEVEL:
        join = SWT.JOIN_BEVEL;
        break;
    }
    return join;
  }
}

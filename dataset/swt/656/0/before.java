class PlaceHold {
  public int getLineCap() {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    GdkGCValues values = new GdkGCValues();
    OS.gdk_gc_get_values(handle, values);
    int cap = SWT.CAP_FLAT;
    switch (values.cap_style) {
      case OS.GDK_CAP_ROUND:
        cap = SWT.CAP_ROUND;
        break;
      case OS.GDK_CAP_BUTT:
        cap = SWT.CAP_FLAT;
        break;
      case OS.GDK_CAP_PROJECTING:
        cap = SWT.CAP_SQUARE;
        break;
    }
    return cap;
  }
}

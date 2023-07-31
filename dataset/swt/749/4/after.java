class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (isDisposed()) {
      SWT.error(ERROR_DEVICE_DISPOSED);
    }
    int root = OS.GDK_ROOT_PARENT();
    int gdkGC = OS.gdk_gc_new(root);
    if (gdkGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
      if ((data.style & mask) == 0) {
        data.style |= SWT.LEFT_TO_RIGHT;
      }
      data.device = this;
      data.drawable = root;
      data.font = defaultFont;
    }
    return gdkGC;
  }
}

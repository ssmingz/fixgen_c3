class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (pixmap == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((type != SWT.BITMAP) || (memGC != null)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int gdkGC = OS.gdk_gc_new(pixmap);
    if (data != null) {
      data.device = device;
      data.drawable = pixmap;
      data.font = device.systemFont.handle;
      data.image = this;
    }
    return gdkGC;
  }
}

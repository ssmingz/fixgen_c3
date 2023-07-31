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
      int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
      if ((data.style & mask) == 0) {
        data.style |= SWT.LEFT_TO_RIGHT;
      } else if ((data.style & SWT.RIGHT_TO_LEFT) != 0) {
        data.style |= SWT.MIRRORED;
      }
      data.device = device;
      data.drawable = pixmap;
      data.background = COLOR_WHITE.handle;
      data.foreground = COLOR_BLACK.handle;
      data.font = systemFont.handle;
      data.image = this;
    }
    return gdkGC;
  }
}

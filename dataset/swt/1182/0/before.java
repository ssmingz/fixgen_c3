class PlaceHold {
  public void dispose() {
    if (pixmap == 0) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    if (pixmap != 0) {
      OS.g_object_unref(pixmap);
    }
    if (mask != 0) {
      OS.g_object_unref(mask);
    }
    device = null;
    pixmap = mask = 0;
    memGC = null;
  }
}

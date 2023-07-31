class PlaceHold {
  public void dispose() {
    if (pixmap == 0) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    if (memGC != null) {
      memGC.dispose();
    }
    if (pixmap != 0) {
      OS.g_object_unref(pixmap);
    }
    if (mask != 0) {
      OS.g_object_unref(mask);
    }
    pixmap = mask = 0;
    memGC = null;
    if (device.tracking) {
      device.dispose_Object(this);
    }
    device = null;
  }
}

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
    int xDisplay = device.xDisplay;
    if (pixmap != 0) {
      OS.XFreePixmap(xDisplay, pixmap);
    }
    if (mask != 0) {
      OS.XFreePixmap(xDisplay, mask);
    }
    if (surface != 0) {
      Cairo.cairo_surface_destroy(surface);
    }
    surface = pixmap = mask = 0;
    memGC = null;
    if (device.tracking) {
      device.dispose_Object(this);
    }
    device = null;
  }
}

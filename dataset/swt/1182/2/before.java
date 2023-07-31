class PlaceHold {
  public void dispose() {
    if (pixmap == 0) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    int xDisplay = device.xDisplay;
    if (pixmap != 0) {
      OS.XFreePixmap(xDisplay, pixmap);
    }
    if (mask != 0) {
      OS.XFreePixmap(xDisplay, mask);
    }
    device = null;
    memGC = null;
    pixmap = mask = 0;
  }
}

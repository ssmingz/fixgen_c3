class PlaceHold {
  public void dispose() {
    if (pixmap == 0) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    if (pixmap != 0) {
      OS.DisposePixMap(pixmap);
    }
    if (mask != 0) {
      DisposeBitMap(mask);
    }
    device = null;
    memGC = null;
    pixmap = mask = 0;
  }
}

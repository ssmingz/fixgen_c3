class PlaceHold {
  public void dispose() {
    if (handle == 0) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    if (memGC != null) {
      memGC.dispose();
    }
    OS.CGImageRelease(handle);
    OS.DisposePtr(data);
    device = null;
    data = handle = 0;
    memGC = null;
  }
}

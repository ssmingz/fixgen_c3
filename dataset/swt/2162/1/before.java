class PlaceHold {
  public void dispose() {
    if (handle != 0) {
      OS.XDestroyRegion(handle);
    }
    handle = 0;
  }
}

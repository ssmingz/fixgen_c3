class PlaceHold {
  public void dispose() {
    if (handle != 0) {
      OS.DeleteObject(handle);
    }
    handle = 0;
  }
}

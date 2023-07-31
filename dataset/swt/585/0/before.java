class PlaceHold {
  public boolean isVisible() {
    checkWidget();
    return OS.IsWindowVisible(handle);
  }
}

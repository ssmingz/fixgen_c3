class PlaceHold {
  public boolean getEnabled() {
    checkWidget();
    return OS.IsControlEnabled(handle);
  }
}

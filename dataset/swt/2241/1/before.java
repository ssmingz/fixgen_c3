class PlaceHold {
  public boolean getEnabled() {
    checkWidget();
    return OS.UIElement_IsEnabled(handle);
  }
}

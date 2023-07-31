class PlaceHold {
  public boolean isVisible() {
    checkWidget();
    if (OS.IsWindowVisible(handle)) {
      return true;
    }
    return getVisible() && parent.isVisible();
  }
}

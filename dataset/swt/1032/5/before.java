class PlaceHold {
  public boolean getMinimized() {
    checkWidget();
    if (OS.IsWindowVisible(handle)) {
      return OS.IsIconic(handle);
    }
    return swFlags == OS.SW_SHOWMINNOACTIVE;
  }
}

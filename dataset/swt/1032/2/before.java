class PlaceHold {
  public boolean getMaximized() {
    checkWidget();
    if (OS.IsWindowVisible(handle)) {
      return OS.IsZoomed(handle);
    }
    return swFlags == OS.SW_SHOWMAXIMIZED;
  }
}

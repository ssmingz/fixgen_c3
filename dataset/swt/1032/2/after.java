class PlaceHold {
  public boolean getMaximized() {
    checkWidget();
    if (OS.IsWinCE) {
      return false;
    }
    if (OS.IsWindowVisible(handle)) {
      return OS.IsZoomed(handle);
    }
    return swFlags == OS.SW_SHOWMAXIMIZED;
  }
}

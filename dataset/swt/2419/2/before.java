class PlaceHold {
  public Tray getSystemTray() {
    checkDevice();
    if (tray != null) {
      return tray;
    }
    return tray = new Tray(this, SWT.NONE);
  }
}

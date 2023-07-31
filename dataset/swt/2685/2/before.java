class PlaceHold {
  public void update() {
    checkDevice();
    Shell[] shells = WidgetTable.shells();
    for (int i = 0; i < shells.length; i++) {
      Shell shell = shells[i];
      if ((!shell.isDisposed()) && (this == shell.getDisplay())) {
        shell.update();
      }
    }
  }
}

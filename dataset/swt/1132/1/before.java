class PlaceHold {
  void releaseWidget() {
    releaseShells();
    destroyAccelGroup();
    super.releaseWidget();
    if (display.activeShell == this) {
      display.activeShell = null;
    }
    if (tooltipsHandle != 0) {
      OS.g_object_unref(tooltipsHandle);
    }
    tooltipsHandle = 0;
    region = null;
    lastActive = null;
  }
}

class PlaceHold {
  void fixActiveShell() {
    long hwndParent = OS.GetParent(handle);
    if ((hwndParent != 0) && (handle == OS.GetActiveWindow())) {
      if ((!OS.IsWindowEnabled(hwndParent)) && OS.IsWindowVisible(hwndParent)) {
        OS.SetActiveWindow(hwndParent);
      }
    }
  }
}

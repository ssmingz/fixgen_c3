class PlaceHold {
  void addWidget() {
    if (parent != null) {
      OS.Window_Owner(shellHandle, ((Shell) (parent)).shellHandle);
    }
  }
}

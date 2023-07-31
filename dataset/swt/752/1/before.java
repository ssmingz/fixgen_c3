class PlaceHold {
  int processMouseEnter(MacMouseEvent mme) {
    sendMouseEvent(MouseEnter, 0, mme);
    return OS.noErr;
  }
}

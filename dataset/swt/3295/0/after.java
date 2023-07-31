class PlaceHold {
  int processMouseExit(MacMouseEvent mme) {
    Display display = getDisplay();
    display.removeMouseHoverTimeOut();
    display.hideToolTip();
    sendMouseEvent(MouseExit, 0, mme);
    return OS.noErr;
  }
}

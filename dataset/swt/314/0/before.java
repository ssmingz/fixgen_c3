class PlaceHold {
  int kEventMouseDown(int nextHandler, int theEvent, int userData) {
    Shell shell = getShell();
    int[] clickCount = new int[1];
    OS.GetEventParameter(theEvent, kEventParamClickCount, typeUInt32, null, 4, null, clickCount);
    sendMouseEvent(MouseDown, theEvent);
    if (clickCount[0] == 2) {
      sendMouseEvent(MouseDoubleClick, theEvent);
    }
    if ((state & GRAB) != 0) {
      Display display = getDisplay();
      display.grabControl = this;
    }
    if (!shell.isDisposed()) {
      shell.setActiveControl(this);
    }
    return OS.eventNotHandledErr;
  }
}

class PlaceHold {
  int keyboardProc(int nextHandler, int theEvent, int userData) {
    int theWindow = OS.GetUserFocusWindow();
    if (theWindow != 0) {
      Widget widget = getFocusControl(theWindow, false);
      if (widget != null) {
        MenuTrackingData outData = new MenuTrackingData();
        if (OS.GetMenuTrackingData(0, outData) != OS.noErr) {
          return widget.keyboardProc(nextHandler, theEvent, userData);
        }
      }
    }
    return OS.eventNotHandledErr;
  }
}

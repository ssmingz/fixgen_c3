class PlaceHold {
  int keyboardProc(int nextHandler, int theEvent, int userData) {
    int theWindow = OS.GetUserFocusWindow();
    if (theWindow != 0) {
      int[] theControl = new int[1];
      OS.GetKeyboardFocus(theWindow, theControl);
      Widget widget = getWidget(theControl[0]);
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

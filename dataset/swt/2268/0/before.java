class PlaceHold {
  int textInputProc(int nextHandler, int theEvent, int userData) {
    int theWindow = OS.GetUserFocusWindow();
    if (theWindow != 0) {
      int[] theControl = new int[1];
      OS.GetKeyboardFocus(theWindow, theControl);
      Widget widget = getWidget(theControl[0]);
      if (widget != null) {
        MenuTrackingData outData = new MenuTrackingData();
        if (OS.GetMenuTrackingData(0, outData) != OS.noErr) {
          OS.GetWindowDefaultButton(theWindow, theControl);
          OS.SetWindowDefaultButton(theWindow, 0);
          int result = widget.textInputProc(nextHandler, theEvent, userData);
          if (result == OS.eventNotHandledErr) {
            result = OS.CallNextEventHandler(nextHandler, theEvent);
          }
          OS.SetWindowDefaultButton(theWindow, theControl[0]);
          return result;
        }
      }
    }
    return OS.eventNotHandledErr;
  }
}

class PlaceHold {
  int getEnabledAttribute(int nextHandler, int theEvent, int userData) {
    int code = OS.CallNextEventHandler(nextHandler, theEvent);
    if (code == OS.eventNotHandledErr) {
      OS.SetEventParameter(
          theEvent,
          kEventParamAccessibleAttributeValue,
          typeBoolean,
          4,
          new boolean[] {control.isEnabled()});
      return OS.noErr;
    }
    return code;
  }
}

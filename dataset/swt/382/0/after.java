class PlaceHold {
  int getEnabledAttribute(int nextHandler, int theEvent, int userData) {
    int code =
        (userData != OS.eventNotHandledErr)
            ? userData
            : OS.CallNextEventHandler(nextHandler, theEvent);
    if (code == OS.eventNotHandledErr) {
      OS.SetEventParameter(
          theEvent,
          kEventParamAccessibleAttributeValue,
          typeBoolean,
          4,
          new boolean[] {control.isEnabled()});
      code = OS.noErr;
    }
    return code;
  }
}

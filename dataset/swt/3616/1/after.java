class PlaceHold {
  int getParentAttribute(int nextHandler, int theEvent, int userData) {
    int code =
        (userData != OS.eventNotHandledErr)
            ? userData
            : OS.CallNextEventHandler(nextHandler, theEvent);
    if (code == OS.eventNotHandledErr) {
      OS.SetEventParameter(
          theEvent,
          kEventParamAccessibleAttributeValue,
          typeCFTypeRef,
          4,
          new int[] {axuielementref});
      code = OS.noErr;
    }
    return code;
  }
}

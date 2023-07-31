class PlaceHold {
  int getParentAttribute(int nextHandler, int theEvent, int userData) {
    int code = OS.CallNextEventHandler(nextHandler, theEvent);
    if (code == OS.eventNotHandledErr) {
      OS.SetEventParameter(
          theEvent,
          kEventParamAccessibleAttributeValue,
          typeCFTypeRef,
          4,
          new int[] {axuielementref});
      return OS.noErr;
    }
    return code;
  }
}

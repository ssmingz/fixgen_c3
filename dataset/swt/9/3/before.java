class PlaceHold {
  int kEventControlSetFocusPart(int nextHandler, int theEvent, int userData) {
    if (!display.ignoreFocus) {
      short[] part = new short[1];
      OS.GetEventParameter(
          theEvent, kEventParamControlPart, typeControlPartCode, null, 2, null, part);
      sendFocusEvent(part[0] != 0, false);
    }
    return OS.eventNotHandledErr;
  }
}

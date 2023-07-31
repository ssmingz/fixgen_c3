class PlaceHold {
  int kEventControlSetFocusPart(int nextHandler, int theEvent, int userData) {
    int result = super.kEventControlSetFocusPart(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    short[] part = new short[1];
    OS.GetEventParameter(
        theEvent, kEventParamControlPart, typeControlPartCode, null, 2, null, part);
    drawFocusClipped(handle, part[0] != 0, hasBorder(), getParentBackground(), inset());
    OS.TXNDraw(txnObject, 0);
    OS.TXNFocus(txnObject, part[0] != 0);
    return OS.noErr;
  }
}

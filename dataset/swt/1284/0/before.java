class PlaceHold {
  int kEventControlSetFocusPart(int nextHandler, int theEvent, int userData) {
    int result = super.kEventControlSetFocusPart(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    if ((state & CANVAS) != 0) {
      if (scrolledHandle != 0) {
        if (((style & SWT.NO_FOCUS) == 0) && hooksKeys()) {
          short[] part = new short[1];
          OS.GetEventParameter(
              theEvent, kEventParamControlPart, typeControlPartCode, null, 2, null, part);
          drawFocusClipped(
              scrolledHandle, part[0] != 0, hasBorder(), getParentBackground(), inset());
        }
      }
      return OS.noErr;
    }
    return result;
  }
}

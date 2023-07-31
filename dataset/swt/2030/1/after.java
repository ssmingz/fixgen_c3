class PlaceHold {
  int kEventWindowBoundsChanged(int nextHandler, int theEvent, int userData) {
    int result = super.kEventWindowBoundsChanged(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    int[] attributes = new int[1];
    OS.GetEventParameter(
        theEvent, kEventParamAttributes, typeUInt32, null, attributes.length * 4, null, attributes);
    if ((attributes[0] & OS.kWindowBoundsChangeOriginChanged) != 0) {
      sendEvent(Move);
    }
    if ((attributes[0] & OS.kWindowBoundsChangeSizeChanged) != 0) {
      resized = true;
      layoutControl(false);
      sendEvent(Resize);
      if (layout != null) {
        layout.layout(this, false);
      }
    }
    return result;
  }
}

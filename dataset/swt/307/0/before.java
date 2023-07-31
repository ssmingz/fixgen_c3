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
      moved = true;
      sendEvent(Move);
    }
    if ((attributes[0] & OS.kWindowBoundsChangeSizeChanged) != 0) {
      resized = true;
      layoutControl(false);
      sendEvent(Resize);
      if (layout != null) {
        layout.layout(this, false);
      }
      if ((region != null) && (!region.isDisposed())) {
        OS.GetEventParameter(
            theEvent, kEventParamCurrentBounds, typeQDRectangle, null, sizeof, null, rgnRect);
        OS.SetRect(
            rgnRect,
            ((short) (0)),
            ((short) (0)),
            ((short) (rgnRect.right - rgnRect.left)),
            ((short) (rgnRect.bottom - rgnRect.top)));
        OS.ReshapeCustomWindow(shellHandle);
      }
    }
    return result;
  }
}

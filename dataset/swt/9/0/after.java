class PlaceHold {
  int kEventControlTrack(int nextHandler, int theEvent, int userData) {
    int result = parent.kEventControlTrack(nextHandler, theEvent, userData);
    if (OS.HIVIEW) {
      if (isDisposed()) {
        return OS.noErr;
      }
      partCode = 0;
      if ((text.length() > 0) && (labelHandle != 0)) {
        redrawWidget(labelHandle, false);
      }
      if ((image != null) && (iconHandle != 0)) {
        OS.SetControlData(
            iconHandle,
            kControlEntireControl,
            kControlIconTransformTag,
            2,
            new short[] {((short) (0))});
        redrawWidget(iconHandle, false);
      }
    }
    return result;
  }
}

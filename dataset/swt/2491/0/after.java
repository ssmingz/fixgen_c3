class PlaceHold {
  void createHandle() {
    state |= GRAB | THEME_BACKGROUND;
    int[] outControl = new int[1];
    int window = OS.GetControlOwner(handle);
    if ((style & SWT.SEPARATOR) != 0) {
      OS.CreateSeparatorControl(window, null, outControl);
    } else {
      int just = OS.teFlushLeft;
      if ((style & SWT.CENTER) != 0) {
        just = OS.teCenter;
      }
      if ((style & SWT.RIGHT) != 0) {
        just = OS.teFlushRight;
      }
      ControlFontStyleRec fontStyle = new ControlFontStyleRec();
      fontStyle.flags |= OS.kControlUseJustMask;
      fontStyle.just = ((short) (just));
      OS.CreateStaticTextControl(window, null, 0, fontStyle, outControl);
    }
    if (outControl[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    handle = outControl[0];
    if ((style & SWT.WRAP) == 0) {
      OS.SetControlData(
          handle, kControlEntireControl, kControlStaticTextIsMultilineTag, 1, new byte[] {0});
    }
  }
}

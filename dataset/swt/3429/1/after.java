class PlaceHold {
  int gtk_key_press_event(int widget, int event) {
    if (!hasFocus()) {
      return 0;
    }
    int imHandle = imHandle();
    if (imHandle != 0) {
      if (OS.gtk_im_context_filter_keypress(imHandle, event)) {
        return 1;
      }
      if (isDisposed()) {
        return 0;
      }
    }
    GdkEventKey gdkEvent = new GdkEventKey();
    OS.memmove(gdkEvent, event, sizeof);
    if (translateMnemonic(gdkEvent.keyval, gdkEvent)) {
      return 1;
    }
    if (translateTraversal(gdkEvent)) {
      return 1;
    }
    if (isDisposed()) {
      return 0;
    }
    return sendKeyEvent(KeyDown, gdkEvent) ? 0 : 1;
  }
}

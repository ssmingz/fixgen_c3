class PlaceHold {
  int gtk_key_release_event(int widget, int event) {
    if (!hasFocus()) {
      return 0;
    }
    int imHandle = imHandle();
    if (imHandle != 0) {
      if (OS.gtk_im_context_filter_keypress(imHandle, event)) {
        return 0;
      }
    }
    GdkEventKey gdkEvent = new GdkEventKey();
    OS.memmove(gdkEvent, event, sizeof);
    sendKeyEvent(KeyUp, gdkEvent);
    return 0;
  }
}

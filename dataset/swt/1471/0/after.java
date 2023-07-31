class PlaceHold {
  int XPointerMotion(int w, int client_data, int call_data, int continue_to_dispatch) {
    if (cursor != null) {
      int xDisplay = display.xDisplay;
      OS.XChangeActivePointerGrab(
          xDisplay,
          (OS.ButtonPressMask | OS.ButtonReleaseMask) | OS.PointerMotionMask,
          cursor.handle,
          CurrentTime);
    }
    return xMouse(MotionNotify, w, client_data, call_data, continue_to_dispatch);
  }
}

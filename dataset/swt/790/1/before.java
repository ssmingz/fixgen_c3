class PlaceHold {
  void runGrabs() {
    if ((grabControl == null) || grabbing) {
      return;
    }
    Rect rect = new Rect();
    int[] outModifiers = new int[1];
    short[] outResult = new short[1];
    Point outPt = new Point();
    grabbing = true;
    try {
      while (((grabControl != null) && (!grabControl.isDisposed()))
          && (outResult[0] != OS.kMouseTrackingMouseUp)) {
        lastModifiers = OS.GetCurrentEventKeyModifiers();
        int oldState = OS.GetCurrentEventButtonState();
        OS.TrackMouseLocationWithOptions(
            0, 0, kEventDurationForever, outPt, outModifiers, outResult);
        int type = 0;
        int button = 0;
        switch (((int) (outResult[0]))) {
          case OS.kMouseTrackingMouseDown:
            {
              type = SWT.MouseDown;
              int newState = OS.GetCurrentEventButtonState();
              if (((oldState & 0x1) == 0) && ((newState & 0x1) != 0)) {
                button = 1;
              }
              if (((oldState & 0x2) == 0) && ((newState & 0x2) != 0)) {
                button = 2;
              }
              if (((oldState & 0x4) == 0) && ((newState & 0x4) != 0)) {
                button = 3;
              }
              break;
            }
          case OS.kMouseTrackingMouseUp:
            {
              type = SWT.MouseUp;
              int newState = OS.GetCurrentEventButtonState();
              if (((oldState & 0x1) != 0) && ((newState & 0x1) == 0)) {
                button = 1;
              }
              if (((oldState & 0x2) != 0) && ((newState & 0x2) == 0)) {
                button = 2;
              }
              if (((oldState & 0x4) != 0) && ((newState & 0x4) == 0)) {
                button = 3;
              }
              break;
            }
          case OS.kMouseTrackingMouseDragged:
            type = SWT.MouseMove;
            break;
          case OS.kMouseTrackingMouseKeyModifiersChanged:
            break;
          case OS.kMouseTrackingUserCancelled:
            break;
          case OS.kMouseTrackingTimedOut:
            break;
          case OS.kMouseTrackingMouseMoved:
            type = SWT.MouseMove;
            break;
        }
        if (type != 0) {
          int handle = grabControl.handle;
          int window = OS.GetControlOwner(handle);
          OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
          int x = outPt.h - rect.left;
          int y = outPt.v - rect.top;
          OS.GetControlBounds(handle, rect);
          x -= rect.left;
          y -= rect.top;
          int chord = OS.GetCurrentEventButtonState();
          grabControl.sendMouseEvent(
              type, ((short) (button)), chord, ((short) (x)), ((short) (y)), outModifiers[0]);
          if ((grabControl != null) && (!grabControl.isDisposed())) {
            grabControl.update();
          }
        }
      }
    } finally {
      grabbing = false;
      grabControl = null;
    }
  }
}

class PlaceHold {
  boolean runGrabs() {
    if ((grabControl == null) || grabbing) {
      return false;
    }
    Rect rect = new Rect();
    int[] outModifiers = new int[1];
    short[] outResult = new short[1];
    Point outPt = new Point();
    grabbing = true;
    mouseUpControl = null;
    try {
      while (((grabControl != null) && (!grabControl.isDisposed()))
          && (outResult[0] != OS.kMouseTrackingMouseUp)) {
        lastModifiers = OS.GetCurrentEventKeyModifiers();
        int oldState = OS.GetCurrentEventButtonState();
        int handle = grabControl.handle;
        int window = OS.GetControlOwner(handle);
        int port = OS.GetWindowPort(window);
        OS.TrackMouseLocationWithOptions(
            port,
            kTrackMouseLocationOptionDontConsumeMouseUp,
            kEventDurationForever,
            outPt,
            outModifiers,
            outResult);
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
            {
              type = SWT.MouseMove;
              dragDetect(grabControl);
              break;
            }
          case OS.kMouseTrackingMouseKeyModifiersChanged:
            break;
          case OS.kMouseTrackingUserCancelled:
            break;
          case OS.kMouseTrackingTimedOut:
            break;
          case OS.kMouseTrackingMouseMoved:
            {
              type = SWT.MouseMove;
              break;
            }
        }
        runEnterExit();
        if (type != 0) {
          OS.GetControlBounds(handle, rect);
          int x = outPt.h - rect.left;
          int y = outPt.v - rect.top;
          int chord = OS.GetCurrentEventButtonState();
          if ((grabControl != null) && (!grabControl.isDisposed())) {
            if (type == SWT.MouseUp) {
              mouseUpControl = grabControl;
            } else {
              grabControl.sendMouseEvent(
                  type,
                  ((short) (button)),
                  chord,
                  ((short) (x)),
                  ((short) (y)),
                  outModifiers[0],
                  true);
            }
          }
          if ((grabControl != null) && (!grabControl.isDisposed())) {
            grabControl.update(true);
          }
        }
      }
    } finally {
      grabbing = false;
      grabControl = null;
    }
    return true;
  }
}

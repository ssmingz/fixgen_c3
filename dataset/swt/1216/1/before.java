class PlaceHold {
  boolean runEnterExit() {
    boolean eventSent = false;
    Control control = null;
    int[] theControl = new int[1];
    Point where = new Point();
    OS.GetGlobalMouse(where);
    int[] theWindow = new int[1];
    if (OS.FindWindow(where, theWindow) == OS.inContent) {
      if (theWindow[0] != 0) {
        Rect rect = new Rect();
        OS.GetWindowBounds(theWindow[0], ((short) (kWindowContentRgn)), rect);
        CGPoint inPoint = new CGPoint();
        inPoint.x = where.h - rect.left;
        inPoint.y = where.v - rect.top;
        int[] theRoot = new int[1];
        OS.GetRootControl(theWindow[0], theRoot);
        OS.HIViewGetSubviewHit(theRoot[0], inPoint, true, theControl);
        while ((theControl[0] != 0) && (!OS.IsControlEnabled(theControl[0]))) {
          OS.GetSuperControl(theControl[0], theControl);
        }
        boolean propagate = true;
        if (theControl[0] != 0) {
          do {
            Widget widget = getWidget(theControl[0]);
            if (widget != null) {
              if (widget instanceof Control) {
                Control cursorControl = ((Control) (widget));
                if (cursorControl.isEnabled()) {
                  if (cursorControl.isEnabledModal()) {
                    if (widget.isTrimHandle(theControl[0])) {
                      propagate = false;
                      break;
                    }
                    control = cursorControl;
                  }
                  break;
                }
              }
            }
            OS.GetSuperControl(theControl[0], theControl);
          } while (theControl[0] != 0);
        }
        if ((control == null) && propagate) {
          theControl[0] = theRoot[0];
          Widget widget = getWidget(theControl[0]);
          if ((widget != null) && (widget instanceof Control)) {
            Control cursorControl = ((Control) (widget));
            if (cursorControl.isEnabled()) {
              if (cursorControl.isEnabledModal()) {
                control = cursorControl;
                theControl[0] = control.handle;
              }
            }
          }
        }
        if ((control != null) && (!control.contains(((int) (inPoint.x)), ((int) (inPoint.y))))) {
          control = null;
        }
      }
    }
    if (control != currentControl) {
      if ((currentControl != null) && (!currentControl.isDisposed())) {
        eventSent = true;
        int chord = OS.GetCurrentEventButtonState();
        int modifiers = OS.GetCurrentEventKeyModifiers();
        Point pt = currentControl.toControl(where.h, where.v);
        currentControl.sendMouseEvent(
            MouseExit,
            ((short) (0)),
            0,
            true,
            chord,
            ((short) (pt.x)),
            ((short) (pt.y)),
            modifiers);
        if (mouseHoverID != 0) {
          OS.RemoveEventLoopTimer(mouseHoverID);
        }
        mouseHoverID = 0;
        mouseMoved = false;
      }
      if ((control != null) && control.isDisposed()) {
        control = null;
      }
      if ((currentControl = control) != null) {
        eventSent = true;
        int chord = OS.GetCurrentEventButtonState();
        int modifiers = OS.GetCurrentEventKeyModifiers();
        Point pt = currentControl.toControl(where.h, where.v);
        currentControl.sendMouseEvent(
            MouseEnter,
            ((short) (0)),
            0,
            true,
            chord,
            ((short) (pt.x)),
            ((short) (pt.y)),
            modifiers);
      }
    }
    if ((control != null) && mouseMoved) {
      int[] outDelay = new int[1];
      OS.HMGetTagDelay(outDelay);
      if (mouseHoverID != 0) {
        OS.SetEventLoopTimerNextFireTime(mouseHoverID, outDelay[0] / 1000.0);
      } else {
        int eventLoop = OS.GetCurrentEventLoop();
        int[] id = new int[1];
        OS.InstallEventLoopTimer(eventLoop, outDelay[0] / 1000.0, 0.0, mouseHoverProc, 0, id);
        mouseHoverID = id[0];
      }
      mouseMoved = false;
    }
    if (((!OS.StillDown()) && (theWindow[0] != 0)) && (theControl[0] != 0)) {
      Rect rect = new Rect();
      OS.GetWindowBounds(theWindow[0], ((short) (kWindowContentRgn)), rect);
      where.h -= rect.left;
      where.v -= rect.top;
      int modifiers = OS.GetCurrentEventKeyModifiers();
      boolean[] cursorWasSet = new boolean[1];
      OS.HandleControlSetCursor(theControl[0], where, ((short) (modifiers)), cursorWasSet);
      if (!cursorWasSet[0]) {
        OS.SetThemeCursor(kThemeArrowCursor);
      }
    }
    return eventSent;
  }
}

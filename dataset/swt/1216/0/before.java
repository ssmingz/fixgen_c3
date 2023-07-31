class PlaceHold {
  public void setCursor(Cursor cursor) {
    checkWidget();
    if ((cursor != null) && cursor.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    this.cursor = cursor;
    if (!isEnabled()) {
      return;
    }
    Point where = new Point();
    OS.GetGlobalMouse(where);
    int[] theWindow = new int[1];
    if (display.grabControl == this) {
      theWindow[0] = OS.GetControlOwner(handle);
    } else {
      if (OS.FindWindow(where, theWindow) != OS.inContent) {
        return;
      }
      if (theWindow[0] == 0) {
        return;
      }
    }
    Rect rect = new Rect();
    OS.GetWindowBounds(theWindow[0], ((short) (kWindowContentRgn)), rect);
    int[] theControl = new int[1];
    if (display.grabControl == this) {
      theControl[0] = handle;
    } else {
      CGPoint inPoint = new CGPoint();
      inPoint.x = where.h - rect.left;
      inPoint.y = where.v - rect.top;
      int[] theRoot = new int[1];
      OS.GetRootControl(theWindow[0], theRoot);
      OS.HIViewGetSubviewHit(theRoot[0], inPoint, true, theControl);
      int cursorControl = theControl[0];
      while ((theControl[0] != 0) && (theControl[0] != handle)) {
        OS.GetSuperControl(theControl[0], theControl);
      }
      if (theControl[0] == 0) {
        return;
      }
      theControl[0] = cursorControl;
      do {
        Widget widget = display.getWidget(theControl[0]);
        if (widget != null) {
          if (widget instanceof Control) {
            Control control = ((Control) (widget));
            if (control.isEnabled()) {
              break;
            }
          }
        }
        OS.GetSuperControl(theControl[0], theControl);
      } while (theControl[0] != 0);
      if (theControl[0] == 0) {
        theControl[0] = theRoot[0];
        Widget widget = display.getWidget(theControl[0]);
        if ((widget != null) && (widget instanceof Control)) {
          Control control = ((Control) (widget));
          theControl[0] = control.handle;
        }
      }
    }
    where.h -= rect.left;
    where.v -= rect.top;
    int modifiers = OS.GetCurrentEventKeyModifiers();
    boolean[] cursorWasSet = new boolean[1];
    OS.HandleControlSetCursor(theControl[0], where, ((short) (modifiers)), cursorWasSet);
    if (!cursorWasSet[0]) {
      OS.SetThemeCursor(kThemeArrowCursor);
    }
  }
}

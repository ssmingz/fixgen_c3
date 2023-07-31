class PlaceHold {
  public void setCursor(Cursor cursor) {
    checkWidget();
    if ((cursor != null) && cursor.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    this.cursor = cursor;
    if (!OS.IsControlEnabled(topHandle())) {
      return;
    }
    Point where = new Point();
    OS.GetGlobalMouse(where);
    int[] theWindow = new int[1];
    if (OS.FindWindow(where, theWindow) != OS.inContent) {
      return;
    }
    if (theWindow[0] == 0) {
      return;
    }
    Rect rect = new Rect();
    OS.GetWindowBounds(theWindow[0], ((short) (kWindowContentRgn)), rect);
    CGPoint inPoint = new CGPoint();
    inPoint.x = where.h - rect.left;
    inPoint.y = where.v - rect.top;
    int[] theRoot = new int[1];
    OS.GetRootControl(theWindow[0], theRoot);
    int[] theControl = new int[1];
    OS.HIViewGetSubviewHit(theRoot[0], inPoint, true, theControl);
    int cursorControl = theControl[0];
    while ((theControl[0] != 0) && (theControl[0] != handle)) {
      OS.GetSuperControl(theControl[0], theControl);
    }
    if (theControl[0] == 0) {
      return;
    }
    Point localPoint = new Point();
    localPoint.h = ((short) (inPoint.x));
    localPoint.v = ((short) (inPoint.y));
    int modifiers = OS.GetCurrentEventKeyModifiers();
    boolean[] cursorWasSet = new boolean[1];
    OS.HandleControlSetCursor(cursorControl, localPoint, ((short) (modifiers)), cursorWasSet);
    if (!cursorWasSet[0]) {
      OS.SetThemeCursor(kThemeArrowCursor);
    }
  }
}

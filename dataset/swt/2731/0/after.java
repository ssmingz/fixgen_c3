class PlaceHold {
  int mouseProc(int nextHandler, int theEvent, int userData) {
    int eventKind = OS.GetEventKind(theEvent);
    int sizeof = Point;
    Point where = new Point();
    OS.GetEventParameter(
        theEvent, kEventParamMouseLocation, typeQDPoint, null, sizeof, null, where);
    int[] theWindow = new int[1];
    int part = OS.FindWindow(where, theWindow);
    switch (part) {
      case OS.inMenuBar:
        {
          if (eventKind == OS.kEventMouseDown) {
            clearMenuFlags();
            if ((menuBar == null) || menuBar.isEnabled()) {
              OS.MenuSelect(where);
            }
            clearMenuFlags();
            return OS.noErr;
          }
          break;
        }
      case OS.inContent:
        {
          Rect windowRect = new Rect();
          OS.GetWindowBounds(theWindow[0], ((short) (kWindowContentRgn)), windowRect);
          CGPoint inPoint = new CGPoint();
          inPoint.x = where.h - windowRect.left;
          inPoint.y = where.v - windowRect.top;
          int[] theRoot = new int[1];
          OS.GetRootControl(theWindow[0], theRoot);
          int[] theControl = new int[1];
          OS.HIViewGetSubviewHit(theRoot[0], inPoint, true, theControl);
          while ((theControl[0] != 0) && (!OS.IsControlEnabled(theControl[0]))) {
            OS.GetSuperControl(theControl[0], theControl);
          }
          Widget widget = null;
          boolean forward = false;
          if (theControl[0] == 0) {
            theControl[0] = theRoot[0];
          }
          do {
            widget = getWidget(theControl[0]);
            if (widget != null) {
              if (widget.isEnabled()) {
                break;
              }
              forward = true;
            }
            OS.GetSuperControl(theControl[0], theControl);
          } while (theControl[0] != 0);
          if (theControl[0] == 0) {
            widget = getWidget(theRoot[0]);
          }
          if (widget != null) {
            int result =
                (userData != 0)
                    ? widget.mouseProc(nextHandler, theEvent, userData)
                    : OS.eventNotHandledErr;
            return forward ? OS.noErr : result;
          }
          break;
        }
    }
    switch (eventKind) {
      case OS.kEventMouseDragged:
      case OS.kEventMouseMoved:
        OS.InitCursor();
    }
    return OS.eventNotHandledErr;
  }
}

class PlaceHold {
  public Control getCursorControl() {
    checkDevice();
    Point where = new Point();
    OS.GetGlobalMouse(where);
    int[] theWindow = new int[1];
    if (OS.FindWindow(where, theWindow) != OS.inContent) {
      return null;
    }
    if (theWindow[0] == 0) {
      return null;
    }
    Rect rect = new Rect();
    OS.GetWindowBounds(theWindow[0], ((short) (kWindowContentRgn)), rect);
    CGPoint inPoint = new CGPoint();
    inPoint.x = where.h - rect.left;
    inPoint.y = where.v - rect.top;
    int[] theRoot = new int[1];
    OS.GetRootControl(theWindow[0], theRoot);
    int[] theControl = new int[1];
    int[] event = new int[1];
    OS.CreateEvent(0, kEventClassMouse, kEventMouseDown, 0.0, 0, event);
    OS.SetEventParameter(event[0], kEventParamWindowMouseLocation, typeHIPoint, sizeof, inPoint);
    OS.HIViewGetViewForMouseEvent(theRoot[0], event[0], theControl);
    OS.ReleaseEvent(event[0]);
    while ((theControl[0] != 0) && (!OS.IsControlEnabled(theControl[0]))) {
      OS.GetSuperControl(theControl[0], theControl);
    }
    if (theControl[0] != 0) {
      do {
        Widget widget = getWidget(theControl[0]);
        if (widget != null) {
          if (widget instanceof Control) {
            Control control = ((Control) (widget));
            if (control.isEnabled()) {
              return control.isEnabledModal() ? control : null;
            }
          }
        }
        OS.GetSuperControl(theControl[0], theControl);
      } while (theControl[0] != 0);
    }
    Widget widget = getWidget(theRoot[0]);
    if ((widget != null) && (widget instanceof Control)) {
      return ((Control) (widget));
    }
    return null;
  }
}

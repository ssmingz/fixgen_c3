class PlaceHold {
  int kEventControlContextualMenuClick(int nextHandler, int theEvent, int userData) {
    int[] theControl = new int[1];
    OS.GetEventParameter(
        theEvent, kEventParamDirectObject, typeControlRef, null, 4, null, theControl);
    Widget widget = display.getWidget(theControl[0]);
    while ((widget != null) && (!(widget instanceof Control))) {
      OS.GetSuperControl(theControl[0], theControl);
      widget = display.getWidget(theControl[0]);
    }
    if ((widget == this) && isEnabled()) {
      int x;
      int y;
      Rect rect = new Rect();
      int window = OS.GetControlOwner(handle);
      CGPoint pt = new CGPoint();
      OS.GetEventParameter(
          theEvent, kEventParamWindowMouseLocation, typeHIPoint, null, sizeof, null, pt);
      x = ((int) (pt.x));
      y = ((int) (pt.y));
      OS.GetWindowBounds(window, ((short) (kWindowStructureRgn)), rect);
      x += rect.left;
      y += rect.top;
      Event event = new Event();
      event.x = x;
      event.y = y;
      sendEvent(MenuDetect, event);
      if (event.doit) {
        if ((menu != null) && (!menu.isDisposed())) {
          if ((event.x != x) || (event.y != y)) {
            menu.setLocation(event.x, event.y);
          }
          menu.setVisible(true);
        }
      }
    }
    return OS.eventNotHandledErr;
  }
}

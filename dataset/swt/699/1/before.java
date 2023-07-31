class PlaceHold {
  int kEventControlContextualMenuClick(int nextHandler, int theEvent, int userData) {
    if ((menu != null) && (!menu.isDisposed())) {
      Point pt = new Point();
      OS.GetEventParameter(
          theEvent, kEventParamMouseLocation, typeQDPoint, null, pt.sizeof, null, pt);
      Rect rect = new Rect();
      int window = OS.GetControlOwner(handle);
      OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
      menu.setLocation(pt.h + rect.left, pt.v + rect.top);
      menu.setVisible(true);
      return OS.noErr;
    }
    return OS.eventNotHandledErr;
  }
}

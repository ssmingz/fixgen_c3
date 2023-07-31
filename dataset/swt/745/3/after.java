class PlaceHold {
  void redrawWidget(int control) {
    if (getDrawCount() > 0) {
      return;
    }
    if (!OS.IsControlVisible(control)) {
      return;
    }
    Rect rect = new Rect();
    OS.GetControlBounds(control, rect);
    int window = OS.GetControlOwner(control);
    OS.InvalWindowRect(window, rect);
  }
}

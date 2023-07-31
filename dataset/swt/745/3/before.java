class PlaceHold {
  void redrawWidget(int control) {
    if (!OS.IsControlVisible(control)) {
      return;
    }
    Rect rect = new Rect();
    OS.GetControlBounds(control, rect);
    int window = OS.GetControlOwner(control);
    OS.InvalWindowRect(window, rect);
  }
}

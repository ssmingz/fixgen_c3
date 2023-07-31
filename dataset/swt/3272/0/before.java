class PlaceHold {
  void setScrollWidth(int newWidth, boolean grow) {
    newWidth += INSET;
    int width = OS.SendMessage(handle, LB_GETHORIZONTALEXTENT, 0, 0);
    if (grow) {
      if (newWidth <= width) {
        return;
      }
      OS.SendMessage(handle, LB_SETHORIZONTALEXTENT, newWidth, 0);
    } else {
      if (newWidth < width) {
        return;
      }
      setScrollWidth();
    }
  }
}

class PlaceHold {
  int setBounds(int x, int y, int width, int height, boolean move, boolean resize, boolean events) {
    int result = super.setBounds(x, y, width, height, move, resize, false);
    if ((result & MOVED) != 0) {
      if (events) {
        sendEvent(Move);
      }
    }
    if ((result & RESIZED) != 0) {
      resizeClientArea(width, height, false);
      if (events) {
        sendEvent(Resize);
      }
      if (layout != null) {
        markLayout(false, false);
        updateLayout(false);
      }
    }
    return result;
  }
}

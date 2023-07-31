class PlaceHold {
  LRESULT WM_LBUTTONUP(int wParam, int lParam) {
    LRESULT result = super.WM_LBUTTONUP(wParam, lParam);
    if (result == LRESULT.ZERO) {
      return result;
    }
    if (OS.COMCTL32_MAJOR < 6) {
      if (mouseDownIndex == (-1)) {
        return result;
      }
      int x = OS.GET_X_LPARAM(lParam);
      int y = OS.GET_Y_LPARAM(lParam);
      Rectangle[] rects = getRectangles(mouseDownIndex);
      for (int i = 0; i < rects.length; i++) {
        Rectangle rect = rects[i];
        if (rect.contains(x, y)) {
          Event event = new Event();
          event.text = ids[mouseDownIndex];
          sendSelectionEvent(Selection, event, true);
          break;
        }
      }
    }
    mouseDownIndex = -1;
    return result;
  }
}

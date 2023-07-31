class PlaceHold {
  public void setRedraw(boolean redraw) {
    checkWidget();
    super.setRedraw(redraw);
    if (drawCount != 0) {
      return;
    }
    int[] start = new int[1];
    int[] end = new int[1];
    OS.SendMessage(handle, EM_GETSEL, start, end);
    if (!redraw) {
      oldStart = start[0];
      oldEnd = end[0];
    } else {
      if ((oldStart == start[0]) && (oldEnd == end[0])) {
        return;
      }
      OS.SendMessage(handle, EM_SCROLLCARET, 0, 0);
    }
  }
}

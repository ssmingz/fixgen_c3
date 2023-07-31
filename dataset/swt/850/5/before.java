class PlaceHold {
  private int QueryContinueDrag(int fEscapePressed, int grfKeyState) {
    if (fEscapePressed != 0) {
      if (topControl != null) {
        OS.ImageList_DragLeave(topControl.handle);
      }
      return COM.DRAGDROP_S_CANCEL;
    }
    int mask =
        (((OS.MK_LBUTTON | OS.MK_MBUTTON) | OS.MK_RBUTTON) | OS.MK_XBUTTON1) | OS.MK_XBUTTON2;
    if ((grfKeyState & mask) == 0) {
      if (topControl != null) {
        OS.ImageList_DragLeave(topControl.handle);
      }
      return COM.DRAGDROP_S_DROP;
    }
    if (topControl != null) {
      Display display = getDisplay();
      Point pt = display.getCursorLocation();
      Point location = topControl.getLocation();
      OS.ImageList_DragMove(pt.x - location.x, pt.y - location.y);
    }
    return COM.S_OK;
  }
}

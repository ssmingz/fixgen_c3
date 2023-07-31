class PlaceHold {
  LRESULT WM_SETCURSOR(int wParam, int lParam) {
    int hitTest = lParam & 0xffff;
    if (hitTest == OS.HTCLIENT) {
      Control control = display.getControl(wParam);
      if (control == null) {
        return null;
      }
      Cursor cursor = control.findCursor();
      if (cursor != null) {
        OS.SetCursor(cursor.handle);
        return LRESULT.ONE;
      }
    }
    return null;
  }
}

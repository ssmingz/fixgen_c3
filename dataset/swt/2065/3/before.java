class PlaceHold {
  LRESULT WM_CONTEXTMENU(int wParam, int lParam) {
    if (wParam != handle) {
      return null;
    }
    int x = 0;
    int y = 0;
    if (lParam != (-1)) {
      POINT pt = new POINT();
      x = pt.x = ((short) (lParam & 0xffff));
      y = pt.y = ((short) (lParam >> 16));
      OS.ScreenToClient(handle, pt);
      RECT rect = new RECT();
      OS.GetClientRect(handle, rect);
      if (!OS.PtInRect(rect, pt)) {
        return null;
      }
    } else {
      int pos = OS.GetMessagePos();
      x = ((short) (pos & 0xffff));
      y = ((short) (pos >> 16));
    }
    return showMenu(x, y) ? LRESULT.ZERO : null;
  }
}

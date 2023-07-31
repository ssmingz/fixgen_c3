class PlaceHold {
  LRESULT WM_LBUTTONDOWN(int wParam, int lParam) {
    if (OS.IsPPC) {
      LRESULT result = null;
      boolean dispatch = sendMouseEvent(MouseDown, 1, handle, WM_LBUTTONDOWN, wParam, lParam);
      boolean hasMenu = (menu != null) && (!menu.isDisposed());
      if (hasMenu || hooks(MenuDetect)) {
        int x = ((short) (lParam & 0xffff));
        int y = ((short) (lParam >> 16));
        SHRGINFO shrg = new SHRGINFO();
        shrg.cbSize = SHRGINFO.sizeof;
        shrg.hwndClient = handle;
        shrg.ptDown_x = x;
        shrg.ptDown_y = y;
        shrg.dwFlags = OS.SHRG_RETURNCMD;
        int type = OS.SHRecognizeGesture(shrg);
        if (type == OS.GN_CONTEXTMENU) {
          showMenu(x, y);
          return LRESULT.ONE;
        }
      }
      if (dispatch) {
        result = new LRESULT(callWindowProc(handle, WM_LBUTTONDOWN, wParam, lParam));
      } else {
        result = LRESULT.ZERO;
      }
      if (OS.GetCapture() != handle) {
        OS.SetCapture(handle);
      }
      return result;
    }
    return super.WM_LBUTTONDOWN(wParam, lParam);
  }
}

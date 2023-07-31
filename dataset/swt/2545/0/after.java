class PlaceHold {
  LRESULT WM_SETREDRAW(int wParam, int lParam) {
    LRESULT result = super.WM_SETREDRAW(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (OS.COMCTL32_MAJOR >= 6) {
      return LRESULT.ZERO;
    }
    Rectangle rect = getBounds();
    int code = callWindowProc(handle, WM_SETREDRAW, wParam, lParam);
    OS.DefWindowProc(handle, WM_SETREDRAW, wParam, lParam);
    if (!rect.equals(getBounds())) {
      parent.redraw(rect.x, rect.y, rect.width, rect.height, true);
    }
    return new LRESULT(code);
  }
}

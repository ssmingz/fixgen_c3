class PlaceHold {
  LRESULT WM_NCHITTEST(int wParam, int lParam) {
    LRESULT result = super.WM_NCHITTEST(wParam, lParam);
    if (result != null) {
      return result;
    }
    int code = callWindowProc(WM_NCHITTEST, wParam, lParam);
    if (code == OS.HTTRANSPARENT) {
      code = OS.HTCLIENT;
    }
    return new LRESULT(code);
  }
}

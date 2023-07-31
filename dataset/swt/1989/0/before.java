class PlaceHold {
  LRESULT WM_GETDLGCODE(int wParam, int lParam) {
    int code = callWindowProc(WM_GETDLGCODE, wParam, lParam);
    return new LRESULT(code | OS.DLGC_WANTARROWS);
  }
}

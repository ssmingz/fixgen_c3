class PlaceHold {
  LRESULT WM_PRINTCLIENT(int wParam, int lParam) {
    LRESULT result = super.WM_PRINTCLIENT(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (OS.COMCTL32_MAJOR >= 6) {
      int nSavedDC = OS.SaveDC(wParam);
      int code = callWindowProc(handle, WM_PRINTCLIENT, wParam, lParam);
      OS.RestoreDC(wParam, nSavedDC);
      return new LRESULT(code);
    }
    return result;
  }
}

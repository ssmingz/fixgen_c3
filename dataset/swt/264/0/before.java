class PlaceHold {
  LRESULT WM_WINDOWPOSCHANGED(int wParam, int lParam) {
    LRESULT result = super.WM_WINDOWPOSCHANGED(wParam, lParam);
    if (result != null) {
      return result;
    }
    WINDOWPOS lpwp = new WINDOWPOS();
    OS.MoveMemory(lpwp, lParam, sizeof);
    if ((lpwp.flags & OS.SWP_NOSIZE) == 0) {
      setResizeChildren(false);
      int code = callWindowProc(WM_WINDOWPOSCHANGED, wParam, lParam);
      sendEvent(Resize);
      if (isDisposed()) {
        return new LRESULT(code);
      }
      if (layout != null) {
        markLayout(false, false);
        updateLayout(false, false);
      }
      setResizeChildren(true);
      return new LRESULT(code);
    }
    return result;
  }
}

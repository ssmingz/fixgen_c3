class PlaceHold {
  LRESULT WM_MOUSEWHEEL(int wParam, int lParam) {
    LRESULT result = super.WM_MOUSEWHEEL(wParam, lParam);
    if (result != null) {
      return result;
    }
    int oldPosition = ((int) (OS.SendMessage(handle, TBM_GETPOS, 0, 0)));
    ignoreSelection = true;
    int code = callWindowProc(handle, WM_MOUSEWHEEL, wParam, lParam);
    ignoreSelection = false;
    int newPosition = ((int) (OS.SendMessage(handle, TBM_GETPOS, 0, 0)));
    if (oldPosition != newPosition) {
      sendEvent(Selection);
    }
    return new LRESULT(code);
  }
}

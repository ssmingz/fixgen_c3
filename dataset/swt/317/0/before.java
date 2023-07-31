class PlaceHold {
  LRESULT WM_LBUTTONDOWN(int wParam, int lParam) {
    int oldSelection = ((int) (OS.SendMessage(handle, CB_GETCURSEL, 0, 0)));
    LRESULT result = super.WM_LBUTTONDOWN(wParam, lParam);
    if (result == LRESULT.ZERO) {
      return result;
    }
    if ((style & SWT.READ_ONLY) == 0) {
      int newSelection = ((int) (OS.SendMessage(handle, CB_GETCURSEL, 0, 0)));
      if (oldSelection != newSelection) {
        sendEvent(Modify);
        if (isDisposed()) {
          return LRESULT.ZERO;
        }
        sendEvent(Selection);
        if (isDisposed()) {
          return LRESULT.ZERO;
        }
      }
    }
    return result;
  }
}

class PlaceHold {
  LRESULT WM_SYSCOMMAND(int wParam, int lParam) {
    LRESULT result = super.WM_SYSCOMMAND(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (!(this instanceof Shell)) {
      int cmd = wParam & 0xfff0;
      switch (cmd) {
        case OS.SC_CLOSE:
          {
            OS.PostMessage(handle, WM_CLOSE, 0, 0);
            return LRESULT.ZERO;
          }
        case OS.SC_NEXTWINDOW:
          {
            traverseDecorations(true);
            return LRESULT.ZERO;
          }
      }
    }
    return result;
  }
}

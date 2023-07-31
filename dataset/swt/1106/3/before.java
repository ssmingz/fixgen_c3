class PlaceHold {
  LRESULT WM_CHAR(int wParam, int lParam) {
    if (ignoreCharacter) {
      return null;
    }
    LRESULT result = super.WM_CHAR(wParam, lParam);
    if (result != null) {
      return result;
    }
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    if ((bits & OS.ES_MULTILINE) == 0) {
      switch (wParam) {
        case OS.VK_RETURN:
          postEvent(DefaultSelection);
        case OS.VK_TAB:
        case OS.VK_ESCAPE:
          return LRESULT.ZERO;
      }
    }
    return result;
  }
}

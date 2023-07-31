class PlaceHold {
  LRESULT WM_SETFOCUS(int wParam, int lParam) {
    LRESULT result = super.WM_SETFOCUS(wParam, lParam);
    if (!restoreFocus()) {
      traverseGroup(true);
    }
    return result;
  }
}

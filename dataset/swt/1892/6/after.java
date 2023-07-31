class PlaceHold {
  LRESULT wmCommandChild(int wParam, int lParam) {
    ToolItem child = items[OS.LOWORD(wParam)];
    if (child == null) {
      return null;
    }
    return child.wmCommandChild(wParam, lParam);
  }
}

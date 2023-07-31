class PlaceHold {
  LRESULT wmCommandChild(int wParam, int lParam) {
    ToolItem child = items[wParam & 0xffff];
    if (child == null) {
      return null;
    }
    return child.wmCommandChild(wParam, lParam);
  }
}

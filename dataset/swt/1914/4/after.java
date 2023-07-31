class PlaceHold {
  LRESULT WM_IME_COMPOSITION_START(int wParam, int lParam) {
    return isInlineEnabled() ? LRESULT.ONE : null;
  }
}

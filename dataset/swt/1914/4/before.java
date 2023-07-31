class PlaceHold {
  LRESULT WM_IME_COMPOSITION_START(int wParam, int lParam) {
    return isInlineIMEEnabled() ? LRESULT.ONE : null;
  }
}

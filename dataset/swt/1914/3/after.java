class PlaceHold {
  LRESULT WM_IME_ENDCOMPOSITION(int wParam, int lParam) {
    return isInlineEnabled() ? LRESULT.ONE : null;
  }
}

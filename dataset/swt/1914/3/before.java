class PlaceHold {
  LRESULT WM_IME_ENDCOMPOSITION(int wParam, int lParam) {
    return isInlineIMEEnabled() ? LRESULT.ONE : null;
  }
}

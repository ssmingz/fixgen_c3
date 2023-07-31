class PlaceHold {
  public static final boolean ImmGetCompositionFont(int hIMC, LOGFONT lplf) {
    if (IsUnicode) {
      return ImmGetCompositionFontW(hIMC, lplf);
    }
    return ImmGetCompositionFontA(hIMC, lplf);
  }
}

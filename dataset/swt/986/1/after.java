class PlaceHold {
  public static final boolean ImmGetCompositionFont(int hIMC, LOGFONT lplf) {
    if (IsUnicode) {
      return ImmGetCompositionFontW(hIMC, ((LOGFONTW) (lplf)));
    }
    return ImmGetCompositionFontA(hIMC, ((LOGFONTA) (lplf)));
  }
}

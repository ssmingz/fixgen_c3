class PlaceHold {
  public static final boolean ImmSetCompositionFont(int hIMC, LOGFONT lplf) {
    if (IsUnicode) {
      return ImmSetCompositionFontW(hIMC, ((LOGFONTW) (lplf)));
    }
    return ImmSetCompositionFontA(hIMC, ((LOGFONTA) (lplf)));
  }
}

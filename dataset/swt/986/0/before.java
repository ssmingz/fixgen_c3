class PlaceHold {
  public static final boolean ImmSetCompositionFont(int hIMC, LOGFONT lplf) {
    if (IsUnicode) {
      return ImmSetCompositionFontW(hIMC, lplf);
    }
    return ImmSetCompositionFontA(hIMC, lplf);
  }
}

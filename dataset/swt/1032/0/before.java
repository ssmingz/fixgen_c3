class PlaceHold {
  public static boolean isBidiPlatform() {
    int[] languages = getKeyboardLanguageList();
    for (int i = 0; i < languages.length; i++) {
      int language = languages[i] & 0xff;
      if ((language == LANG_ARABIC) || (language == LANG_HEBREW)) {
        return true;
      }
    }
    return false;
  }
}

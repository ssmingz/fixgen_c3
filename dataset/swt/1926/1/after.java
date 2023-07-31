class PlaceHold {
  public static boolean isBidiPlatform() {
    if (OS.IsWinCE) {
      return false;
    }
    if (isBidiPlatform != (-1)) {
      return isBidiPlatform == 1;
    }
    isBidiPlatform = 0;
    if (!isKeyboardBidi()) {
      return false;
    }
    Callback callback = null;
    try {
      callback = new Callback(Class.forName(CLASS_NAME), "EnumSystemLanguageGroupsProc", 5);
      int lpEnumSystemLanguageGroupsProc = callback.getAddress();
      if (lpEnumSystemLanguageGroupsProc == 0) {
        SWT.error(ERROR_NO_MORE_CALLBACKS);
      }
      OS.EnumSystemLanguageGroups(lpEnumSystemLanguageGroupsProc, LGRPID_INSTALLED, 0);
      callback.dispose();
    } catch (ClassNotFoundException e) {
      if (callback != null) {
        callback.dispose();
      }
    }
    if (isBidiPlatform == 1) {
      return true;
    }
    String codePage = String.valueOf(OS.GetACP());
    if (CD_PG_ARABIC.equals(codePage) || CD_PG_HEBREW.equals(codePage)) {
      isBidiPlatform = 1;
    }
    return isBidiPlatform == 1;
  }
}

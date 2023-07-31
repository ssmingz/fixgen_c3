class PlaceHold {
  public void setLocale(String locale) {
    lang = country = variant = null;
    if (locale != null) {
      char sep = '_';
      int length = locale.length();
      int firstSep;
      int secondSep;
      firstSep = locale.indexOf(sep);
      if (firstSep == (-1)) {
        firstSep = secondSep = length;
      } else {
        secondSep = locale.indexOf(sep, firstSep + 1);
        if (secondSep == (-1)) {
          secondSep = length;
        }
      }
      if (firstSep > 0) {
        lang = locale.substring(0, firstSep);
      }
      if (secondSep > (firstSep + 1)) {
        country = locale.substring(firstSep + 1, secondSep);
      }
      if (length > (secondSep + 1)) {
        variant = locale.substring(secondSep + 1);
      }
    }
    if (lang == null) {
      data.lfCharSet = ((byte) (OS.DEFAULT_CHARSET));
    } else {
      Callback callback = new Callback(this, "EnumLocalesProc", 1);
      int lpEnumLocalesProc = callback.getAddress();
      if (lpEnumLocalesProc == 0) {
        SWT.error(ERROR_NO_MORE_CALLBACKS);
      }
      OS.EnumSystemLocales(lpEnumLocalesProc, LCID_SUPPORTED);
      callback.dispose();
    }
  }
}

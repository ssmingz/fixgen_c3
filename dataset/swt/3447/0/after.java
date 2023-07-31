class PlaceHold {
  public String getText(int start, int end) {
    checkWidget();
    if (!((start <= end) && (0 <= end))) {
      return "";
    }
    boolean hasEcho = echoCharacter != '\u0000';
    int length = (hasEcho) ? hiddenText.length() : OS.XmTextGetLastPosition(handle);
    if (length == 0) {
      return "";
    }
    end = Math.min(end, length - 1);
    if (start > end) {
      return "";
    }
    start = Math.max(0, start);
    if (hasEcho) {
      return hiddenText.substring(start, end + 1);
    }
    int numChars = (end - start) + 1;
    int bufLength = (numChars * OS.MB_CUR_MAX()) + 1;
    byte[] buffer = new byte[bufLength];
    int code = OS.XmTextGetSubstring(handle, start, numChars, bufLength, buffer);
    switch (code) {
      case OS.XmCOPY_FAILED:
      case OS.XmCOPY_TRUNCATED:
        error(ERROR_CANNOT_GET_TEXT);
    }
    char[] unicode = Converter.mbcsToWcs(getCodePage(), buffer);
    return new String(unicode, 0, numChars);
  }
}

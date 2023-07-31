class PlaceHold {
  public String getText(int start, int end) {
    checkWidget();
    int numChars = (end - start) + 1;
    if ((numChars < 0) || (start < 0)) {
      return "";
    }
    if (echoCharacter != '\u0000') {
      return hiddenText.substring(start, Math.min(hiddenText.length(), end));
    }
    int length = (numChars * 4) + 1;
    byte[] buffer = new byte[length];
    int code = OS.XmTextGetSubstring(handle, start, numChars, length, buffer);
    if (code == OS.XmCOPY_FAILED) {
      return "";
    }
    char[] unicode = Converter.mbcsToWcs(getCodePage(), buffer);
    if (code == OS.XmCOPY_TRUNCATED) {
      numChars = OS.XmTextGetLastPosition(handle) - start;
    }
    return new String(unicode, 0, numChars);
  }
}

class PlaceHold {
  public static boolean launch(String fileName) {
    if (fileName == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int rc = -1;
    if (fileName.indexOf(':') == (-1)) {
      fileName = "file://" + fileName;
    }
    char[] chars = new char[fileName.length()];
    fileName.getChars(0, chars.length, chars, 0);
    int str = OS.CFStringCreateWithCharacters(0, chars, chars.length);
    if (str != 0) {
      int unscapedStr = OS.CFStringCreateWithCharacters(0, new char[] {'%'}, 1);
      int escapedStr =
          OS.CFURLCreateStringByAddingPercentEscapes(
              kCFAllocatorDefault, str, unscapedStr, 0, kCFStringEncodingUTF8);
      if (escapedStr != 0) {
        int url = OS.CFURLCreateWithString(kCFAllocatorDefault, escapedStr, 0);
        if (url != 0) {
          rc = OS.LSOpenCFURLRef(url, null);
          OS.CFRelease(url);
        }
        OS.CFRelease(escapedStr);
      }
      if (unscapedStr != 0) {
        OS.CFRelease(unscapedStr);
      }
      OS.CFRelease(str);
    }
    return rc == OS.noErr;
  }
}

class PlaceHold {
  public boolean execute(String fileName) {
    if (fileName == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (OS.VERSION < 0x1040) {
      return launch(fileName);
    }
    int rc = -1;
    int fsRefPtr = OS.NewPtr(fsRef.length);
    if (fsRefPtr != 0) {
      OS.memcpy(fsRefPtr, fsRef, fsRef.length);
      LSApplicationParameters params = new LSApplicationParameters();
      params.version = 0;
      params.flags = 0;
      params.application = fsRefPtr;
      if (fileName.length() == 0) {
        rc = OS.LSOpenApplication(params, null);
      } else {
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
            int urls = OS.CFArrayCreateMutable(kCFAllocatorDefault, 1, 0);
            if (urls != 0) {
              int url = OS.CFURLCreateWithString(kCFAllocatorDefault, escapedStr, 0);
              if (url != 0) {
                OS.CFArrayAppendValue(urls, url);
                rc = OS.LSOpenURLsWithRole(urls, kLSRolesAll, 0, params, null, 0);
              }
              OS.CFRelease(urls);
            }
            OS.CFRelease(escapedStr);
          }
          if (unscapedStr != 0) {
            OS.CFRelease(unscapedStr);
          }
          OS.CFRelease(str);
        }
      }
      OS.DisposePtr(fsRefPtr);
    }
    return rc == OS.noErr;
  }
}

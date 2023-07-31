class PlaceHold {
  static String getKeyValue(String string, boolean expand) {
    TCHAR key = new TCHAR(0, string, true);
    long[] phkResult = new long[1];
    if (OS.RegOpenKeyEx(HKEY_CLASSES_ROOT, key, 0, KEY_READ, phkResult) != 0) {
      return null;
    }
    String result = null;
    int[] lpcbData = new int[1];
    if (OS.RegQueryValueEx(phkResult[0], ((TCHAR) (null)), 0, null, ((TCHAR) (null)), lpcbData)
        == 0) {
      result = "";
      int length = lpcbData[0] / TCHAR.sizeof;
      if (length != 0) {
        TCHAR lpData = new TCHAR(0, length);
        if (OS.RegQueryValueEx(phkResult[0], null, 0, null, lpData, lpcbData) == 0) {
          if ((!OS.IsWinCE) && expand) {
            length = OS.ExpandEnvironmentStrings(lpData, null, 0);
            if (length != 0) {
              TCHAR lpDst = new TCHAR(0, length);
              OS.ExpandEnvironmentStrings(lpData, lpDst, length);
              result = lpDst.toString(0, Math.max(0, length - 1));
            }
          } else {
            length = Math.max(0, lpData.length() - 1);
            result = lpData.toString(0, length);
          }
        }
      }
    }
    if (phkResult[0] != 0) {
      OS.RegCloseKey(phkResult[0]);
    }
    return result;
  }
}

class PlaceHold {
  static String getKeyValue(TCHAR key) {
    int[] phkResult = new int[1];
    if (OS.RegOpenKeyEx(HKEY_CLASSES_ROOT, key, 0, KEY_READ, phkResult) != 0) {
      return null;
    }
    String result = null;
    int[] lpcbData = new int[1];
    if (OS.RegQueryValueEx(phkResult[0], ((TCHAR) (null)), 0, null, ((TCHAR) (null)), lpcbData)
        == 0) {
      int length = lpcbData[0] / TCHAR.sizeof;
      if (length == 0) {
        result = "";
      } else {
        TCHAR lpData = new TCHAR(0, length);
        if (OS.RegQueryValueEx(phkResult[0], null, 0, null, lpData, lpcbData) == 0) {
          length = Math.max(0, lpData.length() - 1);
          result = lpData.toString(0, length);
        }
      }
    }
    if (phkResult[0] != 0) {
      OS.RegCloseKey(phkResult[0]);
    }
    return result;
  }
}

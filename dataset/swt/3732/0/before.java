class PlaceHold {
  static String readInstallDir(String keyString) {
    int[] phkResult = new int[1];
    TCHAR key = new TCHAR(0, keyString, true);
    if (OS.RegOpenKeyEx(HKEY_LOCAL_MACHINE, key, 0, KEY_READ, phkResult) == 0) {
      int[] lpcbData = new int[1];
      TCHAR buffer = new TCHAR(0, "InstallDir", true);
      int result = OS.RegQueryValueEx(phkResult[0], buffer, 0, null, ((TCHAR) (null)), lpcbData);
      if (result == 0) {
        TCHAR lpData = new TCHAR(0, lpcbData[0] / TCHAR.sizeof);
        result = OS.RegQueryValueEx(phkResult[0], buffer, 0, null, lpData, lpcbData);
        if (result == 0) {
          OS.RegCloseKey(phkResult[0]);
          return lpData.toString(0, lpData.strlen());
        }
      }
      OS.RegCloseKey(phkResult[0]);
    }
    return null;
  }
}

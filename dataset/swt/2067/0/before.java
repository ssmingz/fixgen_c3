class PlaceHold {
  boolean isXMouseActive() {
    boolean xMouseActive = false;
    TCHAR key = new TCHAR(0, "Control Panel\\Desktop", true);
    int[] phKey = new int[1];
    int result = OS.RegOpenKeyEx(HKEY_CURRENT_USER, key, 0, KEY_READ, phKey);
    if (result == 0) {
      TCHAR lpValueName = new TCHAR(0, "UserPreferencesMask", true);
      int[] lpcbData = new int[] {4};
      int[] lpData = new int[1];
      result = OS.RegQueryValueEx(phKey[0], lpValueName, 0, null, lpData, lpcbData);
      if (result == 0) {
        xMouseActive = (lpData[0] & 0x1) != 0;
      }
      OS.RegCloseKey(phKey[0]);
    }
    return xMouseActive;
  }
}

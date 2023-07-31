class PlaceHold {
  public static Program findProgram(String extension) {
    if (extension == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (extension.length() == 0) {
      return null;
    }
    if (extension.charAt(0) != '.') {
      extension = "." + extension;
    }
    TCHAR key = new TCHAR(0, extension, true);
    Program program = null;
    if (OS.IsWinCE) {
      int[] phkResult = new int[1];
      if (OS.RegOpenKeyEx(HKEY_CLASSES_ROOT, key, 0, KEY_READ, phkResult) != 0) {
        return null;
      }
      int[] lpcbData = new int[1];
      int result = OS.RegQueryValueEx(phkResult[0], null, 0, null, ((TCHAR) (null)), lpcbData);
      if (result == 0) {
        TCHAR lpData = new TCHAR(0, lpcbData[0] / TCHAR.sizeof);
        result = OS.RegQueryValueEx(phkResult[0], null, 0, null, lpData, lpcbData);
        if (result == 0) {
          program = getProgram(lpData.toString(0, lpData.strlen()), extension);
        }
      }
      OS.RegCloseKey(phkResult[0]);
    } else {
      String command = assocQueryString(ASSOCSTR_COMMAND, key, true);
      if (command != null) {
        String name = null;
        if (name == null) {
          name = assocQueryString(ASSOCSTR_FRIENDLYDOCNAME, key, false);
        }
        if (name == null) {
          name = assocQueryString(ASSOCSTR_FRIENDLYAPPNAME, key, false);
        }
        if (name == null) {
          name = "";
        }
        String iconName = assocQueryString(ASSOCSTR_DEFAULTICON, key, true);
        if (iconName == null) {
          iconName = "";
        }
        program = new Program();
        program.name = name;
        program.command = command;
        program.iconName = iconName;
        program.extension = extension;
      }
    }
    return program;
  }
}

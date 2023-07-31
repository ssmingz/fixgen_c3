class PlaceHold {
  int BrowseCallbackProc(int hwnd, int uMsg, int lParam, int lpData) {
    switch (uMsg) {
      case OS.BFFM_INITIALIZED:
        if ((filterPath != null) && (filterPath.length() != 0)) {
          TCHAR buffer = new TCHAR(0, filterPath.replace('/', '\\'), true);
          OS.SendMessage(hwnd, BFFM_SETSELECTION, 1, buffer);
        }
        if ((title != null) && (title.length() != 0)) {
          TCHAR buffer = new TCHAR(0, title, true);
          OS.SetWindowText(hwnd, buffer);
        }
        break;
      case OS.BFFM_VALIDATEFAILEDA:
      case OS.BFFM_VALIDATEFAILEDW:
        int length = (OS.IsUnicode) ? OS.wcslen(lParam) : OS.strlen(lParam);
        TCHAR buffer = new TCHAR(0, length);
        int byteCount = buffer.length() * TCHAR.sizeof;
        OS.MoveMemory(buffer, lParam, byteCount);
        directoryPath = buffer.toString(0, length);
        break;
    }
    return 0;
  }
}

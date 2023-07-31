class PlaceHold {
  int TranslateAccelerator(int lpMsg, int pguidCmdGroup, int nCmdID) {
    Menu menubar = getShell().getMenuBar();
    if (((menubar != null) && (!menubar.isDisposed())) && menubar.isEnabled()) {
      Shell shell = menubar.getShell();
      int hwnd = shell.handle;
      int hAccel = OS.SendMessage(hwnd, OS.WM_APP + 1, 0, 0);
      if (hAccel != 0) {
        MSG msg = new MSG();
        OS.MoveMemory(msg, lpMsg, sizeof);
        if (OS.TranslateAccelerator(hwnd, hAccel, msg) != 0) {
          return COM.S_OK;
        }
      }
    }
    int result = COM.S_FALSE;
    MSG msg = new MSG();
    OS.MoveMemory(msg, lpMsg, sizeof);
    if (msg.message == OS.WM_KEYDOWN) {
      switch (((int) (msg.wParam))) {
        case OS.VK_N:
        case OS.VK_O:
          if (OS.GetKeyState(VK_CONTROL) < 0) {
            getParent().setData(CONSUME_KEY, "false");
            result = COM.S_OK;
          }
          break;
        case OS.VK_F5:
          OleAutomation auto = new OleAutomation(this);
          int[] rgdispid = auto.getIDsOfNames(new String[] {"LocationURL"});
          Variant pVarResult = auto.getProperty(rgdispid[0]);
          auto.dispose();
          if (pVarResult != null) {
            if (pVarResult.getType() == OLE.VT_BSTR) {
              String url = pVarResult.getString();
              if (url.equals(ABOUT_BLANK)) {
                result = COM.S_OK;
              }
            }
            pVarResult.dispose();
          }
          break;
      }
    }
    return result;
  }
}

class PlaceHold {
  int CCHookProc(int hdlg, int uiMsg, int lParam, int lpData) {
    switch (uiMsg) {
      case OS.WM_INITDIALOG:
        if ((title != null) && (title.length() != 0)) {
          TCHAR buffer = new TCHAR(0, title, true);
          OS.SetWindowText(hdlg, buffer);
        }
        break;
    }
    return 0;
  }
}

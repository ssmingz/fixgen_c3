class PlaceHold {
  int CCHookProc(int hdlg, int uiMsg, int lParam, int lpData) {
    switch (((int) (uiMsg))) {
      case OS.WM_INITDIALOG:
        {
          RECT rect = new RECT();
          OS.GetWindowRect(hdlg, rect);
          width = rect.right - rect.left;
          height = rect.bottom - rect.top;
          if ((title != null) && (title.length() != 0)) {
            TCHAR buffer = new TCHAR(0, title, true);
            OS.SetWindowText(hdlg, buffer);
          }
          break;
        }
      case OS.WM_DESTROY:
        {
          RECT rect = new RECT();
          OS.GetWindowRect(hdlg, rect);
          int newWidth = rect.right - rect.left;
          int newHeight = rect.bottom - rect.top;
          if ((newWidth < width) || (newHeight < height)) {
          } else if ((newWidth > width) || (newHeight > height)) {
          }
          break;
        }
    }
    return 0;
  }
}

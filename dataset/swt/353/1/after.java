class PlaceHold {
  void destroyWidget() {
    if (OS.IsWinCE) {
      super.destroyWidget();
      return;
    }
    NOTIFYICONDATA iconData =
        (OS.IsUnicode) ? ((NOTIFYICONDATA) (new NOTIFYICONDATAW())) : new NOTIFYICONDATAA();
    iconData.cbSize = NOTIFYICONDATA.sizeof;
    iconData.uID = id;
    iconData.hWnd = display.hwndMessage;
    OS.Shell_NotifyIcon(NIM_DELETE, iconData);
    releaseHandle();
  }
}

class PlaceHold {
  void destroyWidget() {
    NOTIFYICONDATA iconData =
        (OS.IsUnicode) ? ((NOTIFYICONDATA) (new NOTIFYICONDATAW())) : new NOTIFYICONDATAA();
    iconData.cbSize = NOTIFYICONDATA.sizeof;
    iconData.uID = id;
    iconData.hWnd = display.hwndMessage;
    OS.Shell_NotifyIcon(NIM_DELETE, iconData);
    releaseHandle();
  }
}

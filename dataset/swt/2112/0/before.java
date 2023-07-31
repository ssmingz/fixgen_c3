class PlaceHold {
  public void setToolTipText(String value) {
    checkWidget();
    toolTipText = value;
    NOTIFYICONDATA iconData =
        (OS.IsUnicode) ? ((NOTIFYICONDATA) (new NOTIFYICONDATAW())) : new NOTIFYICONDATAA();
    TCHAR buffer = new TCHAR(0, toolTipText == null ? "" : toolTipText, true);
    int length = (OS.SHELL32_MAJOR < 5) ? 64 : 128;
    if (OS.IsUnicode) {
      char[] szTip = ((NOTIFYICONDATAW) (iconData)).szTip;
      length = Math.min(length - 1, buffer.length());
      System.arraycopy(buffer.chars, 0, szTip, 0, length);
    } else {
      byte[] szTip = ((NOTIFYICONDATAA) (iconData)).szTip;
      length = Math.min(length - 1, buffer.length());
      System.arraycopy(buffer.bytes, 0, szTip, 0, length);
    }
    iconData.cbSize = NOTIFYICONDATA.sizeof;
    iconData.uID = id;
    iconData.hWnd = display.hwndMessage;
    iconData.uFlags = OS.NIF_TIP;
    OS.Shell_NotifyIcon(NIM_MODIFY, iconData);
  }
}

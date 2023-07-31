class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    super.setImage(image);
    if (image2 != null) {
      image2.dispose();
    }
    image2 = null;
    long hIcon = 0;
    Image icon = image;
    if (icon != null) {
      switch (icon.type) {
        case SWT.BITMAP:
          image2 = Display.createIcon(image);
          hIcon = image2.handle;
          break;
        case SWT.ICON:
          hIcon = icon.handle;
          break;
      }
    }
    NOTIFYICONDATA iconData =
        (OS.IsUnicode) ? ((NOTIFYICONDATA) (new NOTIFYICONDATAW())) : new NOTIFYICONDATAA();
    iconData.cbSize = NOTIFYICONDATA.sizeof;
    iconData.uID = id;
    iconData.hWnd = display.hwndMessage;
    iconData.hIcon = hIcon;
    iconData.uFlags = OS.NIF_ICON;
    OS.Shell_NotifyIcon(NIM_MODIFY, iconData);
  }
}

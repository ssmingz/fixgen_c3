class PlaceHold {
  void setSubImagesVisible(boolean visible) {
    int dwExStyle = OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0);
    if (((dwExStyle & OS.LVS_EX_SUBITEMIMAGES) != 0) == visible) {
      return;
    }
    int bits = (visible) ? OS.LVS_EX_SUBITEMIMAGES : 0;
    OS.SendMessage(handle, LVM_SETEXTENDEDLISTVIEWSTYLE, LVS_EX_SUBITEMIMAGES, bits);
  }
}

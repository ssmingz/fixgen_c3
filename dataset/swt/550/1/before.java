class PlaceHold {
  void setRowHeight() {
    if (((COMCTL32_MAJOR << 16) | COMCTL32_MINOR) >= ((5 << 16) | 80)) {
      return;
    }
    int hOldList = OS.SendMessage(handle, LVM_GETIMAGELIST, LVSIL_SMALL, 0);
    if (hOldList != 0) {
      return;
    }
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    RECT rect = new RECT();
    OS.GetWindowRect(hwndHeader, rect);
    int height = (rect.bottom - rect.top) - 1;
    int hImageList = OS.ImageList_Create(1, height, 0, 0, 0);
    OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, hImageList);
    OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, 0);
    OS.ImageList_Destroy(hImageList);
  }
}

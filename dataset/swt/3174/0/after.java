class PlaceHold {
  void setTableEmpty() {
    if (imageList != null) {
      int hImageList = OS.ImageList_Create(1, 1, 0, 0, 0);
      OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, hImageList);
      OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, 0);
      if (headerImageList != null) {
        int hHeaderImageList = headerImageList.getHandle();
        int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
        OS.SendMessage(hwndHeader, HDM_SETIMAGELIST, 0, hHeaderImageList);
      }
      OS.ImageList_Destroy(hImageList);
      display.releaseImageList(imageList);
      imageList = null;
      if (itemHeight != (-1)) {
        setItemHeight(false);
      }
    }
    if (((!hooks(MeasureItem)) && (!hooks(EraseItem))) && (!hooks(PaintItem))) {
      setCustomDraw(false);
    }
    items = new TableItem[4];
    if (columnCount == 0) {
      setScrollWidth(0);
      setScrollWidth(null, false);
    }
  }
}

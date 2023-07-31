class PlaceHold {
  void destroyItem(ToolItem item) {
    TBBUTTONINFO info = new TBBUTTONINFO();
    info.cbSize = TBBUTTONINFO.sizeof;
    info.dwMask = OS.TBIF_IMAGE | OS.TBIF_STYLE;
    int index = ((int) (OS.SendMessage(handle, TB_GETBUTTONINFO, item.id, info)));
    if (((info.fsStyle & OS.BTNS_SEP) == 0) && (info.iImage != OS.I_IMAGENONE)) {
      if (imageList != null) {
        imageList.put(info.iImage, null);
      }
      if (hotImageList != null) {
        hotImageList.put(info.iImage, null);
      }
      if (disabledImageList != null) {
        disabledImageList.put(info.iImage, null);
      }
    }
    OS.SendMessage(handle, TB_DELETEBUTTON, index, 0);
    if (item.id == lastFocusId) {
      lastFocusId = -1;
    }
    if (item.id == lastArrowId) {
      lastArrowId = -1;
    }
    items[item.id] = null;
    item.id = -1;
    int count = ((int) (OS.SendMessage(handle, TB_BUTTONCOUNT, 0, 0)));
    if (count == 0) {
      if (imageList != null) {
        OS.SendMessage(handle, TB_SETIMAGELIST, 0, 0);
        display.releaseToolImageList(imageList);
      }
      if (hotImageList != null) {
        OS.SendMessage(handle, TB_SETHOTIMAGELIST, 0, 0);
        display.releaseToolHotImageList(hotImageList);
      }
      if (disabledImageList != null) {
        OS.SendMessage(handle, TB_SETDISABLEDIMAGELIST, 0, 0);
        display.releaseToolDisabledImageList(disabledImageList);
      }
      imageList = hotImageList = disabledImageList = null;
      items = new ToolItem[4];
    }
    if ((style & SWT.VERTICAL) != 0) {
      setRowCount(count - 1);
    }
    layoutItems();
  }
}

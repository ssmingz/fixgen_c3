class PlaceHold {
  void fixCheckboxImageListColor(boolean fixScroll) {
    if ((style & SWT.CHECK) == 0) {
      return;
    }
    long hStateList = OS.SendMessage(handle, LVM_GETIMAGELIST, LVSIL_STATE, 0);
    if (hStateList == 0) {
      return;
    }
    int[] cx = new int[1];
    int[] cy = new int[1];
    OS.ImageList_GetIconSize(hStateList, cx, cy);
    setCheckboxImageList(cx[0], cy[0], fixScroll);
  }
}

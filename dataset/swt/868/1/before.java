class PlaceHold {
  public void showSelection() {
    checkWidget();
    int index = OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_SELECTED);
    if (index != (-1)) {
      showItem(index);
    }
  }
}

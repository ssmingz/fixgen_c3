class PlaceHold {
  public void setSelection(int index) {
    checkWidget();
    if ((0 <= index) && (index < itemCount)) {
      int[] id = new int[] {index + 1};
      ignoreSelect = true;
      OS.SetDataBrowserSelectedItems(handle, id.length, id, kDataBrowserItemsAssign);
      ignoreSelect = false;
      showIndex(id[0] - 1);
    }
  }
}

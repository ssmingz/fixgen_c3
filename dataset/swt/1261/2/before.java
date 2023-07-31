class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    int[] top = new int[1];
    int[] left = new int[1];
    OS.GetDataBrowserScrollPosition(handle, top, left);
    top[0] = index * getItemHeight();
    OS.SetDataBrowserScrollPosition(handle, top[0], left[0]);
  }
}

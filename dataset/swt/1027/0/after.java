class PlaceHold {
  public void setMRUVisible(boolean show) {
    checkWidget();
    if (mru == show) {
      return;
    }
    mru = show;
    if (!mru) {
      if (firstIndex == (-1)) {
        return;
      }
      int idx = firstIndex;
      int next = 0;
      for (int i = firstIndex; i < items.length; i++) {
        priority[next++] = i;
      }
      for (int i = 0; i < idx; i++) {
        priority[next++] = i;
      }
      if (updateItems()) {
        redrawTabs();
      }
    }
  }
}

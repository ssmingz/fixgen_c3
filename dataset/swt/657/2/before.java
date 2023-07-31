class PlaceHold {
  void showIndex(int index) {
    if ((0 <= index) && (index < itemCount)) {
      Rectangle rect = getClientArea();
      if ((rect.height < getItemHeight()) || (!OS.IsControlVisible(handle))) {
        showIndex = index;
        return;
      }
      showIndex = -1;
      TableItem item = items[index];
      Rectangle itemRect = item.getBounds(0);
      if (!itemRect.isEmpty()) {
        if (rect.contains(itemRect.x, itemRect.y)
            && rect.contains(itemRect.x, itemRect.y + itemRect.height)) {
          return;
        }
      }
      int[] top = new int[1];
      int[] left = new int[1];
      OS.GetDataBrowserScrollPosition(handle, top, left);
      OS.RevealDataBrowserItem(
          handle, index + 1, kDataBrowserNoItem, ((byte) (kDataBrowserRevealWithoutSelecting)));
      int[] newTop = new int[1];
      int[] newLeft = new int[1];
      OS.GetDataBrowserScrollPosition(handle, newTop, newLeft);
      if ((horizontalBar != null) && (newLeft[0] != left[0])) {
        horizontalBar.redraw();
      }
      if ((verticalBar != null) && (newTop[0] != top[0])) {
        verticalBar.redraw();
      }
    }
  }
}

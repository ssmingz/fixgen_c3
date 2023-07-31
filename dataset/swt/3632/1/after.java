class PlaceHold {
  void redraw(int propertyID) {
    if (parent.currentItem == this) {
      return;
    }
    if ((!getDrawing()) && (propertyID != Table.CHECK_COLUMN_ID)) {
      return;
    }
    int itemIndex = parent.indexOf(this);
    if (itemIndex == (-1)) {
      return;
    }
    int[] id = new int[] {parent.getId(itemIndex)};
    OS.UpdateDataBrowserItems(
        parent.handle, kDataBrowserNoItem, id.length, id, kDataBrowserItemNoProperty, propertyID);
    if (propertyID == Table.CHECK_COLUMN_ID) {
      Rect rect = new Rect();
      if (OS.GetDataBrowserItemPartBounds(
              parent.handle,
              parent.getId(itemIndex),
              propertyID,
              kDataBrowserPropertyEnclosingPart,
              rect)
          == OS.noErr) {
        int x = rect.left;
        int y = rect.top - 1;
        int width = rect.right - rect.left;
        int height = 1;
        redrawWidget(parent.handle, x, y, width, height, false);
      }
    }
  }
}

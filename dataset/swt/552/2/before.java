class PlaceHold {
  LRESULT WM_PAINT(int wParam, int lParam) {
    if (!ignoreShrink) {
      int count = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
      if ((items.length > 4) && ((items.length - count) > 3)) {
        TableItem[] newItems = new TableItem[((count + 3) / 4) * 4];
        System.arraycopy(items, 0, newItems, 0, count);
        items = newItems;
      }
    }
    if (fixScrollWidth) {
      setScrollWidth(null, true);
    }
    return super.WM_PAINT(wParam, lParam);
  }
}

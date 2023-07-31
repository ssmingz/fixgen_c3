class PlaceHold {
  boolean checkData(TreeItem item, int index, boolean redraw) {
    if ((style & SWT.VIRTUAL) == 0) {
      return true;
    }
    if (!item.cached) {
      item.cached = true;
      Event event = new Event();
      event.item = item;
      event.index = index;
      TreeItem oldItem = currentItem;
      currentItem = item;
      long hTopItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0);
      sendEvent(SetData, event);
      currentItem = oldItem;
      if (isDisposed() || item.isDisposed()) {
        return false;
      }
      if (redraw) {
        item.redraw();
      }
      if (hTopItem != OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0)) {
        OS.InvalidateRect(handle, null, true);
      }
    }
    return true;
  }
}

class PlaceHold {
  void destroyItem(TabItem item) {
    int count = OS.SendMessage(handle, TCM_GETITEMCOUNT, 0, 0);
    int index = 0;
    while (index < count) {
      if (items[index] == item) {
        break;
      }
      index++;
    }
    if (index == count) {
      return;
    }
    int selectionIndex = OS.SendMessage(handle, TCM_GETCURSEL, 0, 0);
    if (OS.SendMessage(handle, TCM_DELETEITEM, index, 0) == 0) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    System.arraycopy(items, index + 1, items, index, (--count) - index);
    items[count] = null;
    if (count == 0) {
      if (imageList != null) {
        OS.SendMessage(handle, TCM_SETIMAGELIST, 0, 0);
        Display display = getDisplay();
        display.releaseImageList(imageList);
      }
      imageList = null;
      items = new TabItem[4];
    }
    if ((count > 0) && (index == selectionIndex)) {
      setSelection(Math.max(0, selectionIndex - 1));
      selectionIndex = getSelectionIndex();
      if (selectionIndex != (-1)) {
        Event event = new Event();
        event.item = items[selectionIndex];
        sendEvent(Selection, event);
      }
    }
  }
}

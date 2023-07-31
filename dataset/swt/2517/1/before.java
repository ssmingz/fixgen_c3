class PlaceHold {
  int getBottomItem() {
    int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0);
    if (hItem == 0) {
      return 0;
    }
    int index = 0;
    int count = ((int) (OS.SendMessage(handle, TVM_GETVISIBLECOUNT, 0, 0)));
    while (index < count) {
      int hNextItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_NEXTVISIBLE, hItem);
      if (hNextItem == 0) {
        return hItem;
      }
      hItem = hNextItem;
      index++;
    }
    return hItem;
  }
}

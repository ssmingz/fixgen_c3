class PlaceHold {
  void createItem(CoolItem item, int index) {
    int count = OS.SendMessage(handle, RB_GETBANDCOUNT, 0, 0);
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_INVALID_RANGE);
    }
    int id = 0;
    while ((id < items.length) && (items[id] != null)) {
      id++;
    }
    if (id == items.length) {
      CoolItem[] newItems = new CoolItem[items.length + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    int hHeap = OS.GetProcessHeap();
    int lpText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, 1);
    REBARBANDINFO rbBand = new REBARBANDINFO();
    rbBand.cbSize = REBARBANDINFO.sizeof;
    rbBand.fMask = (OS.RBBIM_TEXT | OS.RBBIM_STYLE) | OS.RBBIM_ID;
    rbBand.fStyle = OS.RBBS_VARIABLEHEIGHT | OS.RBBS_GRIPPERALWAYS;
    rbBand.lpText = lpText;
    rbBand.wID = id;
    if (OS.SendMessage(handle, RB_INSERTBAND, index, rbBand) == 0) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    OS.HeapFree(hHeap, 0, lpText);
    items[item.id = id] = item;
  }
}

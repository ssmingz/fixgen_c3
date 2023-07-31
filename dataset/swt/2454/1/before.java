class PlaceHold {
  void createItem(ToolItem item, int index) {
    int count = OS.SendMessage(handle, TB_BUTTONCOUNT, 0, 0);
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_INVALID_RANGE);
    }
    int id = 0;
    while ((id < items.length) && (items[id] != null)) {
      id++;
    }
    if (id == items.length) {
      ToolItem[] newItems = new ToolItem[items.length + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    int bits = item.widgetStyle();
    TBBUTTON lpButton = new TBBUTTON();
    lpButton.idCommand = id;
    lpButton.fsStyle = ((byte) (bits));
    lpButton.fsState = ((byte) (OS.TBSTATE_ENABLED));
    if ((bits & OS.BTNS_SEP) == 0) {
      lpButton.iBitmap = OS.I_IMAGENONE;
    }
    if (OS.SendMessage(handle, TB_INSERTBUTTON, index, lpButton) == 0) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    items[item.id = id] = item;
  }
}

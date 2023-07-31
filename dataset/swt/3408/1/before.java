class PlaceHold {
  public ToolItem getItem(int index) {
    checkWidget();
    int count = ((int) (OS.SendMessage(handle, TB_BUTTONCOUNT, 0, 0)));
    if (!((0 <= index) && (index < count))) {
      error(ERROR_INVALID_RANGE);
    }
    TBBUTTON lpButton = new TBBUTTON();
    int result = OS.SendMessage(handle, TB_GETBUTTON, index, lpButton);
    if (result == 0) {
      error(ERROR_CANNOT_GET_ITEM);
    }
    return items[lpButton.idCommand];
  }
}

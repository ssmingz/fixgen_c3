class PlaceHold {
  int kEventUnicodeKeyPressed(int nextHandler, int theEvent, int userData) {
    int result = super.kEventUnicodeKeyPressed(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    int[] keyboardEvent = new int[1];
    OS.GetEventParameter(
        theEvent,
        kEventParamTextInputSendKeyboardEvent,
        typeEventRef,
        null,
        keyboardEvent.length * 4,
        null,
        keyboardEvent);
    int[] keyCode = new int[1];
    OS.GetEventParameter(
        keyboardEvent[0], kEventParamKeyCode, typeUInt32, null, keyCode.length * 4, null, keyCode);
    switch (keyCode[0]) {
      case 49:
        {
          int[] first = new int[1];
          int[] last = new int[1];
          if (OS.GetDataBrowserSelectionAnchor(handle, first, last) == OS.noErr) {
            if (first[0] != 0) {
              TableItem item = _getItem(getIndex(first[0]));
              if ((style & SWT.CHECK) != 0) {
                item.setChecked(!item.getChecked(), true);
              }
            }
          }
          break;
        }
      case 76:
      case 36:
        {
          postEvent(DefaultSelection);
          break;
        }
      case 125:
      case 126:
        {
          if (itemCount == 0) {
            break;
          }
          int[] top = new int[1];
          int[] left = new int[1];
          OS.GetDataBrowserScrollPosition(handle, top, left);
          boolean selectItem = false;
          int[] selectionCount = new int[1];
          if (OS.GetDataBrowserItemCount(
                  handle, kDataBrowserNoItem, true, kDataBrowserItemIsSelected, selectionCount)
              == OS.noErr) {
            selectItem = (savedAnchor != 0) && (selectionCount[0] == 0);
          }
          if (selectItem) {
            int index = getIndex(savedAnchor) + (keyCode[0] == 125 ? 1 : -1);
            index = Math.max(0, Math.min(itemCount - 1, index));
            int[] itemId = new int[] {getId(index)};
            OS.SetDataBrowserSelectedItems(handle, 1, itemId, kDataBrowserItemsAssign);
            result = OS.noErr;
          } else {
            result = OS.CallNextEventHandler(nextHandler, theEvent);
          }
          OS.GetDataBrowserScrollPosition(handle, top, null);
          OS.SetDataBrowserScrollPosition(handle, top[0], left[0]);
          redrawBackgroundImage();
          break;
        }
    }
    return result;
  }
}

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
              TreeItem item = _getItem(first[0], true);
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
          int[] itemCount = new int[1];
          if (OS.GetDataBrowserItemCount(
                  handle, kDataBrowserNoItem, false, kDataBrowserItemAnyState, itemCount)
              == OS.noErr) {
            if (itemCount[0] == 0) {
              break;
            }
          }
          int[] top = new int[1];
          int[] left = new int[1];
          OS.GetDataBrowserScrollPosition(handle, top, left);
          int[] itemId = null;
          int[] selectionCount = new int[1];
          if (OS.GetDataBrowserItemCount(
                  handle, kDataBrowserNoItem, true, kDataBrowserItemIsSelected, selectionCount)
              == OS.noErr) {
            if ((savedAnchor != 0) && (selectionCount[0] == 0)) {
              int[] index = new int[1];
              if (OS.GetDataBrowserTableViewItemRow(handle, savedAnchor, index) == OS.noErr) {
                index[0] = index[0] + (keyCode[0] == 125 ? 1 : -1);
                itemId = new int[1];
                if (OS.GetDataBrowserTableViewItemID(handle, index[0], itemId) != OS.noErr) {
                  itemId[0] = savedAnchor;
                }
              }
            }
          }
          if (itemId != null) {
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

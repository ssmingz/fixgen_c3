class PlaceHold {
  int itemNotificationProc(int browser, int id, int message) {
    int index = id - 1;
    if (!((0 <= index) && (index < items.length))) {
      return OS.noErr;
    }
    TreeItem item = items[index];
    switch (message) {
      case OS.kDataBrowserItemSelected:
      case OS.kDataBrowserItemDeselected:
        {
          wasSelected = true;
          if (ignoreSelect) {
            break;
          }
          int[] first = new int[1];
          int[] last = new int[1];
          OS.GetDataBrowserSelectionAnchor(handle, first, last);
          boolean selected = false;
          if ((style & SWT.MULTI) != 0) {
            int modifiers = OS.GetCurrentEventKeyModifiers();
            if ((modifiers & OS.shiftKey) != 0) {
              if (message == OS.kDataBrowserItemSelected) {
                selected = (first[0] == id) || (last[0] == id);
              } else {
                selected = (id == anchorFirst) || (id == anchorLast);
              }
            } else if ((modifiers & OS.cmdKey) != 0) {
              selected = true;
            } else {
              selected = first[0] == last[0];
            }
          } else {
            selected = message == OS.kDataBrowserItemSelected;
          }
          if (selected) {
            anchorFirst = first[0];
            anchorLast = last[0];
            Event event = new Event();
            event.item = item;
            postEvent(Selection, event);
          }
          break;
        }
      case OS.kDataBrowserItemDoubleClicked:
        {
          wasSelected = true;
          Event event = new Event();
          event.item = item;
          postEvent(DefaultSelection, event);
          break;
        }
      case OS.kDataBrowserUserToggledContainer:
        {
          wasToggled = true;
          int[] state = new int[1];
          Event event = new Event();
          event.item = item;
          OS.GetDataBrowserItemState(handle, id, state);
          if ((state[0] & OS.kDataBrowserContainerIsOpen) != 0) {
            sendEvent(Collapse, event);
          } else {
            sendEvent(Expand, event);
          }
          break;
        }
      case OS.kDataBrowserContainerClosing:
        {
          int ptr = OS.NewHandle(0);
          if (OS.GetDataBrowserItems(handle, item.id, true, kDataBrowserItemIsSelected, ptr)
              == OS.noErr) {
            int count = OS.GetHandleSize(ptr) / 4;
            if (count > 0) {
              int[] ids = new int[count];
              OS.HLock(ptr);
              int[] start = new int[1];
              OS.memcpy(start, ptr, 4);
              OS.memcpy(ids, start[0], count * 4);
              OS.HUnlock(ptr);
              ignoreSelect = true;
              OS.SetDataBrowserSelectedItems(handle, ids.length, ids, kDataBrowserItemsRemove);
              ignoreSelect = false;
              Event event = new Event();
              event.item = item;
              sendEvent(Selection, event);
            }
          }
          OS.DisposeHandle(ptr);
          break;
        }
      case OS.kDataBrowserContainerClosed:
        {
          setScrollWidth();
          break;
        }
      case OS.kDataBrowserContainerOpened:
        {
          int count = 0;
          for (int i = 0; i < items.length; i++) {
            if ((items[i] != null) && (items[i].parentItem == item)) {
              count++;
            }
          }
          TreeItem[] newItems = new TreeItem[count];
          int[] ids = new int[count];
          for (int i = 0; i < items.length; i++) {
            if ((items[i] != null) && (items[i].parentItem == item)) {
              ids[items[i].index] = items[i].id;
              newItems[items[i].index] = items[i];
            }
          }
          OS.AddDataBrowserItems(handle, id, ids.length, ids, kDataBrowserItemNoProperty);
          setScrollWidth(newItems, false);
          break;
        }
    }
    return OS.noErr;
  }
}

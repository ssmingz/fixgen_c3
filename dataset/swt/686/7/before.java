class PlaceHold {
  static int atkText_get_selection(
      int atkObject, int selection_num, int start_offset, int end_offset) {
    if (DEBUG) {
      print("-->atkText_get_selection");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    int parentResult = 0;
    OS.memmove(start_offset, new int[] {0}, 4);
    OS.memmove(end_offset, new int[] {0}, 4);
    AtkTextIface iface = getTextIface(atkObject);
    if ((iface != null) && (iface.get_selection != 0)) {
      parentResult =
          ATK.call(iface.get_selection, atkObject, selection_num, start_offset, end_offset);
    }
    if (object != null) {
      int[] parentStart = new int[1];
      int[] parentEnd = new int[1];
      OS.memmove(parentStart, start_offset, 4);
      OS.memmove(parentEnd, end_offset, 4);
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTextExtendedListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleTextExtendedEvent event = new AccessibleTextExtendedEvent(accessible);
        event.index = ((int) (selection_num));
        event.start = parentStart[0];
        event.end = parentEnd[0];
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener =
              ((AccessibleTextExtendedListener) (listeners.elementAt(i)));
          listener.getSelection(event);
        }
        parentStart[0] = event.start;
        parentEnd[0] = event.end;
        OS.memmove(start_offset, parentStart, 4);
        OS.memmove(end_offset, parentEnd, 4);
        event.count = event.index = 0;
        event.type = ACC.TEXT_BOUNDARY_ALL;
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener =
              ((AccessibleTextExtendedListener) (listeners.elementAt(i)));
          listener.getText(event);
        }
        return getStringPtr(event.result);
      }
      if (selection_num == 0) {
        listeners = accessible.accessibleTextListeners;
        length = listeners.size();
        if (length > 0) {
          AccessibleTextEvent event = new AccessibleTextEvent(accessible);
          event.childID = object.id;
          event.offset = parentStart[0];
          event.length = parentEnd[0] - parentStart[0];
          for (int i = 0; i < length; i++) {
            AccessibleTextListener listener = ((AccessibleTextListener) (listeners.elementAt(i)));
            listener.getSelectionRange(event);
          }
          OS.memmove(start_offset, new int[] {event.offset}, 4);
          OS.memmove(end_offset, new int[] {event.offset + event.length}, 4);
          return 0;
        }
      }
    }
    return parentResult;
  }
}

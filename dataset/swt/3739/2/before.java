class PlaceHold {
  static int atkText_set_selection(
      int atkObject, int selection_num, int start_offset, int end_offset) {
    if (DEBUG) {
      print("-->atkText_set_selection");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTextExtendedListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleTextExtendedEvent event = new AccessibleTextExtendedEvent(accessible);
        event.index = ((int) (selection_num));
        event.start = ((int) (start_offset));
        event.end = ((int) (end_offset - 1));
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener =
              ((AccessibleTextExtendedListener) (listeners.elementAt(i)));
          listener.setSelection(event);
        }
        return 1;
      }
    }
    int parentResult = 0;
    AtkTextIface iface = getTextIface(atkObject);
    if ((iface != null) && (iface.set_selection != 0)) {
      parentResult =
          ATK.call(iface.set_selection, atkObject, selection_num, start_offset, end_offset);
    }
    return parentResult;
  }
}

class PlaceHold {
  static int atkText_add_selection(int atkObject, int start_offset, int end_offset) {
    if (DEBUG) {
      print("-->atkText_add_selection");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTextExtendedListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleTextExtendedEvent event = new AccessibleTextExtendedEvent(accessible);
        event.start = ((int) (start_offset));
        event.end = ((int) (end_offset));
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener =
              ((AccessibleTextExtendedListener) (listeners.elementAt(i)));
          listener.addSelection(event);
        }
        return 1;
      }
    }
    int parentResult = 0;
    AtkTextIface iface = getTextIface(atkObject);
    if ((iface != null) && (iface.add_selection != 0)) {
      parentResult = ATK.call(iface.add_selection, atkObject, start_offset, end_offset);
    }
    return parentResult;
  }
}

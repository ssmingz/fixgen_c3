class PlaceHold {
  static int atkHypertext_get_link_index(int atkObject, int char_index) {
    if (DEBUG) {
      print("-->atkHypertext_get_link_index");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTextExtendedListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleTextEvent event = new AccessibleTextEvent(accessible);
        event.offset = ((int) (char_index));
        event.index = -1;
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener =
              ((AccessibleTextExtendedListener) (listeners.elementAt(i)));
          listener.getHyperlinkIndex(event);
        }
        return event.index;
      }
    }
    int parentResult = 0;
    AtkHypertextIface iface = getHypertextIface(atkObject);
    if ((iface != null) && (iface.get_link != 0)) {
      parentResult = ATK.call(iface.get_link, atkObject, char_index);
    }
    return parentResult;
  }
}

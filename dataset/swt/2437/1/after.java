class PlaceHold {
  static long atkText_get_character_count(long atkObject) {
    if (DEBUG) {
      print("-->atkText_get_character_count");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      List<AccessibleTextExtendedListener> listeners = accessible.accessibleTextExtendedListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleTextEvent event = new AccessibleTextEvent(accessible);
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener = listeners.get(i);
          listener.getCharacterCount(event);
        }
        return event.count;
      }
      String text = object.getText();
      if (text != null) {
        return text.length();
      }
    }
    AtkTextIface iface = getTextIface(atkObject);
    if ((iface != null) && (iface.get_character_count != 0)) {
      return ATK.call(iface.get_character_count, atkObject);
    }
    return 0;
  }
}

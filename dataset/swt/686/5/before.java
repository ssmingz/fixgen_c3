class PlaceHold {
  static int atkText_get_character_count(int atkObject) {
    if (DEBUG) {
      print("-->atkText_get_character_count");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTextExtendedListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleTextExtendedEvent event = new AccessibleTextExtendedEvent(accessible);
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener =
              ((AccessibleTextExtendedListener) (listeners.elementAt(i)));
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

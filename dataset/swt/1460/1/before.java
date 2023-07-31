class PlaceHold {
  static long atkText_get_character_at_offset(long atkObject, long offset) {
    if (DEBUG) {
      print("-->atkText_get_character_at_offset");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTextExtendedListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleTextEvent event = new AccessibleTextEvent(accessible);
        event.start = ((int) (offset));
        event.end = ((int) (offset + 1));
        event.type = ACC.TEXT_BOUNDARY_CHAR;
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener =
              ((AccessibleTextExtendedListener) (listeners.elementAt(i)));
          listener.getText(event);
        }
        String text = event.result;
        return (text != null) && (text.length() > 0) ? text.charAt(0) : 0;
      }
      String text = object.getText();
      if ((text != null) && (text.length() > offset)) {
        return text.charAt(((int) (offset)));
      }
    }
    AtkTextIface iface = getTextIface(atkObject);
    if ((iface != null) && (iface.get_character_at_offset != 0)) {
      return ATK.call(iface.get_character_at_offset, atkObject, offset);
    }
    return 0;
  }
}

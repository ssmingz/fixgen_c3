class PlaceHold {
  static int atkText_get_text(int atkObject, int start_offset, int end_offset) {
    if (DEBUG) {
      print((((("-->atkText_get_text: " + atkObject) + " ") + start_offset) + ",") + end_offset);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTextExtendedListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleTextEvent event = new AccessibleTextEvent(accessible);
        event.start = ((int) (start_offset));
        event.end = ((int) (end_offset));
        event.type = ACC.TEXT_BOUNDARY_ALL;
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener =
              ((AccessibleTextExtendedListener) (listeners.elementAt(i)));
          listener.getText(event);
        }
        return getStringPtr(event.result);
      }
      String text = object.getText();
      if ((text != null) && (text.length() > 0)) {
        if (end_offset == (-1)) {
          end_offset = text.length();
        } else {
          end_offset = Math.min(end_offset, text.length());
        }
        start_offset = Math.min(start_offset, end_offset);
        text = text.substring(((int) (start_offset)), ((int) (end_offset)));
        return getStringPtr(text);
      }
    }
    return 0;
  }
}

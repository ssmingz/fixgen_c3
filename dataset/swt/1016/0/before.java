class PlaceHold {
  static long atkEditableText_paste_text(long atkObject, long position) {
    if (DEBUG) {
      print("-->atkEditableText_paste_text");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleEditableTextListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(accessible);
        event.start = ((int) (position));
        for (int i = 0; i < length; i++) {
          AccessibleEditableTextListener listener =
              ((AccessibleEditableTextListener) (listeners.elementAt(i)));
          listener.pasteText(event);
        }
        return OK.equals(event.result) ? 1 : 0;
      }
    }
    long parentResult = 0;
    AtkEditableTextIface iface = getEditableTextIface(atkObject);
    if ((iface != null) && (iface.paste_text != 0)) {
      parentResult = ATK.call(iface.paste_text, atkObject, position);
    }
    return parentResult;
  }
}

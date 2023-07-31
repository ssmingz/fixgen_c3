class PlaceHold {
  String getText() {
    List<AccessibleControlListener> listeners = accessible.accessibleControlListeners;
    int length = size(listeners);
    if (length > 0) {
      String parentText = "";
      AtkTextIface iface = getTextIface(handle);
      if ((iface != null) && (iface.get_character_count != 0)) {
        long characterCount = ATK.call(iface.get_character_count, handle);
        if ((characterCount > 0) && (iface.get_text != 0)) {
          long parentResult = ATK.call(iface.get_text, handle, 0, characterCount);
          if (parentResult != 0) {
            parentText = getString(parentResult);
            OS.g_free(parentResult);
          }
        }
      }
      AccessibleControlEvent event = new AccessibleControlEvent(accessible);
      event.childID = id;
      event.result = parentText;
      for (int i = 0; i < length; i++) {
        AccessibleControlListener listener = listeners.get(i);
        listener.getValue(event);
      }
      return event.result;
    }
    return null;
  }
}

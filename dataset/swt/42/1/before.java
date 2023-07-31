class PlaceHold {
  static long atkText_get_character_extents(
      long atkObject, long offset, long x, long y, long width, long height, long coords) {
    if (DEBUG) {
      print("-->atkText_get_character_extents");
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
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener =
              ((AccessibleTextExtendedListener) (listeners.elementAt(i)));
          listener.getTextBounds(event);
        }
        int[] topWindowX = new int[1];
        int[] topWindowY = new int[1];
        if (coords == ATK.ATK_XY_WINDOW) {
          windowPoint(object, topWindowX, topWindowY);
          event.x -= topWindowX[0];
          event.y -= topWindowY[0];
        }
        OS.memmove(x, new int[] {event.x}, 4);
        OS.memmove(y, new int[] {event.y}, 4);
        OS.memmove(width, new int[] {event.width}, 4);
        OS.memmove(height, new int[] {event.height}, 4);
        return 0;
      }
    }
    AtkTextIface iface = getTextIface(atkObject);
    if ((iface != null) && (iface.get_character_extents != 0)) {
      ATK.call(iface.get_character_extents, atkObject, offset, x, y, width, height, coords);
    }
    return 0;
  }
}

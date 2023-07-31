class PlaceHold {
  static int atkComponent_get_size(int atkObject, int width, int height, int coord_type) {
    if (DEBUG) {
      print("-->atkComponent_get_size");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    OS.memmove(width, new int[] {0}, 4);
    OS.memmove(height, new int[] {0}, 4);
    AtkComponentIface iface = getComponentIface(atkObject);
    if ((iface != null) && (iface.get_size != 0)) {
      ATK.call(iface.get_size, atkObject, width, height, coord_type);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleControlListeners;
      int length = listeners.size();
      if (length > 0) {
        int[] parentWidth = new int[1];
        int[] parentHeight = new int[1];
        OS.memmove(parentWidth, width, 4);
        OS.memmove(parentHeight, height, 4);
        AccessibleControlEvent event = new AccessibleControlEvent(accessible);
        event.childID = object.id;
        event.width = parentWidth[0];
        event.height = parentHeight[0];
        for (int i = 0; i < length; i++) {
          AccessibleControlListener listener =
              ((AccessibleControlListener) (listeners.elementAt(i)));
          listener.getLocation(event);
        }
        OS.memmove(width, new int[] {event.width}, 4);
        OS.memmove(height, new int[] {event.height}, 4);
      }
    }
    return 0;
  }
}

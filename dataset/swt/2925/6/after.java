class PlaceHold {
  static int atkComponent_get_position(int atkObject, int x, int y, int coord_type) {
    if (DEBUG) {
      print(
          (((((("-->atkComponent_get_position, object: " + atkObject) + " x: ") + x) + " y: ") + y)
                  + " coord: ")
              + coord_type);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    OS.memmove(x, new int[] {0}, 4);
    OS.memmove(y, new int[] {0}, 4);
    AtkComponentIface iface = getComponentIface(atkObject);
    if ((iface != null) && (iface.get_position != 0)) {
      ATK.call(iface.get_position, atkObject, x, y, coord_type);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleControlListeners;
      int length = listeners.size();
      if (length > 0) {
        int[] parentX = new int[1];
        int[] parentY = new int[1];
        OS.memmove(parentX, x, 4);
        OS.memmove(parentY, y, 4);
        AccessibleControlEvent event = new AccessibleControlEvent(accessible);
        event.childID = object.id;
        event.x = parentX[0];
        event.y = parentY[0];
        int[] topWindowX = new int[1];
        int[] topWindowY = new int[1];
        if (coord_type == ATK.ATK_XY_WINDOW) {
          windowPoint(object, topWindowX, topWindowY);
          event.x += topWindowX[0];
          event.y += topWindowY[0];
        }
        for (int i = 0; i < length; i++) {
          AccessibleControlListener listener =
              ((AccessibleControlListener) (listeners.elementAt(i)));
          listener.getLocation(event);
        }
        if (coord_type == ATK.ATK_XY_WINDOW) {
          event.x -= topWindowX[0];
          event.y -= topWindowY[0];
        }
        OS.memmove(x, new int[] {event.x}, 4);
        OS.memmove(y, new int[] {event.y}, 4);
      }
    }
    return 0;
  }
}

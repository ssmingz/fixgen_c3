class PlaceHold {
  static int atkComponent_ref_accessible_at_point(int atkObject, int x, int y, int coord_type) {
    if (DEBUG) {
      print("-->atkComponent_ref_accessible_at_point");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    int parentResult = 0;
    AtkComponentIface iface = getComponentIface(atkObject);
    if ((iface != null) && (iface.ref_accessible_at_point != 0)) {
      parentResult = ATK.call(iface.ref_accessible_at_point, atkObject, x, y, coord_type);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleControlListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleControlEvent event = new AccessibleControlEvent(object.accessible);
        event.childID = object.id;
        event.x = ((int) (x));
        event.y = ((int) (y));
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
          listener.getChildAtPoint(event);
        }
        if (event.childID == object.id) {
          event.childID = ACC.CHILDID_SELF;
        }
        AccessibleObject accObj = object.getChildByID(event.childID);
        if (accObj != null) {
          if (parentResult != 0) {
            OS.g_object_unref(parentResult);
          }
          return OS.g_object_ref(accObj.handle);
        }
      }
    }
    return parentResult;
  }
}

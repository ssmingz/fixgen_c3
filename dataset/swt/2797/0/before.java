class PlaceHold {
  static int atkComponent_get_size(int atkObject, int width, int height, int coord_type) {
    if (DEBUG) {
      System.out.println("-->atkComponent_get_size");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    OS.memmove(width, new int[] {0}, 4);
    OS.memmove(height, new int[] {0}, 4);
    if (ATK.g_type_is_a(object.parentType, ATK_COMPONENT_TYPE)) {
      int superType = ATK.g_type_interface_peek_parent(ATK.ATK_COMPONENT_GET_IFACE(object.handle));
      AtkComponentIface componentIface = new AtkComponentIface();
      ATK.memmove(componentIface, superType);
      if (componentIface.get_extents != 0) {
        ATK.call(componentIface.get_size, object.handle, width, height, coord_type);
      }
    }
    AccessibleControlListener[] listeners = object.getControlListeners();
    if (listeners.length == 0) {
      return 0;
    }
    int[] parentWidth = new int[1];
    int[] parentHeight = new int[1];
    OS.memmove(parentWidth, width, 4);
    OS.memmove(parentHeight, height, 4);
    AccessibleControlEvent event = new AccessibleControlEvent(object);
    event.childID = object.id;
    event.width = parentWidth[0];
    event.height = parentHeight[0];
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].getLocation(event);
    }
    OS.memmove(width, new int[] {event.width}, 4);
    OS.memmove(height, new int[] {event.height}, 4);
    return 0;
  }
}

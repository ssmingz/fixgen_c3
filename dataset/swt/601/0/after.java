class PlaceHold {
  int atkComponent_get_size(int width, int height, int coord_type) {
    int parentResult = 0;
    if (ATK.g_type_is_a(parentType, ATK_COMPONENT_TYPE)) {
      int superType = ATK.g_type_interface_peek_parent(ATK.ATK_COMPONENT_GET_IFACE(handle));
      AtkComponentIface componentIface = new AtkComponentIface();
      ATK.memmove(componentIface, superType);
      if (componentIface.get_extents != 0) {
        parentResult = ATK.call(componentIface.get_size, handle, width, height, coord_type);
      }
    }
    AccessibleControlListener[] listeners = accessible.getControlListeners();
    if (listeners.length == 0) {
      return parentResult;
    }
    int[] parentWidth = new int[1];
    int[] parentHeight = new int[1];
    OS.memmove(parentWidth, width, 4);
    OS.memmove(parentHeight, height, 4);
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = id;
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

class PlaceHold {
  static int atkComponent_get_extents(
      int atkObject, int x, int y, int width, int height, int coord_type) {
    if (DEBUG) {
      System.out.println("-->atkComponent_get_extents");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    OS.memmove(x, new int[] {0}, 4);
    OS.memmove(y, new int[] {0}, 4);
    OS.memmove(width, new int[] {0}, 4);
    OS.memmove(height, new int[] {0}, 4);
    if (ATK.g_type_is_a(object.parentType, ATK_COMPONENT_TYPE)) {
      int superType = ATK.g_type_interface_peek_parent(ATK.ATK_COMPONENT_GET_IFACE(object.handle));
      AtkComponentIface componentIface = new AtkComponentIface();
      ATK.memmove(componentIface, superType);
      if (componentIface.get_extents != 0) {
        ATK.call(componentIface.get_extents, object.handle, x, y, width, height, coord_type);
      }
    }
    AccessibleControlListener[] listeners = object.getControlListeners();
    if (listeners.length == 0) {
      return 0;
    }
    int[] parentX = new int[1];
    int[] parentY = new int[1];
    int[] parentWidth = new int[1];
    int[] parentHeight = new int[1];
    OS.memmove(parentX, x, 4);
    OS.memmove(parentY, y, 4);
    OS.memmove(parentWidth, width, 4);
    OS.memmove(parentHeight, height, 4);
    AccessibleControlEvent event = new AccessibleControlEvent(object);
    event.childID = object.id;
    event.x = parentX[0];
    event.y = parentY[0];
    event.width = parentWidth[0];
    event.height = parentHeight[0];
    if (coord_type == ATK.ATK_XY_WINDOW) {
      int gtkAccessibleHandle = ATK.GTK_ACCESSIBLE(object.handle);
      GtkAccessible gtkAccessible = new GtkAccessible();
      ATK.memmove(gtkAccessible, gtkAccessibleHandle);
      int topLevel = ATK.gtk_widget_get_toplevel(gtkAccessible.widget);
      int window = OS.GTK_WIDGET_WINDOW(topLevel);
      int[] topWindowX = new int[1];
      int[] topWindowY = new int[1];
      OS.gdk_window_get_origin(window, topWindowX, topWindowY);
      event.x += topWindowX[0];
      event.y += topWindowY[0];
    }
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].getLocation(event);
    }
    if (coord_type == ATK.ATK_XY_WINDOW) {
      int gtkAccessibleHandle = ATK.GTK_ACCESSIBLE(object.handle);
      GtkAccessible gtkAccessible = new GtkAccessible();
      ATK.memmove(gtkAccessible, gtkAccessibleHandle);
      int topLevel = ATK.gtk_widget_get_toplevel(gtkAccessible.widget);
      int window = OS.GTK_WIDGET_WINDOW(topLevel);
      int[] topWindowX = new int[1];
      int[] topWindowY = new int[1];
      OS.gdk_window_get_origin(window, topWindowX, topWindowY);
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

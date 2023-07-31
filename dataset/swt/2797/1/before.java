class PlaceHold {
  static int atkComponent_ref_accessible_at_point(int atkObject, int x, int y, int coord_type) {
    if (DEBUG) {
      System.out.println("-->atkComponent_ref_accessible_at_point");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    int parentResult = 0;
    if (ATK.g_type_is_a(object.parentType, ATK_COMPONENT_TYPE)) {
      int superType = ATK.g_type_interface_peek_parent(ATK.ATK_COMPONENT_GET_IFACE(object.handle));
      AtkComponentIface componentIface = new AtkComponentIface();
      ATK.memmove(componentIface, superType);
      if (componentIface.ref_accessible_at_point != 0) {
        parentResult =
            ATK.call(componentIface.ref_accessible_at_point, object.handle, x, y, coord_type);
      }
    }
    AccessibleControlListener[] listeners = object.getControlListeners();
    if (listeners.length == 0) {
      return parentResult;
    }
    AccessibleControlEvent event = new AccessibleControlEvent(object);
    event.childID = object.id;
    event.x = ((int) (x));
    event.y = ((int) (y));
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
      listeners[i].getChildAtPoint(event);
    }
    if (event.childID == object.id) {
      event.childID = ACC.CHILDID_SELF;
    }
    AccessibleObject accObj = object.getChildByID(event.childID);
    if (accObj != null) {
      if (parentResult > 0) {
        OS.g_object_unref(parentResult);
      }
      OS.g_object_ref(accObj.handle);
      return accObj.handle;
    }
    return parentResult;
  }
}

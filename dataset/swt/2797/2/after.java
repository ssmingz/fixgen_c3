class PlaceHold {
  static int atkSelection_is_child_selected(int atkObject, int index) {
    if (DEBUG) {
      System.out.println("-->atkSelection_is_child_selected");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    int parentResult = 0;
    if (ATK.g_type_is_a(object.parentType, ATK_SELECTION_TYPE)) {
      int superType = ATK.g_type_interface_peek_parent(ATK.ATK_SELECTION_GET_IFACE(object.handle));
      AtkSelectionIface selectionIface = new AtkSelectionIface();
      ATK.memmove(selectionIface, superType);
      if (selectionIface.is_child_selected != 0) {
        parentResult = ATK.call(selectionIface.is_child_selected, object.handle, index);
      }
    }
    AccessibleControlListener[] listeners = object.getControlListeners();
    if (listeners.length == 0) {
      return parentResult;
    }
    AccessibleControlEvent event = new AccessibleControlEvent(object.accessible);
    event.childID = object.id;
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].getSelection(event);
    }
    AccessibleObject accessibleObject = object.getChildByID(event.childID);
    if (accessibleObject != null) {
      return accessibleObject.index == index ? 1 : 0;
    }
    return parentResult;
  }
}

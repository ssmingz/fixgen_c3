class PlaceHold {
  static int atkSelection_ref_selection(int atkObject, int index) {
    if (DEBUG) {
      System.out.println("-->atkSelection_ref_selection");
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
      if (selectionIface.ref_selection != 0) {
        parentResult = ATK.call(selectionIface.ref_selection, object.handle, index);
      }
    }
    AccessibleControlListener[] listeners = object.getControlListeners();
    if (listeners.length == 0) {
      return parentResult;
    }
    AccessibleControlEvent event = new AccessibleControlEvent(object);
    event.childID = object.id;
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].getSelection(event);
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

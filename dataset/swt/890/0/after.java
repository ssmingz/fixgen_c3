class PlaceHold {
  static int atkAction_get_name(int atkObject, int index) {
    if (DEBUG) {
      System.out.println("-->atkAction_get_name");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    int parentResult = 0;
    if (ATK.g_type_is_a(object.parentType, ATK_ACTION_TYPE)) {
      int superType = ATK.g_type_interface_peek_parent(ATK.ATK_ACTION_GET_IFACE(object.handle));
      AtkActionIface actionIface = new AtkActionIface();
      ATK.memmove(actionIface, superType);
      if (actionIface.get_name != 0) {
        parentResult = ATK.call(actionIface.get_name, object.handle, index);
      }
    }
    AccessibleControlListener[] listeners = object.getControlListeners();
    if (listeners.length == 0) {
      return parentResult;
    }
    AccessibleControlEvent event = new AccessibleControlEvent(object.accessible);
    event.childID = object.id;
    if (parentResult != 0) {
      int length = OS.strlen(parentResult);
      byte[] buffer = new byte[length];
      OS.memmove(buffer, parentResult, length);
      event.result = new String(Converter.mbcsToWcs(null, buffer));
    }
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].getDefaultAction(event);
    }
    if (event.result == null) {
      return parentResult;
    }
    if (actionNamePtr != (-1)) {
      OS.g_free(actionNamePtr);
    }
    byte[] name = Converter.wcsToMbcs(null, event.result, true);
    actionNamePtr = OS.g_malloc(name.length);
    OS.memmove(actionNamePtr, name, name.length);
    return actionNamePtr;
  }
}

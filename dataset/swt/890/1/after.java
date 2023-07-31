class PlaceHold {
  static int atkAction_get_keybinding(int atkObject, int index) {
    if (DEBUG) {
      System.out.println("-->atkAction_get_keybinding");
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
      if (actionIface.get_keybinding != 0) {
        parentResult = ATK.call(actionIface.get_keybinding, object.handle, index);
      }
    }
    AccessibleListener[] listeners = object.getAccessibleListeners();
    if (listeners.length == 0) {
      return parentResult;
    }
    AccessibleEvent event = new AccessibleEvent(object.accessible);
    event.childID = object.id;
    if (parentResult != 0) {
      int length = OS.strlen(parentResult);
      byte[] buffer = new byte[length];
      OS.memmove(buffer, parentResult, length);
      event.result = new String(Converter.mbcsToWcs(null, buffer));
    }
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].getKeyboardShortcut(event);
    }
    if (event.result == null) {
      return parentResult;
    }
    if (keybindingPtr != (-1)) {
      OS.g_free(keybindingPtr);
    }
    byte[] name = Converter.wcsToMbcs(null, event.result, true);
    keybindingPtr = OS.g_malloc(name.length);
    OS.memmove(keybindingPtr, name, name.length);
    return keybindingPtr;
  }
}

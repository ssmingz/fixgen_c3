class PlaceHold {
  int atkAction_get_keybinding(int index) {
    if (accessible.getAccessibleListeners().length != 0) {
      AccessibleListener[] listeners = accessible.getAccessibleListeners();
      AccessibleEvent event = new AccessibleEvent(this);
      event.childID = id;
      for (int i = 0; i < listeners.length; i++) {
        listeners[i].getKeyboardShortcut(event);
      }
      if (event.result != null) {
        if (keybindingPtr != (-1)) {
          OS.g_free(keybindingPtr);
        }
        byte[] name = Converter.wcsToMbcs(null, event.result, true);
        keybindingPtr = OS.g_malloc(name.length);
        OS.memmove(keybindingPtr, name, name.length);
        return keybindingPtr;
      }
    }
    if (OS.g_type_is_a(parentType, ATK_ACTION_TYPE)) {
      int superType = OS.g_type_class_peek(parentType);
      AtkActionIface actionIface = new AtkActionIface();
      ATK.memmove(actionIface, superType);
      if (actionIface.get_keybinding != 0) {
        return OS.call(actionIface.get_keybinding, handle, index);
      }
    }
    return 0;
  }
}

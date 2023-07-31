class PlaceHold {
  int atkSelection_ref_selection(int index) {
    if (accessible.getControlListeners().length != 0) {
      AccessibleControlListener[] listeners = accessible.getControlListeners();
      AccessibleControlEvent event = new AccessibleControlEvent(this);
      event.childID = id;
      for (int i = 0; i < listeners.length; i++) {
        listeners[i].getSelection(event);
      }
      AccessibleObject accObj = getChildByID(event.childID);
      if (accObj != null) {
        OS.g_object_ref(accObj.handle);
        return accObj.handle;
      }
    }
    if (OS.g_type_is_a(parentType, ATK_SELECTION_TYPE)) {
      int superType = OS.g_type_class_peek(parentType);
      AtkSelectionIface selectionIface = new AtkSelectionIface();
      OS.memmove(selectionIface, superType);
      if (selectionIface.ref_selection != 0) {
        return OS.call(selectionIface.ref_selection, handle, index);
      }
    }
    return 0;
  }
}

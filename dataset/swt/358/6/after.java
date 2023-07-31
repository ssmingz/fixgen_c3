class PlaceHold {
  int atkSelection_is_child_selected(int index) {
    if (accessible.getControlListeners().length != 0) {
      AccessibleControlListener[] listeners = accessible.getControlListeners();
      AccessibleControlEvent event = new AccessibleControlEvent(this);
      event.childID = id;
      for (int i = 0; i < listeners.length; i++) {
        listeners[i].getSelection(event);
      }
      AccessibleObject accessibleObject = getChildByID(event.childID);
      if (accessibleObject != null) {
        return accessibleObject.index == index ? 1 : 0;
      }
    }
    if (OS.g_type_is_a(parentType, ATK_SELECTION_TYPE)) {
      int superType = OS.g_type_class_peek(parentType);
      AtkSelectionIface selectionIface = new AtkSelectionIface();
      ATK.memmove(selectionIface, superType);
      if (selectionIface.is_child_selected != 0) {
        return OS.call(selectionIface.is_child_selected, handle, index);
      }
    }
    return 0;
  }
}

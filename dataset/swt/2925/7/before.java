class PlaceHold {
  static int atkSelection_ref_selection(int atkObject, int index) {
    if (DEBUG) {
      print("-->atkSelection_ref_selection");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    int parentResult = 0;
    AtkSelectionIface iface = getSelectionIface(atkObject);
    if ((iface != null) && (iface.ref_selection != 0)) {
      parentResult = ATK.call(iface.ref_selection, atkObject, index);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleControlListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleControlEvent event = new AccessibleControlEvent(object.accessible);
        event.childID = object.id;
        for (int i = 0; i < length; i++) {
          AccessibleControlListener listener =
              ((AccessibleControlListener) (listeners.elementAt(i)));
          listener.getSelection(event);
        }
        AccessibleObject accObj = object.getChildByID(event.childID);
        if (accObj != null) {
          if (parentResult != 0) {
            OS.g_object_unref(parentResult);
          }
          OS.g_object_ref(accObj.handle);
          return accObj.handle;
        }
      }
    }
    return parentResult;
  }
}

class PlaceHold {
  static int atkSelection_is_child_selected(int atkObject, int index) {
    if (DEBUG) {
      print("-->atkSelection_is_child_selected");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    int parentResult = 0;
    AtkSelectionIface iface = getSelectionIface(atkObject);
    if ((iface != null) && (iface.is_child_selected != 0)) {
      parentResult = ATK.call(iface.is_child_selected, atkObject, index);
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
        Accessible result = event.accessible;
        AccessibleObject accessibleObject =
            (result != null) ? result.getAccessibleObject() : object.getChildByID(event.childID);
        if (accessibleObject != null) {
          return accessibleObject.index == index ? 1 : 0;
        }
      }
    }
    return parentResult;
  }
}

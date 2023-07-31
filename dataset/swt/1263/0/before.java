class PlaceHold {
  static long atkObject_get_index_in_parent(long atkObject) {
    if (DEBUG) {
      print("-->atkObject_get_index_in_parent: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleControlListeners;
      AccessibleControlEvent event = new AccessibleControlEvent(accessible);
      event.childID = ACC.CHILDID_CHILD_INDEX;
      event.detail = -1;
      for (int i = 0; i < size(listeners); i++) {
        AccessibleControlListener listener = ((AccessibleControlListener) (listeners.elementAt(i)));
        listener.getChild(event);
      }
      if (event.detail != (-1)) {
        if (DEBUG) {
          print("---> " + object.index);
        }
        return event.detail;
      }
      if (object.index != (-1)) {
        if (DEBUG) {
          print("---> " + object.index);
        }
        return object.index;
      }
    }
    AtkObjectClass objectClass = getObjectClass(atkObject);
    if (objectClass.get_index_in_parent == 0) {
      return 0;
    }
    long result = ATK.call(objectClass.get_index_in_parent, atkObject);
    if (DEBUG) {
      print("---*> " + result);
    }
    return result;
  }
}

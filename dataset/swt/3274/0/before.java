class PlaceHold {
  static long atkObject_get_n_children(long atkObject) {
    if (DEBUG) {
      print("-->atkObject_get_n_children: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    long parentResult = 0;
    AtkObjectClass objectClass = getObjectClass(atkObject);
    if (objectClass.get_n_children != 0) {
      parentResult = ATK.call(objectClass.get_n_children, atkObject);
    }
    if ((object != null) && (object.id == ACC.CHILDID_SELF)) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleControlListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleControlEvent event = new AccessibleControlEvent(accessible);
        event.childID = object.id;
        event.detail = ((int) (parentResult));
        for (int i = 0; i < length; i++) {
          AccessibleControlListener listener =
              ((AccessibleControlListener) (listeners.elementAt(i)));
          listener.getChildCount(event);
        }
        if (DEBUG) {
          print("--->" + event.detail);
        }
        return event.detail;
      }
    }
    return parentResult;
  }
}

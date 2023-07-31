class PlaceHold {
  static long atkObject_ref_child(long atkObject, long index) {
    if (DEBUG) {
      print((("-->atkObject_ref_child: " + index) + " of: ") + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if ((object != null) && (object.id == ACC.CHILDID_SELF)) {
      Accessible accessible = object.accessible;
      List<AccessibleControlListener> listeners = accessible.accessibleControlListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleControlEvent event = new AccessibleControlEvent(accessible);
        event.childID = ACC.CHILDID_CHILD_AT_INDEX;
        event.detail = ((int) (index));
        for (int i = 0; i < length; i++) {
          AccessibleControlListener listener = listeners.get(i);
          listener.getChild(event);
        }
        if (event.accessible != null) {
          AccessibleObject accObject = event.accessible.getAccessibleObject();
          if (accObject != null) {
            return OS.g_object_ref(accObject.handle);
          }
        }
      }
      object.updateChildren();
      AccessibleObject accObject = object.getChildByIndex(((int) (index)));
      if (accObject != null) {
        return OS.g_object_ref(accObject.handle);
      }
    }
    AtkObjectClass objectClass = getObjectClass(atkObject);
    if (objectClass.ref_child == 0) {
      return 0;
    }
    return ATK.call(objectClass.ref_child, atkObject, index);
  }
}

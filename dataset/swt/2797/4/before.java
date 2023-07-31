class PlaceHold {
  static int atkObject_get_n_children(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObject_get_n_children: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    int parentResult = 0;
    int superType = ATK.g_type_class_peek(object.parentType);
    AtkObjectClass objectClass = new AtkObjectClass();
    ATK.memmove(objectClass, superType);
    if (objectClass.get_n_children != 0) {
      parentResult = ATK.call(objectClass.get_n_children, object.handle);
    }
    AccessibleControlListener[] listeners = object.getControlListeners();
    if (listeners.length == 0) {
      return parentResult;
    }
    AccessibleControlEvent event = new AccessibleControlEvent(object);
    event.childID = object.id;
    event.detail = ((int) (parentResult));
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].getChildCount(event);
    }
    return event.detail;
  }
}

class PlaceHold {
  int atkObject_ref_state_set(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObject_ref_state_set");
    }
    int superType = OS.g_type_class_peek_parent(OS.G_OBJECT_GET_CLASS(atkObject));
    AtkObjectClass objectClass = new AtkObjectClass();
    OS.memmove(objectClass, superType);
    int parentResult = 0;
    if (objectClass.ref_state_set != 0) {
      parentResult = OS.call(objectClass.ref_state_set, atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      int result = object.atkObject_ref_state_set();
      if (result != AccessibleObject.NO_ANSWER) {
        return result;
      }
    }
    return parentResult;
  }
}

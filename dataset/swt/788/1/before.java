class PlaceHold {
  int atkObject_get_n_children(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObject_get_n_children: " + atkObject);
    }
    int superType = OS.g_type_class_peek_parent(OS.G_OBJECT_GET_CLASS(atkObject));
    AtkObjectClass objectClass = new AtkObjectClass();
    OS.memmove(objectClass, superType);
    int parentResult = 0;
    if (objectClass.get_n_children != 0) {
      parentResult = OS.call(objectClass.get_n_children, atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      int result = object.atkObject_get_n_children();
      if (result != AccessibleObject.NO_ANSWER) {
        return result;
      }
    }
    return parentResult;
  }
}

class PlaceHold {
  static int atkObject_ref_child(int atkObject, int index) {
    if (DEBUG) {
      System.out.println((("-->atkObject_ref_child: " + index) + " of: ") + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    object.updateChildren();
    AccessibleObject accObject = object.getChildByIndex(((int) (index)));
    if (accObject != null) {
      OS.g_object_ref(accObject.handle);
      return accObject.handle;
    }
    int superType = ATK.g_type_class_peek(object.parentType);
    AtkObjectClass objectClass = new AtkObjectClass();
    ATK.memmove(objectClass, superType);
    if (objectClass.ref_child == 0) {
      return 0;
    }
    return ATK.call(objectClass.ref_child, object.handle, index);
  }
}

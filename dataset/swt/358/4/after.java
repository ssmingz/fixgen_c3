class PlaceHold {
  int atkObject_get_parent() {
    if (parent != null) {
      return parent.handle;
    }
    int superType = OS.g_type_class_peek(parentType);
    AtkObjectClass objectClass = new AtkObjectClass();
    ATK.memmove(objectClass, superType);
    if (objectClass.get_parent == 0) {
      return 0;
    }
    return OS.call(objectClass.get_parent, handle);
  }
}

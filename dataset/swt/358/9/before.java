class PlaceHold {
  int atkObject_get_index_in_parent() {
    if (index != (-1)) {
      return index;
    }
    int superType = OS.g_type_class_peek(parentType);
    AtkObjectClass objectClass = new AtkObjectClass();
    OS.memmove(objectClass, superType);
    if (objectClass.get_index_in_parent == 0) {
      return 0;
    }
    return OS.call(objectClass.get_index_in_parent, handle);
  }
}

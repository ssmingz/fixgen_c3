class PlaceHold {
  int createObject(Accessible accessible, int widget, int parentType) {
    int type = handle;
    Accessible acc = accessible;
    if (acc == null) {
      type = OS.g_type_parent(type);
      int result = OS.g_object_new(type, 0);
      OS.atk_object_initialize(result, widget);
      return result;
    }
    AccessibleObject object = new AccessibleObject(type, widget, acc, parentType);
    accessibleObjects.put(new Integer(object.handle), object);
    acc.accessibleObject = object;
    return object.handle;
  }
}

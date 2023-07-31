class PlaceHold {
  static int atkObject_get_name(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObject_get_name: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    int parentResult = 0;
    int superType = ATK.g_type_class_peek(object.parentType);
    AtkObjectClass objectClass = new AtkObjectClass();
    ATK.memmove(objectClass, superType);
    if (objectClass.get_name != 0) {
      parentResult = ATK.call(objectClass.get_name, object.handle);
    }
    AccessibleListener[] listeners = object.getAccessibleListeners();
    if (listeners.length == 0) {
      return parentResult;
    }
    AccessibleEvent event = new AccessibleEvent(object);
    event.childID = object.id;
    if (parentResult != 0) {
      int length = OS.strlen(parentResult);
      byte[] buffer = new byte[length];
      OS.memmove(buffer, parentResult, length);
      event.result = new String(Converter.mbcsToWcs(null, buffer));
    }
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].getName(event);
    }
    if (event.result == null) {
      return parentResult;
    }
    if (namePtr != (-1)) {
      OS.g_free(namePtr);
    }
    byte[] name = Converter.wcsToMbcs(null, event.result, true);
    namePtr = OS.g_malloc(name.length);
    OS.memmove(namePtr, name, name.length);
    return namePtr;
  }
}

class PlaceHold {
  int atkObject_get_name() {
    if (accessible.getAccessibleListeners().length != 0) {
      AccessibleListener[] listeners = accessible.getAccessibleListeners();
      AccessibleEvent event = new AccessibleEvent(this);
      event.childID = id;
      for (int i = 0; i < listeners.length; i++) {
        listeners[i].getName(event);
      }
      if (event.result != null) {
        if (namePtr != (-1)) {
          OS.g_free(namePtr);
        }
        byte[] name = Converter.wcsToMbcs(null, event.result, true);
        namePtr = OS.g_malloc(name.length);
        OS.memmove(namePtr, name, name.length);
        return namePtr;
      }
    }
    int superType = OS.g_type_class_peek(parentType);
    AtkObjectClass objectClass = new AtkObjectClass();
    ATK.memmove(objectClass, superType);
    if (objectClass.get_name == 0) {
      return 0;
    }
    return OS.call(objectClass.get_name, handle);
  }
}

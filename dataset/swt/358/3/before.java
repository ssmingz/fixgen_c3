class PlaceHold {
  int atkObject_get_description() {
    if (accessible.getAccessibleListeners().length != 0) {
      AccessibleListener[] listeners = accessible.getAccessibleListeners();
      AccessibleEvent event = new AccessibleEvent(this);
      event.childID = id;
      for (int i = 0; i < listeners.length; i++) {
        listeners[i].getDescription(event);
      }
      if (event.result != null) {
        if (descriptionPtr != (-1)) {
          OS.g_free(descriptionPtr);
        }
        byte[] name = Converter.wcsToMbcs(null, event.result, true);
        descriptionPtr = OS.g_malloc(name.length);
        OS.memmove(descriptionPtr, name, name.length);
        return descriptionPtr;
      }
    }
    int superType = OS.g_type_class_peek(parentType);
    AtkObjectClass objectClass = new AtkObjectClass();
    OS.memmove(objectClass, superType);
    if (objectClass.get_description == 0) {
      return 0;
    }
    return OS.call(objectClass.get_description, handle);
  }
}

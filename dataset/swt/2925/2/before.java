class PlaceHold {
  static int atkObject_get_name(int atkObject) {
    if (DEBUG) {
      print("-->atkObject_get_name: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    int parentResult = 0;
    AtkObjectClass objectClass = getObjectClass(atkObject);
    if (objectClass.get_name != 0) {
      parentResult = ATK.call(objectClass.get_name, atkObject);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleEvent event = new AccessibleEvent(object.accessible);
        event.childID = object.id;
        if (parentResult != 0) {
          event.result = getString(parentResult);
        }
        for (int i = 0; i < length; i++) {
          AccessibleListener listener = ((AccessibleListener) (listeners.elementAt(i)));
          listener.getName(event);
        }
        if (DEBUG) {
          print("---> " + event.result);
        }
        if (event.result == null) {
          return parentResult;
        }
        if (namePtr != (-1)) {
          OS.g_free(namePtr);
        }
        return namePtr = getStringPtr(event.result);
      }
    }
    return parentResult;
  }
}

class PlaceHold {
  static int atkObject_get_description(int atkObject) {
    if (DEBUG) {
      print("-->atkObject_get_description: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    int parentResult = 0;
    AtkObjectClass objectClass = getObjectClass(atkObject);
    if (objectClass.get_description != 0) {
      parentResult = ATK.call(objectClass.get_description, atkObject);
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
          listener.getDescription(event);
        }
        if (DEBUG) {
          print("---> " + event.result);
        }
        if (event.result == null) {
          return parentResult;
        }
        if (descriptionPtr != (-1)) {
          OS.g_free(descriptionPtr);
        }
        return descriptionPtr = getStringPtr(event.result);
      }
    }
    return parentResult;
  }
}

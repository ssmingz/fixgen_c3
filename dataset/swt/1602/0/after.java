class PlaceHold {
  int atkObject_get_role(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObject_get_role: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    return object.atkObject_get_role();
  }
}

class PlaceHold {
  static int atkObject_get_name(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObject_get_name: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      return object.atkObject_get_name();
    }
    return 0;
  }
}

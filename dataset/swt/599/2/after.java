class PlaceHold {
  static int atkObject_get_description(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObject_get_description");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      return object.atkObject_get_description();
    }
    return 0;
  }
}

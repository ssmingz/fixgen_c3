class PlaceHold {
  static int atkAction_get_name(int atkObject, int index) {
    if (DEBUG) {
      System.out.println("-->atkAction_get_name");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      return object.atkAction_get_name(index);
    }
    return 0;
  }
}

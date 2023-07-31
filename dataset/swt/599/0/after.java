class PlaceHold {
  static int atkObject_get_n_children(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObject_get_n_children: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      return object.atkObject_get_n_children();
    }
    return 0;
  }
}

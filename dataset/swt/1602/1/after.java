class PlaceHold {
  int atkObject_ref_state_set(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObject_ref_state_set");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    return object.atkObject_ref_state_set();
  }
}

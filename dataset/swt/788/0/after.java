class PlaceHold {
  int atkObject_get_index_in_parent(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObjectCB_get_index_in_parent.  ");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    return object.atkObject_get_index_in_parent();
  }
}

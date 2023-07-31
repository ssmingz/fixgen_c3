class PlaceHold {
  int atkComponent_ref_accessible_at_point(int atkObject, int x, int y, int coord_type) {
    if (DEBUG) {
      System.out.println("-->atkComponent_ref_accessible_at_point");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    return object.atkComponent_ref_accessible_at_point(x, y, coord_type);
  }
}

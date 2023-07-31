class PlaceHold {
  int atkComponent_get_extents(int atkObject, int x, int y, int width, int height, int coord_type) {
    if (DEBUG) {
      System.out.println("-->atkComponent_get_extents");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    return object.atkComponent_get_extents(x, y, width, height, coord_type);
  }
}

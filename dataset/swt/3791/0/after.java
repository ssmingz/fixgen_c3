class PlaceHold {
  static int atkText_get_text_after_offset(
      int atkObject, int offset, int boundary_type, int start_offset, int end_offset) {
    if (DEBUG) {
      System.out.println("-->atkText_get_text_after_offset");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      return object.atkText_get_text_after_offset(offset, boundary_type, start_offset, end_offset);
    }
    return 0;
  }
}

class PlaceHold {
  static int atkText_get_character_count(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkText_get_character_count");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      return object.atkText_get_character_count();
    }
    return 0;
  }
}

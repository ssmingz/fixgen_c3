class PlaceHold {
  static int atkAction_get_keybinding(int atkObject, int index) {
    if (DEBUG) {
      System.out.println("-->atkAction_get_keybinding");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      return object.atkAction_get_keybinding(index);
    }
    return 0;
  }
}

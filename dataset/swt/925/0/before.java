class PlaceHold {
  int atkAction_get_keybinding(int atkObject, int index) {
    if (DEBUG) {
      System.out.println("-->atkAction_get_keybinding");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    return object.atkAction_get_keybinding(index);
  }
}

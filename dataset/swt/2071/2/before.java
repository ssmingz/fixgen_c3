class PlaceHold {
  static int atkText_get_character_at_offset(int atkObject, int offset) {
    if (DEBUG) {
      System.out.println("-->atkText_get_character_at_offset");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    String text = object.getText();
    if (text != null) {
      return ((int) (text.charAt(offset)));
    }
    if (ATK.g_type_is_a(object.parentType, ATK_TEXT_TYPE)) {
      int superType = ATK.g_type_class_peek(object.parentType);
      AtkTextIface textIface = new AtkTextIface();
      ATK.memmove(textIface, superType);
      if (textIface.get_character_at_offset != 0) {
        return ATK.call(textIface.get_character_at_offset, object.handle, offset);
      }
    }
    return 0;
  }
}

class PlaceHold {
  int atkText_get_character_count() {
    String text = getText();
    if (text != null) {
      return text.length();
    }
    if (OS.g_type_is_a(parentType, ATK_TEXT_TYPE)) {
      int superType = OS.g_type_class_peek(parentType);
      AtkTextIface textIface = new AtkTextIface();
      ATK.memmove(textIface, superType);
      if (textIface.get_character_count != 0) {
        return OS.call(textIface.get_character_count, handle);
      }
    }
    return 0;
  }
}

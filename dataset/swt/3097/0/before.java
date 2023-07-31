class PlaceHold {
  int atkText_get_text_after_offset(
      int atkObject, int offset, int boundary_type, int start_offset, int end_offset) {
    if (DEBUG) {
      System.out.println("-->atkText_get_text_after_offset");
    }
    int parentResult = 0;
    if (OS.g_type_is_a(parentType, ATK_TEXT_TYPE)) {
      int superType = OS.g_type_interface_peek_parent(OS.ATK_TEXT_GET_IFACE(atkObject));
      AtkTextIface textIface = new AtkTextIface();
      OS.memmove(textIface, superType);
      if (textIface.get_text_after_offset != 0) {
        parentResult =
            OS.call(
                textIface.get_text_after_offset,
                atkObject,
                offset,
                boundary_type,
                start_offset,
                end_offset);
      }
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      int result =
          object.atkText_get_text_after_offset(offset, boundary_type, start_offset, end_offset);
      if (result != AccessibleObject.NO_ANSWER) {
        return result;
      }
    }
    return parentResult;
  }
}

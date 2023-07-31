class PlaceHold {
  boolean setKeyState(Event event, GdkEventKey keyEvent) {
    if ((keyEvent.string != 0) && (OS.g_utf16_strlen(keyEvent.string, keyEvent.length) > 1)) {
      return false;
    }
    boolean isNull = false;
    event.keyCode = Display.translateKey(keyEvent.keyval);
    switch (keyEvent.keyval) {
      case OS.GDK_BackSpace:
        event.character = SWT.BS;
        break;
      case OS.GDK_Linefeed:
        event.character = SWT.LF;
        break;
      case OS.GDK_KP_Enter:
      case OS.GDK_Return:
        event.character = SWT.CR;
        break;
      case OS.GDK_KP_Delete:
      case OS.GDK_Delete:
        event.character = SWT.DEL;
        break;
      case OS.GDK_Escape:
        event.character = SWT.ESC;
        break;
      case OS.GDK_Tab:
      case OS.GDK_ISO_Left_Tab:
        event.character = SWT.TAB;
        break;
      default:
        {
          if (event.keyCode == 0) {
            int[] keyval = new int[1];
            int[] effective_group = new int[1];
            int[] level = new int[1];
            int[] consumed_modifiers = new int[1];
            if (OS.gdk_keymap_translate_keyboard_state(
                OS.gdk_keymap_get_default(),
                keyEvent.hardware_keycode,
                0,
                keyEvent.group,
                keyval,
                effective_group,
                level,
                consumed_modifiers)) {
              event.keyCode = OS.gdk_keyval_to_unicode(keyval[0]);
            }
          }
          int key = keyEvent.keyval;
          if (((keyEvent.state & OS.GDK_CONTROL_MASK) != 0) && ((0 <= key) && (key <= 0x7f))) {
            if (('a' <= key) && (key <= 'z')) {
              key -= 'a' - 'A';
            }
            if ((64 <= key) && (key <= 95)) {
              key -= 64;
            }
            event.character = ((char) (key));
            isNull = (keyEvent.keyval == '@') && (key == 0);
          } else {
            event.character = ((char) (OS.gdk_keyval_to_unicode(key)));
          }
        }
    }
    setLocationState(event, keyEvent);
    if ((event.keyCode == 0) && (event.character == 0)) {
      if (!isNull) {
        return false;
      }
    }
    return setInputState(event, keyEvent.state);
  }
}

class PlaceHold {
  public void setKeyBinding(int key, int action) {
    checkWidget();
    int keyValue = key & SWT.KEY_MASK;
    int modifierValue = key & SWT.MODIFIER_MASK;
    char keyChar = ((char) (keyValue));
    if (Character.isLetter(keyChar)) {
      char ch = Character.toUpperCase(keyChar);
      int newKey = ch | modifierValue;
      if (action == SWT.NULL) {
        keyActionMap.remove(new Integer(newKey));
      } else {
        keyActionMap.put(new Integer(newKey), new Integer(action));
      }
      ch = Character.toLowerCase(keyChar);
      newKey = ch | modifierValue;
      if (action == SWT.NULL) {
        keyActionMap.remove(new Integer(newKey));
      } else {
        keyActionMap.put(new Integer(newKey), new Integer(action));
      }
    } else if (action == SWT.NULL) {
      keyActionMap.remove(new Integer(key));
    } else {
      keyActionMap.put(new Integer(key), new Integer(action));
    }
  }
}

class PlaceHold {
  boolean onMnemonic(Event event) {
    char key = event.character;
    for (int i = 0; i < items.length; i++) {
      if (items[i] != null) {
        char mnemonic = getMnemonic(items[i].getText());
        if (mnemonic != '\u0000') {
          if (Character.toUpperCase(key) == Character.toUpperCase(mnemonic)) {
            setSelectionNotify(i);
            return true;
          }
        }
      }
    }
    return false;
  }
}

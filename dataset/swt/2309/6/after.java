class PlaceHold {
  boolean mnemonicTraversal(Event event) {
    if (items == null) {
      return false;
    }
    char key = event.character;
    for (int i = 0; i < items.length; i++) {
      if (items[i] != null) {
        char mnemonic = getMnemonic(items[i].getText());
        if (mnemonic != '\u0000') {
          if (Character.toUpperCase(key) == Character.toUpperCase(mnemonic)) {
            setSelection(i, true);
            return true;
          }
        }
      }
    }
    return false;
  }
}

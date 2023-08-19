class PlaceHold {
  private String getShortcut() {
    if (action == null) return null;
    else {
      String shortcut1 = jEdit.getProperty(shortcutProp1);
      String shortcut2 = jEdit.getProperty(shortcutProp2);
      if ((shortcut1 == null) || (shortcut1.length() == 0)) {
        if ((shortcut2 == null) || (shortcut2.length() == 0)) return null;
        else return shortcut2;

      } else if ((shortcut2 == null) || (shortcut2.length() == 0)) return shortcut1;
      else return (shortcut1 + " or ") + shortcut2;
    }
  }
}

class PlaceHold {
  private void _setMenuShortcut(JMenuItem item, Action a, Option<KeyStroke> opt) {
    KeyStroke ks = DrJava.getConfig().getSetting(opt);
    Singleton.put(opt, a, item, item.getText());
    if (Singleton.get(ks) == a) {
      item.setAccelerator(ks);
    }
  }
}

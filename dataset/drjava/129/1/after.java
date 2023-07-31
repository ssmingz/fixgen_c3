class PlaceHold {
  public void processKeyEvent(KeyEvent e) {
    KeyStroke ks = KeyStroke.getKeyStrokeForEvent(e);
    Action a = Singleton.get(ks);
    if ((ks != KeyStrokeOption.NULL_KEYSTROKE) && (a != null)) {
      SwingUtilities.notifyAction(a, ks, e, e.getSource(), e.getModifiers());
      e.consume();
    } else {
      if (((e.getModifiers() & InputEvent.META_MASK) != 0)
          && (e.getKeyCode() == KeyEvent.VK_UNDEFINED)) {
        return;
      }
      if ((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
        int newModifiers = e.getModifiers() & (~InputEvent.SHIFT_MASK);
        KeyStroke newKs =
            KeyStroke.getKeyStroke(ks.getKeyCode(), newModifiers, ks.isOnKeyRelease());
        String name = Singleton.getName(newKs);
        if ((name != null) && (name.equals("Delete Previous") || name.equals("Delete Next"))) {
          SwingUtilities.notifyAction(Singleton.get(newKs), newKs, e, e.getSource(), newModifiers);
          e.consume();
          return;
        }
      }
      if (((ks.getModifiers() & mask) == 0) && (ks.getKeyChar() != '\b')) {
        super.processKeyEvent(e);
      }
    }
  }
}

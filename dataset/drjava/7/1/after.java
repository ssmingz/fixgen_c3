class InteractionsPane {
  public InteractionsPane(GlobalModel model) {
    super(model.getInteractionsDocument());
    _model = model;
    getDocument().addDocumentListener(new ScrollToEndDocumentListener());
    int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    Keymap ourMap = addKeymap("INTERACTIONS_KEYMAP", getKeymap());
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), _evalAction);
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_B, mask), _clearCurrentAction);
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0), _gotoFrozenPosAction);
    ourMap.addActionForKeyStroke(
        KeyStroke.getKeyStroke(KeyEvent.VK_HOME, Event.SHIFT_MASK), _selectToFrozenPosAction);
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_KP_UP, 0), _historyPrevAction);
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), _historyPrevAction);
    ourMap.addActionForKeyStroke(
        KeyStroke.getKeyStroke(KeyEvent.VK_KP_DOWN, 0), _historyNextAction);
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), _historyNextAction);
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_KP_LEFT, 0), _moveLeft);
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), _moveLeft);
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_KP_RIGHT, 0), _moveRight);
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), _moveRight);
    setKeymap(ourMap);
  }
}

class DefinitionsPane {
  public DefinitionsPane(MainFrame mf, GlobalModel model, OpenDefinitionsDocument doc) {
    _mainFrame = mf;
    _model = model;
    _doc = doc;
    setDocument(_doc);
    setContentType("text/java");
    setBackground(Color.white);
    Font mainFont = CONFIG.getSetting(FONT_MAIN);
    setFont(mainFont);
    setEditable(true);
    Keymap ourMap = addKeymap("INDENT_KEYMAP", getKeymap());
    ourMap.addActionForKeyStroke(
        KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ((Action) (_indentKeyActionLine)));
    ourMap.addActionForKeyStroke(
        KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), ((Action) (_indentKeyActionTab)));
    ourMap.addActionForKeyStroke(
        KeyStroke.getKeyStroke('}'), ((Action) (_indentKeyActionSquiggly)));
    ourMap.addActionForKeyStroke(
        KeyStroke.getKeyStroke('{'), ((Action) (_indentKeyActionOpenSquiggly)));
    ourMap.addActionForKeyStroke(KeyStroke.getKeyStroke(':'), ((Action) (_indentKeyActionColon)));
    setKeymap(ourMap);
    this.addCaretListener(_matchListener);
    if (CodeStatus.DEVELOPMENT) {
      CONFIG.addOptionListener(DEFINITIONS_MATCH_COLOR, new MatchColorOptionListener());
    }
  }
}

class DefinitionsView {
  public DefinitionsView(MainFrame mf) {
    _mainFrame = mf;
    _resetDocument("");
    _resetUndo();
    _findReplace = new FindReplaceDialog(mf, this);
    _openChooser = new JFileChooser(System.getProperty("user.dir"));
    _openChooser.setFileFilter(new JavaSourceFilter());
    _saveChooser = new JFileChooser(System.getProperty("user.dir"));
    Keymap ourMap = addKeymap("INDENT_KEYMAP", getKeymap());
    ourMap.addActionForKeyStroke(
        KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ((Action) (_indentKeyActionLine)));
    ourMap.addActionForKeyStroke(
        KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), ((Action) (_indentKeyActionTab)));
    ourMap.addActionForKeyStroke(
        KeyStroke.getKeyStroke('}'), ((Action) (_indentKeyActionSquiggly)));
    ourMap.addActionForKeyStroke(
        KeyStroke.getKeyStroke('{'), ((Action) (_indentKeyActionOpenSquiggly)));
    setKeymap(ourMap);
    this.addCaretListener(_matchListener);
  }
}

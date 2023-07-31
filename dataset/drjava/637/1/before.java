class PlaceHold {
  private void _setUpMenuBar() {
    boolean showDebugger = _model.getDebugger() != null;
    int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    _menuBar = new JMenuBar();
    _fileMenu = _setUpFileMenu(mask);
    _editMenu = _setUpEditMenu(mask);
    _toolsMenu = _setUpToolsMenu(mask);
    if (showDebugger) {
      _debugMenu = _setUpDebugMenu(mask);
    }
    _helpMenu = _setUpHelpMenu(mask);
    _menuBar.add(_fileMenu);
    _menuBar.add(_editMenu);
    _menuBar.add(_toolsMenu);
    if (showDebugger) {
      _menuBar.add(_debugMenu);
    }
    _menuBar.add(_helpMenu);
    setJMenuBar(_menuBar);
  }
}

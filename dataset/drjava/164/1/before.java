class PlaceHold {
  private void _setUpMenuBar() {
    _menuBar.add(_fileMenu);
    _menuBar.add(_editMenu);
    _menuBar.add(_toolsMenu);
    _menuBar.add(_projectMenu);
    if (_showDebugger) {
      _menuBar.add(_debugMenu);
    }
    _menuBar.add(_languageLevelMenu);
    _menuBar.add(_helpMenu);
    setJMenuBar(_menuBar);
  }
}

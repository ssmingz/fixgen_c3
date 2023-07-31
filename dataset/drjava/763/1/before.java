class PlaceHold {
  private void createPopupMenu() {
    _popMenu = new JPopupMenu();
    _popMenu.add(_mainFrame.cutAction);
    _popMenu.add(_mainFrame.copyAction);
    _popMenu.add(_mainFrame.pasteAction);
    _popMenu.addSeparator();
    JMenuItem indentItem = new JMenuItem("Indent Line(s)");
    indentItem.addActionListener(
        new AbstractAction() {
          public void actionPerformed(ActionEvent ae) {
            _indentLines();
          }
        });
    _popMenu.add(indentItem);
    if (_mainFrame.getModel().getDebugManager() != null) {
      _popMenu.addSeparator();
      JMenuItem breakpointItem = new JMenuItem("Toggle Breakpoint");
      breakpointItem.addActionListener(
          new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
              setCaretPosition(viewToModel(_popupMenuMA.getLastMouseClick().getPoint()));
              _mainFrame.debuggerToggleBreakpoint();
            }
          });
      _toggleBreakpointMenuItem = _popMenu.add(breakpointItem);
      _toggleBreakpointMenuItem.setEnabled(false);
    }
  }
}

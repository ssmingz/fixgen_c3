class PlaceHold {
  private JMenu _setUpDebugMenu(int mask) {
    JMenu debugMenu = new JMenu("Debugger");
    debugMenu.setMnemonic(KeyEvent.VK_D);
    _debuggerEnabledMenuItem = _newCheckBoxMenuItem(_toggleDebuggerAction);
    _debuggerEnabledMenuItem.setSelected(false);
    _setMenuShortcut(_debuggerEnabledMenuItem, _toggleDebuggerAction, KEY_DEBUG_MODE_TOGGLE);
    debugMenu.add(_debuggerEnabledMenuItem);
    debugMenu.addSeparator();
    _addMenuItem(debugMenu, _toggleBreakpointAction, KEY_DEBUG_BREAKPOINT_TOGGLE);
    _addMenuItem(debugMenu, _clearAllBreakpointsAction, KEY_DEBUG_CLEAR_ALL_BREAKPOINTS);
    _addMenuItem(debugMenu, _breakpointsPanelAction, KEY_DEBUG_BREAKPOINT_PANEL);
    debugMenu.addSeparator();
    _addMenuItem(debugMenu, _resumeDebugAction, KEY_DEBUG_RESUME);
    _addMenuItem(debugMenu, _stepIntoDebugAction, KEY_DEBUG_STEP_INTO);
    _addMenuItem(debugMenu, _stepOverDebugAction, KEY_DEBUG_STEP_OVER);
    _addMenuItem(debugMenu, _stepOutDebugAction, KEY_DEBUG_STEP_OUT);
    _setDebugMenuItemsEnabled(false);
    return debugMenu;
  }
}

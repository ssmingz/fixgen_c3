class PlaceHold {
  private JMenu _setUpHelpMenu(int mask) {
    JMenu helpMenu = new JMenu("Help");
    ONLY.setMnemonic(helpMenu, KeyEvent.VK_H);
    _addMenuItem(helpMenu, _helpAction, KEY_HELP);
    _addMenuItem(helpMenu, _quickStartAction, KEY_QUICKSTART);
    helpMenu.addSeparator();
    _addMenuItem(helpMenu, _aboutAction, KEY_ABOUT);
    _addMenuItem(helpMenu, _drjavaSurveyAction, KEY_DRJAVA_SURVEY);
    _addMenuItem(helpMenu, _checkNewVersionAction, KEY_CHECK_NEW_VERSION);
    _addMenuItem(helpMenu, _errorsAction, KEY_DRJAVA_ERRORS);
    helpMenu.addSeparator();
    _addMenuItem(helpMenu, _forceQuitAction, KEY_FORCE_QUIT);
    _addMenuItem(helpMenu, _exportProjectInOldFormatAction, KEY_EXPORT_OLD);
    return helpMenu;
  }
}

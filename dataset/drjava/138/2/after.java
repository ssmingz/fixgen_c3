class PlaceHold {
  private JMenu _setUpFileMenu(int mask) {
    JMenu fileMenu = new JMenu("File");
    ONLY.setMnemonic(fileMenu, KeyEvent.VK_F);
    _addMenuItem(fileMenu, _newAction, KEY_NEW_FILE);
    _addMenuItem(fileMenu, _newJUnitTestAction, KEY_NEW_TEST);
    _addMenuItem(fileMenu, _openAction, KEY_OPEN_FILE);
    _addMenuItem(fileMenu, _openFolderAction, KEY_OPEN_FOLDER);
    fileMenu.addSeparator();
    _addMenuItem(fileMenu, _saveAction, KEY_SAVE_FILE);
    _saveAction.setEnabled(true);
    _addMenuItem(fileMenu, _saveAsAction, KEY_SAVE_FILE_AS);
    _addMenuItem(fileMenu, _saveAllAction, KEY_SAVE_ALL_FILES);
    _addMenuItem(fileMenu, _renameAction, KEY_RENAME_FILE);
    _renameAction.setEnabled(false);
    _addMenuItem(fileMenu, _revertAction, KEY_REVERT_FILE);
    _revertAction.setEnabled(false);
    fileMenu.addSeparator();
    _addMenuItem(fileMenu, _closeAction, KEY_CLOSE_FILE);
    _addMenuItem(fileMenu, _closeAllAction, KEY_CLOSE_ALL_FILES);
    fileMenu.addSeparator();
    _addMenuItem(fileMenu, _pageSetupAction, KEY_PAGE_SETUP);
    _addMenuItem(fileMenu, _printDefDocPreviewAction, KEY_PRINT_PREVIEW);
    _addMenuItem(fileMenu, _printDefDocAction, KEY_PRINT);
    fileMenu.addSeparator();
    _addMenuItem(fileMenu, _quitAction, KEY_QUIT);
    return fileMenu;
  }
}

class PlaceHold {
  private void _setUpToolBar() {
    _toolBar.setFloatable(false);
    _toolBar.add(_createToolbarButton(_newAction));
    _toolBar.add(_createToolbarButton(_openFileOrProjectAction));
    _toolBar.add(_createToolbarButton(_saveAction));
    _closeButton = _createToolbarButton(_closeAction);
    _toolBar.add(_closeButton);
    _toolBar.addSeparator();
    _toolBar.add(_createToolbarButton(cutAction));
    _toolBar.add(_createToolbarButton(copyAction));
    _toolBar.add(_createToolbarButton(pasteAction));
    _toolBar.add(_undoButton);
    _toolBar.add(_redoButton);
    _toolBar.addSeparator();
    _toolBar.add(_createToolbarButton(_findReplaceAction));
    _toolBar.addSeparator();
    _toolBar.add(_compileButton = _createToolbarButton(_compileAllAction));
    _toolBar.add(_createToolbarButton(_resetInteractionsAction));
    _toolBar.addSeparator();
    _toolBar.add(_runButton = _createToolbarButton(_runAction));
    _toolBar.add(_junitButton = _createToolbarButton(_junitAllAction));
    _toolBar.add(_createToolbarButton(_javadocAllAction));
    _toolBar.addSeparator();
    _errorsButton = _createToolbarButton(_errorsAction);
    _errorsButton.setVisible(false);
    _errorsButton.setBackground(DrJava.getConfig().getSetting(DRJAVA_ERRORS_BUTTON_COLOR));
    _toolBar.add(_errorsButton);
    OptionListener<Color> errBtnColorOptionListener =
        new OptionListener<Color>() {
          public void optionChanged(OptionEvent<Color> oce) {
            _errorsButton.setBackground(oce.value);
          }
        };
    DrJava.getConfig().addOptionListener(DRJAVA_ERRORS_BUTTON_COLOR, errBtnColorOptionListener);
    _fixToolbarHeights();
    if (Utilities.isPlasticLaf()) {
      _toolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
      _toolBar.putClientProperty(HEADER_STYLE_KEY, BOTH);
    }
    getContentPane().add(_toolBar, BorderLayout.NORTH);
  }
}

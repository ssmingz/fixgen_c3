class MainFrame {
  public MainFrame() {
    _posListener = new PositionListener();
    _setUpStatusBar();
    _model = new SingleDisplayModel();
    String userdir = CONFIG.getSetting(WORKING_DIRECTORY).toString();
    _openChooser = new JFileChooser(userdir);
    _openChooser.setFileFilter(new JavaSourceFilter());
    _openChooser.setMultiSelectionEnabled(true);
    _saveChooser = new JFileChooser(userdir);
    _saveChooser.setFileFilter(new JavaSourceFilter());
    setGlassPane(new GlassPane());
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.addWindowListener(_windowCloseListener);
    _model.addListener(new ModelListener());
    _setUpTabs();
    _defScrollPanes = new Hashtable();
    JScrollPane defScroll = _createDefScrollPane(_model.getActiveDocument());
    _currentDefPane = ((DefinitionsPane) (defScroll.getViewport().getView()));
    _undoAction.setDelegatee(_currentDefPane.getUndoAction());
    _redoAction.setDelegatee(_currentDefPane.getRedoAction());
    _errorPanel.getErrorListPane().setLastDefPane(_currentDefPane);
    _errorPanel.reset();
    _junitPanel.getJUnitErrorListPane().setLastDefPane(_currentDefPane);
    _junitPanel.reset();
    _setUpActions();
    _setUpMenuBar();
    _setUpToolBar();
    _setUpDocumentSelector();
    setBounds(0, 0, GUI_WIDTH, GUI_HEIGHT);
    setSize(GUI_WIDTH, GUI_HEIGHT);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    final int menubarHeight = 24;
    if (frameSize.height > (screenSize.height - menubarHeight)) {
      frameSize.height = screenSize.height - menubarHeight;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    this.setSize(frameSize);
    this.setLocation(
        (screenSize.width - frameSize.width) / 2,
        ((screenSize.height - frameSize.height) - menubarHeight) / 2);
    _setUpPanes();
    updateFileTitle();
    Font mainFont =
        new Font(
            CONFIG.getSetting(FONT_MAIN_NAME).toString(),
            CONFIG.getSetting(FONT_MAIN_STYLE).intValue(),
            CONFIG.getSetting(FONT_MAIN_SIZE).intValue());
    Font doclistFont =
        new Font(
            CONFIG.getSetting(FONT_DOCLIST_NAME).toString(),
            CONFIG.getSetting(FONT_DOCLIST_STYLE).intValue(),
            CONFIG.getSetting(FONT_DOCLIST_SIZE).intValue());
    _setAllFonts(mainFont);
    _docList.setFont(doclistFont);
    _findReplace = new FindReplaceDialog(this, _model);
  }
}

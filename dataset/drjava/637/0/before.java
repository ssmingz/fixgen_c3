class MainFrame {
  public MainFrame(int rmiPort) {
    _posListener = new PositionListener();
    _setUpStatusBar();
    _model = new SingleDisplayModel(rmiPort);
    if (_model.getDebugger() != null) {
      _model.getDebugger().addListener(new UIDebugListener());
    }
    _debugStepTimer =
        new Timer(
            DEBUG_STEP_TIMER_VALUE,
            new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                _model.printDebugMessage("Stepping...");
              }
            });
    _debugStepTimer.setRepeats(false);
    File workDir = DrJava.getConfig().getSetting(WORKING_DIRECTORY);
    if (workDir == FileOption.NULL_FILE) {
      workDir = new File(System.getProperty("user.dir"));
    }
    if (workDir.isFile() && (workDir.getParent() != null)) {
      workDir = workDir.getParentFile();
    }
    _openChooser = new JFileChooser();
    _openChooser.setCurrentDirectory(workDir);
    _openChooser.setFileFilter(new JavaSourceFilter());
    _openChooser.setMultiSelectionEnabled(true);
    _saveChooser = new JFileChooser();
    _saveChooser.setCurrentDirectory(workDir);
    _saveChooser.setFileFilter(new JavaSourceFilter());
    setGlassPane(new GlassPane());
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.addWindowListener(_windowCloseListener);
    _model.addListener(new ModelListener());
    _defScrollPanes = new Hashtable();
    _setUpTabs();
    JScrollPane defScroll = _createDefScrollPane(_model.getActiveDocument());
    _currentDefPane = ((DefinitionsPane) (defScroll.getViewport().getView()));
    Singleton.setMainFrame(this);
    Singleton.setActionMap(_currentDefPane.getActionMap());
    _setUpKeyBindingMaps();
    _posListener.updateLocation();
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
    _recentFileManager = new RecentFileManager(_fileMenu.getItemCount() - 2, _fileMenu, this);
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
    _promptBeforeQuit = DrJava.getConfig().getSetting(QUIT_PROMPT).booleanValue();
    _setMainFont();
    Font doclistFont = DrJava.getConfig().getSetting(FONT_DOCLIST);
    _docList.setFont(doclistFont);
    DrJava.getConfig().addOptionListener(FONT_MAIN, new MainFontOptionListener());
    DrJava.getConfig().addOptionListener(FONT_DOCLIST, new DoclistFontOptionListener());
    DrJava.getConfig().addOptionListener(FONT_TOOLBAR, new ToolbarFontOptionListener());
    DrJava.getConfig().addOptionListener(TOOLBAR_ICONS_ENABLED, new ToolbarOptionListener());
    DrJava.getConfig().addOptionListener(TOOLBAR_TEXT_ENABLED, new ToolbarOptionListener());
    DrJava.getConfig().addOptionListener(WORKING_DIRECTORY, new WorkingDirOptionListener());
    DrJava.getConfig().addOptionListener(LINEENUM_ENABLED, new LineEnumOptionListener());
    DrJava.getConfig().addOptionListener(QUIT_PROMPT, new QuitPromptOptionListener());
    DrJava.getConfig().addOptionListener(RECENT_FILES_MAX_SIZE, new RecentFilesOptionListener());
    _breakpointHighlights = new Hashtable<Breakpoint, HighlightManager.HighlightInfo>();
    _configFrame = null;
    _helpFrame = null;
    _aboutDialog = null;
    _showConfigException();
    Singleton.setShouldCheckConflict(false);
  }
}

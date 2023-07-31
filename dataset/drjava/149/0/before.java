class MainFrame {
  public MainFrame() {
    final Configuration config = DrJava.getConfig();
    _model = new DefaultGlobalModel();
    _showDebugger = _model.getDebugger().isAvailable();
    _findReplace = new FindReplacePanel(this, _model);
    if (_showDebugger) {
      _debugPanel = new DebugPanel(this);
      _breakpointsPanel = new BreakpointsPanel(this, _model.getBreakpointManager());
    } else {
      _debugPanel = null;
      _breakpointsPanel = null;
    }
    _compilerErrorPanel = new CompilerErrorPanel(_model, this);
    _consoleController =
        new ConsoleController(_model.getConsoleDocument(), _model.getSwingConsoleDocument());
    _consolePane = _consoleController.getPane();
    _consoleScroll =
        new BorderlessScrollPane(_consolePane) {
          public boolean requestFocusInWindow() {
            super.requestFocusInWindow();
            return _consolePane.requestFocusInWindow();
          }
        };
    _interactionsController =
        new InteractionsController(
            _model.getInteractionsModel(), _model.getSwingInteractionsDocument());
    _interactionsPane = _interactionsController.getPane();
    _interactionsContainer = new JPanel(new BorderLayout());
    _lastFocusOwner = _interactionsContainer;
    _junitErrorPanel = new JUnitPanel(_model, this);
    _javadocErrorPanel = new JavadocErrorPanel(_model, this);
    _bookmarksPanel = new BookmarksPanel(this, _model.getBookmarkManager());
    _setUpStatusBar();
    DefinitionsPane.setEditorKit(_model.getEditorKit());
    _defScrollPanes = new Hashtable<OpenDefinitionsDocument, JScrollPane>();
    _tabbedPane = new JTabbedPane();
    _tabbedPane.addFocusListener(
        new FocusListener() {
          public void focusGained(FocusEvent e) {
            Component c = _tabbedPane.getSelectedComponent();
            if (c == _interactionsContainer) {
              _interactionsPane.requestFocusInWindow();
            } else if (c == _findReplace) {
              _findReplace.getFindField().requestFocusInWindow();
            }
          }

          public void focusLost(FocusEvent e) {}
        });
    _tabbedPane.addFocusListener(_focusListenerForRecentDocs);
    _tabbedPane.addKeyListener(_historyListener);
    if (Utilities.isPlasticLaf()) {
      _tabbedPane.putClientProperty(EMBEDDED_TABS_KEY, Boolean.TRUE);
    }
    JScrollPane defScroll = _createDefScrollPane(_model.getActiveDocument());
    _docSplitPane =
        new BorderlessSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            true,
            new JScrollPane(_model.getDocumentNavigator().asContainer()),
            defScroll);
    _debugSplitPane = new BorderlessSplitPane(JSplitPane.VERTICAL_SPLIT, true);
    _mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, _docSplitPane, _tabbedPane);
    _tabbedPanesFrame =
        new DetachedFrame(
            "Tabbed Panes",
            this,
            new Lambda<Void, DetachedFrame>() {
              public Void apply(DetachedFrame frame) {
                frame.getContentPane().add(_tabbedPane);
                return null;
              }
            },
            new Lambda<Void, DetachedFrame>() {
              public Void apply(DetachedFrame frame) {
                _mainSplit.setBottomComponent(_tabbedPane);
                return null;
              }
            });
    _tabbedPanesFrame.addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent we) {
            _detachTabbedPanesMenuItem.setSelected(false);
            DrJava.getConfig().setSetting(DETACH_TABBEDPANES, false);
          }
        });
    _model.getDocumentNavigator().asContainer().addKeyListener(_historyListener);
    _model.getDocumentNavigator().asContainer().addFocusListener(_focusListenerForRecentDocs);
    _model.getDocumentNavigator().asContainer().addMouseListener(_resetFindReplaceListener);
    if (_showDebugger) {
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
    File workDir = _model.getMasterWorkingDirectory();
    _openChooser =
        new JFileChooser() {
          public void setCurrentDirectory(File dir) {
            super.setCurrentDirectory(dir);
            setDialogTitle("Open:  " + getCurrentDirectory());
          }
        };
    _openChooser.setPreferredSize(new Dimension(650, 410));
    _openChooser.setCurrentDirectory(workDir);
    _openChooser.setFileFilter(_javaSourceFilter);
    _openChooser.setMultiSelectionEnabled(true);
    _openRecursiveCheckBox = new JCheckBox("Open folders recursively");
    _openRecursiveCheckBox.setSelected(config.getSetting(OPEN_FOLDER_RECURSIVE).booleanValue());
    _folderChooser = makeFolderChooser(workDir);
    Vector<File> recentProjects = config.getSetting(RECENT_PROJECTS);
    _openProjectChooser = new JFileChooser();
    _openProjectChooser.setPreferredSize(new Dimension(650, 410));
    if ((recentProjects.size() > 0) && (recentProjects.elementAt(0).getParentFile() != null)) {
      _openProjectChooser.setCurrentDirectory(recentProjects.elementAt(0).getParentFile());
    } else {
      _openProjectChooser.setCurrentDirectory(workDir);
    }
    _openProjectChooser.setFileFilter(_projectFilter);
    _openProjectChooser.setMultiSelectionEnabled(false);
    _saveChooser =
        new JFileChooser() {
          public void setCurrentDirectory(File dir) {
            super.setCurrentDirectory(dir);
            setDialogTitle("Save:  " + getCurrentDirectory());
          }
        };
    _saveChooser.setPreferredSize(new Dimension(650, 410));
    _saveChooser.setCurrentDirectory(workDir);
    _saveChooser.setFileFilter(_javaSourceFilter);
    _interactionsHistoryChooser = new JFileChooser();
    _interactionsHistoryChooser.setPreferredSize(new Dimension(650, 410));
    _interactionsHistoryChooser.setCurrentDirectory(workDir);
    _interactionsHistoryChooser.setFileFilter(new InteractionsHistoryFilter());
    _interactionsHistoryChooser.setMultiSelectionEnabled(true);
    setGlassPane(new GlassPane());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(_windowCloseListener);
    _mainListener = new ModelListener();
    _model.addListener(_mainListener);
    _setUpTabs();
    _recentDocFrame = new RecentDocFrame(this);
    OpenDefinitionsDocument activeDoc = _model.getActiveDocument();
    _recentDocFrame.pokeDocument(activeDoc);
    _currentDefDoc = activeDoc.getDocument();
    _currentDefPane = ((DefinitionsPane) (defScroll.getViewport().getView()));
    _currentDefPane.notifyActive();
    int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    ONLY.setMainFrame(this);
    ONLY.setActionMap(_currentDefPane.getActionMap());
    _setUpKeyBindingMaps();
    _posListener.updateLocation();
    _undoAction.setDelegatee(_currentDefPane.getUndoAction());
    _redoAction.setDelegatee(_currentDefPane.getRedoAction());
    _compilerErrorPanel.reset();
    _junitErrorPanel.reset();
    _javadocErrorPanel.reset();
    _menuBar = new MenuBar();
    _fileMenu = _setUpFileMenu(mask);
    _editMenu = _setUpEditMenu(mask);
    _toolsMenu = _setUpToolsMenu(mask);
    _projectMenu = _setUpProjectMenu(mask);
    _debugMenu = null;
    if (_showDebugger) {
      _debugMenu = _setUpDebugMenu(mask);
    }
    _languageLevelMenu = _setUpLanguageLevelMenu(mask);
    _helpMenu = _setUpHelpMenu(mask);
    _setUpActions();
    _setUpMenuBar();
    _setUpContextMenus();
    _toolBar = new JToolBar();
    _undoButton = _createManualToolbarButton(_undoAction);
    _redoButton = _createManualToolbarButton(_redoAction);
    _setUpToolBar();
    if (_debugPanel != null) {
      _debugFrame =
          new DetachedFrame(
              "Debugger",
              this,
              new Lambda<Void, DetachedFrame>() {
                public Void apply(DetachedFrame frame) {
                  frame.getContentPane().add(_debugPanel);
                  return null;
                }
              },
              new Lambda<Void, DetachedFrame>() {
                public Void apply(DetachedFrame frame) {
                  _debugSplitPane.setTopComponent(_docSplitPane);
                  _debugSplitPane.setBottomComponent(_debugPanel);
                  _mainSplit.setTopComponent(_debugSplitPane);
                  return null;
                }
              });
      _debugFrame.addWindowListener(
          new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
              if (_debugFrame == null) {
                return;
              }
              _detachDebugFrameMenuItem.setSelected(false);
              DrJava.getConfig().setSetting(DETACH_DEBUGGER, false);
            }
          });
    } else {
      _debugFrame = null;
    }
    RecentFileAction fileAct =
        new RecentFileManager.RecentFileManager.RecentFileAction() {
          public void actionPerformed(FileOpenSelector selector) {
            open(selector);
          }
        };
    _recentFileManager =
        new RecentFileManager(
            _fileMenu.getItemCount() - 2,
            _fileMenu,
            fileAct,
            OptionConstants.OptionConstants.RECENT_FILES);
    RecentFileAction projAct =
        new RecentFileManager.RecentFileManager.RecentFileAction() {
          public void actionPerformed(FileOpenSelector selector) {
            openProject(selector);
          }
        };
    _recentProjectManager =
        new RecentFileManager(
            _projectMenu.getItemCount() - 2,
            _projectMenu,
            projAct,
            OptionConstants.OptionConstants.RECENT_PROJECTS);
    setIconImage(getIcon("drjava64.png").getImage());
    int x = config.getSetting(WINDOW_X).intValue();
    int y = config.getSetting(WINDOW_Y).intValue();
    int width = config.getSetting(WINDOW_WIDTH).intValue();
    int height = config.getSetting(WINDOW_HEIGHT).intValue();
    int state = config.getSetting(WINDOW_STATE).intValue();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int menubarHeight = 24;
    if (height > (screenSize.height - menubarHeight)) {
      height = screenSize.height - menubarHeight;
    }
    if (width > screenSize.width) {
      width = screenSize.width;
    }
    Rectangle bounds =
        GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .getDefaultConfiguration()
            .getBounds();
    if (x == Integer.MAX_VALUE) {
      x = ((bounds.width - width) + bounds.x) / 2;
    }
    if (y == Integer.MAX_VALUE) {
      y = ((bounds.height - height) + bounds.y) / 2;
    }
    if (x < bounds.x) {
      x = bounds.x;
    }
    if (y < bounds.y) {
      y = bounds.y;
    }
    if ((x + width) > (bounds.x + bounds.width)) {
      x = (bounds.width - width) + bounds.x;
    }
    if ((y + height) > (bounds.y + bounds.height)) {
      y = (bounds.height - height) + bounds.y;
    }
    state &= ~Frame.ICONIFIED;
    if (!Toolkit.getDefaultToolkit().isFrameStateSupported(state)) {
      state = WINDOW_STATE.getDefault();
    }
    setBounds(x, y, width, height);
    final int stateCopy = state;
    addWindowListener(
        new WindowAdapter() {
          public void windowOpened(WindowEvent e) {
            setExtendedState(stateCopy);
            removeWindowListener(this);
          }
        });
    _setUpPanes();
    updateStatusField();
    _promptBeforeQuit = config.getSetting(QUIT_PROMPT).booleanValue();
    _setMainFont();
    Font doclistFont = config.getSetting(FONT_DOCLIST);
    _model.getDocCollectionWidget().setFont(doclistFont);
    _updateNormalColor();
    _updateBackgroundColor();
    config.addOptionListener(DEFINITIONS_NORMAL_COLOR, new NormalColorOptionListener());
    config.addOptionListener(DEFINITIONS_BACKGROUND_COLOR, new BackgroundColorOptionListener());
    config.addOptionListener(FONT_MAIN, new MainFontOptionListener());
    config.addOptionListener(FONT_LINE_NUMBERS, new LineNumbersFontOptionListener());
    config.addOptionListener(FONT_DOCLIST, new DoclistFontOptionListener());
    config.addOptionListener(FONT_TOOLBAR, new ToolbarFontOptionListener());
    config.addOptionListener(TOOLBAR_ICONS_ENABLED, new ToolbarOptionListener());
    config.addOptionListener(TOOLBAR_TEXT_ENABLED, new ToolbarOptionListener());
    config.addOptionListener(TOOLBAR_ENABLED, new ToolbarOptionListener());
    config.addOptionListener(LINEENUM_ENABLED, new LineEnumOptionListener());
    config.addOptionListener(QUIT_PROMPT, new QuitPromptOptionListener());
    config.addOptionListener(RECENT_FILES_MAX_SIZE, new RecentFilesOptionListener());
    config.addOptionListener(
        ALLOW_PRIVATE_ACCESS,
        new OptionListener<Boolean>() {
          public void optionChanged(OptionEvent<Boolean> oce) {
            _model.getInteractionsModel().setPrivateAccessible(oce.value.booleanValue());
          }
        });
    config.addOptionListener(
        FORCE_TEST_SUFFIX,
        new OptionListener<Boolean>() {
          public void optionChanged(OptionEvent<Boolean> oce) {
            _model.getJUnitModel().setForceTestSuffix(oce.value.booleanValue());
          }
        });
    OptionListener<String> choiceOptionListener =
        new OptionListener<String>() {
          public void optionChanged(OptionEvent<String> oce) {
            _javaAPIList = null;
          }
        };
    DrJava.getConfig().addOptionListener(JAVADOC_API_REF_VERSION, choiceOptionListener);
    OptionListener<String> link13OptionListener =
        new OptionListener<String>() {
          public void optionChanged(OptionEvent<String> oce) {
            String linkVersion = DrJava.getConfig().getSetting(JAVADOC_API_REF_VERSION);
            if (linkVersion.equals(JAVADOC_1_3_TEXT)) {
              _javaAPIList = null;
            }
          }
        };
    DrJava.getConfig().addOptionListener(JAVADOC_1_3_LINK, link13OptionListener);
    OptionListener<String> link14OptionListener =
        new OptionListener<String>() {
          public void optionChanged(OptionEvent<String> oce) {
            String linkVersion = DrJava.getConfig().getSetting(JAVADOC_API_REF_VERSION);
            if (linkVersion.equals(JAVADOC_1_4_TEXT)) {
              _javaAPIList = null;
            }
          }
        };
    DrJava.getConfig().addOptionListener(JAVADOC_1_4_LINK, link14OptionListener);
    OptionListener<String> link15OptionListener =
        new OptionListener<String>() {
          public void optionChanged(OptionEvent<String> oce) {
            String linkVersion = DrJava.getConfig().getSetting(JAVADOC_API_REF_VERSION);
            if (linkVersion.equals(JAVADOC_1_5_TEXT)) {
              _javaAPIList = null;
            }
          }
        };
    DrJava.getConfig().addOptionListener(JAVADOC_1_5_LINK, link15OptionListener);
    OptionListener<String> link16OptionListener =
        new OptionListener<String>() {
          public void optionChanged(OptionEvent<String> oce) {
            String linkVersion = DrJava.getConfig().getSetting(JAVADOC_API_REF_VERSION);
            if (linkVersion.equals(JAVADOC_1_6_TEXT)) {
              _javaAPIList = null;
            }
          }
        };
    DrJava.getConfig().addOptionListener(JAVADOC_1_6_LINK, link16OptionListener);
    _documentBreakpointHighlights = new Hashtable<Breakpoint, HighlightManager.HighlightInfo>();
    _documentBookmarkHighlights =
        new Hashtable<OrderedDocumentRegion, HighlightManager.HighlightInfo>();
    _configFrame = new ConfigFrame(this);
    _helpFrame = new HelpFrame();
    _aboutDialog = new AboutDialog(this);
    _quickStartFrame = new QuickStartFrame();
    _interactionsScriptController = null;
    _executeExternalDialog = new ExecuteExternalDialog(this);
    _editExternalDialog = new EditExternalDialog(this);
    _jarOptionsDialog = new JarOptionsDialog(this);
    initTabbedPanesFrame();
    initDebugFrame();
    initJarOptionsDialog();
    initExecuteExternalProcessDialog();
    config.addOptionListener(
        LOOK_AND_FEEL, new ConfigOptionListeners.LookAndFeelListener(_configFrame));
    OptionListener<String> slaveJVMArgsListener =
        new ConfigOptionListeners.SlaveJVMArgsListener(_configFrame);
    config.addOptionListener(SLAVE_JVM_ARGS, slaveJVMArgsListener);
    _slaveJvmXmxListener = new ConfigOptionListeners.SlaveJVMXMXListener(_configFrame);
    config.addOptionListener(SLAVE_JVM_XMX, _slaveJvmXmxListener);
    OptionListener<String> masterJVMArgsListener =
        new ConfigOptionListeners.MasterJVMArgsListener(_configFrame);
    config.addOptionListener(MASTER_JVM_ARGS, masterJVMArgsListener);
    _masterJvmXmxListener = new ConfigOptionListeners.MasterJVMXMXListener(_configFrame);
    config.addOptionListener(MASTER_JVM_XMX, _masterJvmXmxListener);
    config.addOptionListener(
        JAVADOC_CUSTOM_PARAMS, new ConfigOptionListeners.JavadocCustomParamsListener(_configFrame));
    ConfigOptionListeners.sanitizeSlaveJVMArgs(
        this, config.getSetting(SLAVE_JVM_ARGS), slaveJVMArgsListener);
    ConfigOptionListeners.sanitizeSlaveJVMXMX(this, config.getSetting(SLAVE_JVM_XMX));
    ConfigOptionListeners.sanitizeMasterJVMArgs(
        this, config.getSetting(MASTER_JVM_ARGS), masterJVMArgsListener);
    ConfigOptionListeners.sanitizeMasterJVMXMX(this, config.getSetting(MASTER_JVM_XMX));
    ConfigOptionListeners.sanitizeJavadocCustomParams(
        this, config.getSetting(JAVADOC_CUSTOM_PARAMS));
    _showConfigException();
    ONLY.setShouldCheckConflict(false);
    ONLY.afterUISetup(_aboutAction, _editPreferencesAction, _quitAction);
    setUpKeys();
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addKeyEventDispatcher(
            new KeyEventDispatcher() {
              public boolean dispatchKeyEvent(KeyEvent e) {
                boolean discardEvent = false;
                if ((((e.getID() == KeyEvent.KEY_TYPED) && (e.getKeyChar() == '`'))
                        && (((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK)
                                == InputEvent.CTRL_DOWN_MASK)
                            || ((e.getModifiersEx()
                                    & (InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK))
                                == (InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK))))
                    && e.getComponent().getClass().equals(DefinitionsPane.class)) {
                  discardEvent = true;
                }
                return discardEvent;
              }
            });
    if (DrJava.getConfig().getSetting(REMOTE_CONTROL_ENABLED)) {
      try {
        if (!RemoteControlClient.isServerRunning()) {
          RemoteControlServer rcServer = new RemoteControlServer(this);
        }
      } catch (IOException ioe) {
        try {
          RemoteControlClient.openFile(null);
        } catch (IOException ignored) {
        }
        if ((!Utilities.TEST_MODE)
            && (!System.getProperty("user.name").equals(RemoteControlClient.getServerUser()))) {
          Object[] options = new Object[] {"Disable", "Ignore"};
          String msg = "<html>Could not start DrJava's remote control server";
          if (RemoteControlClient.getServerUser() != null) {
            msg +=
                ("<br>because user " + RemoteControlClient.getServerUser())
                    + " is already using the same port";
          }
          msg +=
              ".<br>Please select an unused port in the Preferences dialog.<br>"
                  + "In the meantime, do you want to disable the remote control feature?";
          int n =
              JOptionPane.showOptionDialog(
                  this,
                  msg,
                  "Could Not Start Remote Control Server",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[1]);
          if (n == JOptionPane.YES_OPTION) {
            DrJava.getConfig().setSetting(REMOTE_CONTROL_ENABLED, false);
          }
        }
      }
    }
    setUpDrJavaProperties();
    DrJavaErrorHandler.setButton(_errorsButton);
    boolean askedForNewVersion = false;
    if ((!DrJava.getConfig()
            .getSetting(NEW_VERSION_NOTIFICATION)
            .equals(NEW_VERSION_NOTIFICATION_CHOICES.get(3)))
        && (!Utilities.TEST_MODE)) {
      int days = DrJava.getConfig().getSetting(NEW_VERSION_NOTIFICATION_DAYS);
      Date nextCheck =
          new Date(
              DrJava.getConfig().getSetting(LAST_NEW_VERSION_NOTIFICATION)
                  + ((((days * 24L) * 60) * 60) * 1000));
      if (new Date().after(nextCheck)) {
        askedForNewVersion = true;
        EventQueue.invokeLater(
            new Runnable() {
              public void run() {
                NewVersionPopup popup = new NewVersionPopup(MainFrame.this);
                if (popup.checkNewVersion()) {
                  popup.setVisible(true);
                }
              }
            });
      }
    }
    if (!askedForNewVersion) {
      if (DrJava.getConfig().getSetting(DIALOG_DRJAVA_SURVEY_ENABLED) && (!Utilities.TEST_MODE)) {
        if (DrJavaSurveyPopup.maySubmitSurvey()) {
          EventQueue.invokeLater(
              new Runnable() {
                public void run() {
                  DrJavaSurveyPopup popup = new DrJavaSurveyPopup(MainFrame.this);
                  popup.setVisible(true);
                }
              });
        }
      }
    }
    initDone();
    EventQueue.invokeLater(
        new Runnable() {
          public void run() {
            _tabbedPanesFrame.setDisplayInFrame(DrJava.getConfig().getSetting(DETACH_TABBEDPANES));
          }
        });
  }
}

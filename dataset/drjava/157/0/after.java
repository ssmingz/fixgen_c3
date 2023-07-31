class HTMLFrame {
  public HTMLFrame(String frameName, URL introUrl, URL indexUrl, String iconString, File baseDir) {
    super(frameName);
    _contentsDocPane = new JEditorPane();
    _contentsDocPane.setEditable(false);
    JScrollPane contentsScroll = new BorderlessScrollPane(_contentsDocPane);
    _mainDocPane = new JEditorPane();
    _mainDocPane.setEditable(false);
    _mainScroll = new BorderlessScrollPane(_mainDocPane);
    _splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, contentsScroll, _mainScroll);
    _splitPane.setDividerLocation(LEFT_PANEL_WIDTH);
    _splitPaneHolder = new JPanel(new GridLayout(1, 1));
    _splitPaneHolder.setBorder(new EmptyBorder(0, 5, 0, 5));
    _splitPaneHolder.add(_splitPane);
    _closeButton = new JButton(_closeAction);
    _backButton = makeButton(_backAction, JButton.RIGHT, 0, 3);
    _forwardButton = makeButton(_forwardAction, JButton.LEFT, 3, 0);
    _backAction.setEnabled(false);
    _forwardAction.setEnabled(false);
    _closePanel = new JPanel(new BorderLayout());
    _closePanel.add(_closeButton, BorderLayout.EAST);
    _closePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    _navPane = new JPanel();
    _navPane.setBackground(new Color(0xccccff));
    _navPane.setLayout(new BoxLayout(_navPane, BoxLayout.X_AXIS));
    JLabel icon = new JLabel(MainFrame.getIcon(iconString));
    _navPane.add(icon);
    _navPane.add(Box.createHorizontalStrut(8));
    _navPane.add(Box.createHorizontalGlue());
    _navPane.add(_backButton);
    _navPane.add(Box.createHorizontalStrut(8));
    _navPane.add(_forwardButton);
    _navPane.add(Box.createHorizontalStrut(3));
    _navPane.setBorder(new EmptyBorder(0, 0, 0, 5));
    JPanel navContainer = new JPanel(new GridLayout(1, 1));
    navContainer.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new EtchedBorder()));
    navContainer.add(_navPane);
    Container cp = getContentPane();
    cp.setLayout(new BorderLayout());
    cp.add(navContainer, BorderLayout.NORTH);
    cp.add(_splitPaneHolder, BorderLayout.CENTER);
    cp.add(_closePanel, BorderLayout.SOUTH);
    _linkError = false;
    _hyperlinkListeners = new ArrayList<HyperlinkListener>();
    _hyperlinkListeners.add(_resetListener);
    _mainDocPane.addHyperlinkListener(_resetListener);
    if (baseDir == null) {
      _baseURL = null;
    } else {
      try {
        _baseURL = FileOps.toURL(baseDir);
      } catch (MalformedURLException ex) {
        throw new UnexpectedException(ex);
      }
    }
    if (indexUrl == null) {
      _displayContentsError(null);
    } else {
      try {
        _contentsDocPane.setPage(indexUrl);
        if (_baseURL != null) {
          ((HTMLDocument) (_contentsDocPane.getDocument())).setBase(_baseURL);
        }
      } catch (IOException ioe) {
        _displayContentsError(indexUrl, ioe);
      }
    }
    if (introUrl == null) {
      _displayMainError(null);
    } else {
      _history = new HistoryList(introUrl);
      _displayPage(introUrl);
      _displayPage(introUrl);
    }
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    Utilities.setPopupLoc(this, null);
    initDone();
  }
}

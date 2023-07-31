class PlaceHold {
  protected JFrame createUI(String suiteName) {
    JFrame frame = createFrame("JUnit");
    JMenuBar mb = new JMenuBar();
    createMenus(mb);
    frame.setJMenuBar(mb);
    JLabel suiteLabel = new JLabel("Test class name:");
    fSuiteCombo = createSuiteCombo();
    fRun = createRunButton();
    frame.getRootPane().setDefaultButton(fRun);
    Component browseButton = createBrowseButton();
    fUseLoadingRunner = createUseLoaderCheckBox();
    fProgressIndicator = new ProgressBar();
    fCounterPanel = createCounterPanel();
    JLabel failureLabel = new JLabel("Errors and Failures:");
    fFailures = new DefaultListModel();
    fTestViewTab = createTestRunViews();
    JPanel failedPanel = createFailedPanel();
    fFailureView = createFailureDetailView();
    JScrollPane tracePane =
        new JScrollPane(
            fFailureView.getComponent(),
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    fStatusLine = createStatusLine();
    fQuitButton = createQuitButton();
    fLogo = createLogo();
    JPanel panel = new JPanel(new GridBagLayout());
    addGrid(
        panel, suiteLabel, 0, 0, 2, GridBagConstraints.HORIZONTAL, 1.0, GridBagConstraints.WEST);
    addGrid(
        panel, fSuiteCombo, 0, 1, 1, GridBagConstraints.HORIZONTAL, 1.0, GridBagConstraints.WEST);
    addGrid(panel, browseButton, 1, 1, 1, GridBagConstraints.NONE, 0.0, GridBagConstraints.WEST);
    addGrid(panel, fRun, 2, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, GridBagConstraints.CENTER);
    addGrid(
        panel, fUseLoadingRunner, 0, 2, 3, GridBagConstraints.NONE, 1.0, GridBagConstraints.WEST);
    addGrid(
        panel,
        new JSeparator(),
        0,
        3,
        3,
        GridBagConstraints.HORIZONTAL,
        1.0,
        GridBagConstraints.WEST);
    addGrid(
        panel,
        fProgressIndicator,
        0,
        4,
        2,
        GridBagConstraints.HORIZONTAL,
        1.0,
        GridBagConstraints.WEST);
    addGrid(panel, fLogo, 2, 4, 1, GridBagConstraints.NONE, 0.0, GridBagConstraints.NORTH);
    addGrid(panel, fCounterPanel, 0, 5, 2, GridBagConstraints.NONE, 0.0, GridBagConstraints.CENTER);
    JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fTestViewTab, tracePane);
    addGrid(panel, splitter, 0, 6, 2, GridBagConstraints.BOTH, 1.0, GridBagConstraints.WEST);
    addGrid(
        panel, failedPanel, 2, 6, 1, GridBagConstraints.HORIZONTAL, 0.0, GridBagConstraints.NORTH);
    addGrid(
        panel, fStatusLine, 0, 8, 2, GridBagConstraints.HORIZONTAL, 1.0, GridBagConstraints.CENTER);
    addGrid(
        panel, fQuitButton, 2, 8, 1, GridBagConstraints.HORIZONTAL, 0.0, GridBagConstraints.CENTER);
    frame.setContentPane(panel);
    frame.pack();
    frame.setLocation(200, 200);
    return frame;
  }
}

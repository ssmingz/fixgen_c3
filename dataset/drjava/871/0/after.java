class FindReplaceDialog {
  public FindReplaceDialog(MainFrame frame, SingleDisplayModel model) {
    super(frame, "Find/Replace");
    _model = model;
    int i = this.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
    InputMap im = _mainPanel.getInputMap(i);
    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Close");
    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Find Next");
    ActionMap am = _mainPanel.getActionMap();
    am.put("Find Next", _findNextAction);
    am.put(
        "Close",
        new AbstractAction("Close") {
          public void actionPerformed(ActionEvent ae) {
            _frame.getCurrentDefPane().requestFocus();
            _close();
          }
        });
    _findNextButton = new JButton(_findNextAction);
    _replaceButton = new JButton(_replaceAction);
    _replaceFindButton = new JButton(_replaceFindAction);
    _replaceAllButton = new JButton(_replaceAllAction);
    _message = new JLabel("");
    _replaceAction.setEnabled(false);
    _replaceFindAction.setEnabled(false);
    Font font = CONFIG.getSetting(FONT_MAIN);
    _findField.setFont(font);
    _replaceField.setFont(font);
    JPanel buttons = new JPanel();
    buttons.setLayout(new GridLayout(1, 0));
    buttons.add(_findNextButton);
    buttons.add(_replaceButton);
    buttons.add(_replaceFindButton);
    buttons.add(_replaceAllButton);
    JLabel findLabel = new JLabel("Find:", SwingConstants.LEFT);
    findLabel.setHorizontalAlignment(SwingConstants.LEFT);
    JLabel replaceLabel = new JLabel("Replace:", SwingConstants.LEFT);
    replaceLabel.setHorizontalAlignment(SwingConstants.LEFT);
    _labelPanel = new JPanel(new GridLayout(0, 1));
    _labelPanel.add(findLabel);
    _labelPanel.add(replaceLabel);
    MatchCaseListener mcl = new MatchCaseListener();
    _matchCase = new JCheckBox("Match Case", true);
    _matchCase.addItemListener(mcl);
    _matchCaseAndClosePanel = new JPanel(new BorderLayout());
    _matchCaseAndClosePanel.add(_matchCase, BorderLayout.WEST);
    _rightPanel = new JPanel(new GridLayout(2, 2));
    _rightPanel.add(wrap(_findField));
    _rightPanel.add(_matchCaseAndClosePanel);
    _rightPanel.add(wrap(_replaceField));
    _rightPanel.add(_message);
    hookComponents(_mainPanel, _rightPanel, _labelPanel, buttons);
    _machine = new FindReplaceMachine();
    _findField.addActionListener(_findNextAction);
    _findField.setNextFocusableComponent(_replaceField);
    _replaceField.setNextFocusableComponent(_matchCase);
    _matchCase.setNextFocusableComponent(_findNextButton);
    _replaceAllButton.setNextFocusableComponent(_closeButton);
    _closeButton.setNextFocusableComponent(_findField);
    _findField
        .getDocument()
        .addDocumentListener(
            new DocumentListener() {
              public void changedUpdate(DocumentEvent e) {
                _machine.makeCurrentOffsetStart();
                _replaceAction.setEnabled(false);
                _replaceFindAction.setEnabled(false);
                if (_findField.getText().equals("")) {
                  _replaceAllAction.setEnabled(false);
                } else {
                  _replaceAllAction.setEnabled(true);
                }
              }

              public void insertUpdate(DocumentEvent e) {
                _machine.makeCurrentOffsetStart();
                _replaceAction.setEnabled(false);
                _replaceFindAction.setEnabled(false);
                if (_findField.getText().equals("")) {
                  _replaceAllAction.setEnabled(false);
                } else {
                  _replaceAllAction.setEnabled(true);
                }
              }

              public void removeUpdate(DocumentEvent e) {
                _machine.makeCurrentOffsetStart();
                _replaceAction.setEnabled(false);
                _replaceFindAction.setEnabled(false);
                if (_findField.getText().equals("")) {
                  _replaceAllAction.setEnabled(false);
                } else {
                  _replaceAllAction.setEnabled(true);
                }
              }
            });
  }
}

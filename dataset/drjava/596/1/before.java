class PlaceHold {
  private JPanel makeCommandPane() {
    JPanel panel = new JPanel(new BorderLayout());
    GridBagLayout gridbag = new GridBagLayout();
    JPanel main = new JPanel(gridbag);
    GridBagConstraints c = new GridBagConstraints();
    main.setLayout(gridbag);
    c.fill = GridBagConstraints.BOTH;
    Insets labelInsets = new Insets(5, 10, 0, 0);
    Insets compInsets = new Insets(5, 5, 0, 10);
    c.weightx = 0.0;
    c.weighty = 0.0;
    c.insets = labelInsets;
    JLabel commandLineLabel = new JLabel("Command line:");
    gridbag.setConstraints(commandLineLabel, c);
    main.add(commandLineLabel);
    c.weightx = 1.0;
    c.weighty = 32.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = compInsets;
    _commandLine = new JTextPane();
    _commandLine.addKeyListener(
        new KeyListener() {
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              e.consume();
            } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
              e.consume();
              if (e.isShiftDown()) {
                _insertCommandButton.setEnabled(false);
                _tabbedPane.requestFocus();
              } else {
                _commandWorkDirLine.requestFocus();
              }
            }
          }

          public void keyReleased(KeyEvent e) {}

          public void keyTyped(KeyEvent e) {}
        });
    JScrollPane commandLineSP = new JScrollPane(_commandLine);
    commandLineSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    gridbag.setConstraints(commandLineSP, c);
    main.add(commandLineSP);
    c.weightx = 0.0;
    c.weighty = 0.0;
    c.gridwidth = 1;
    c.insets = labelInsets;
    JLabel commandLinePreviewLabel = new JLabel("Command line preview:");
    commandLinePreviewLabel.setToolTipText(STALE_TOOLTIP);
    gridbag.setConstraints(commandLinePreviewLabel, c);
    main.add(commandLinePreviewLabel);
    c.weightx = 1.0;
    c.weighty = 32.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = compInsets;
    _commandLinePreview = new JTextPane();
    _commandLinePreview.setToolTipText(STALE_TOOLTIP);
    _commandLineDoc = ((StyledDocument) (_commandLinePreview.getDocument()));
    _varCommandLineCmdStyle = new SimpleAttributeSet();
    StyleConstants.setBackground(
        _varCommandLineCmdStyle, DrJava.getConfig().getSetting(DEFINITIONS_MATCH_COLOR));
    _commandLineCmdAS = new SimpleAttributeSet();
    StyleConstants.setForeground(
        _commandLineCmdAS, DrJava.getConfig().getSetting(DEFINITIONS_NORMAL_COLOR));
    _varCommandLineCmdStyle = new SimpleAttributeSet();
    StyleConstants.setBackground(
        _varCommandLineCmdStyle, DrJava.getConfig().getSetting(DEFINITIONS_MATCH_COLOR));
    _varErrorCommandLineCmdStyle = new SimpleAttributeSet();
    StyleConstants.setBackground(
        _varErrorCommandLineCmdStyle, DrJava.getConfig().getSetting(DEBUG_BREAKPOINT_COLOR));
    _varCommandLineCmdStyle = new SimpleAttributeSet();
    StyleConstants.setBackground(
        _varCommandLineCmdStyle, DrJava.getConfig().getSetting(DEFINITIONS_MATCH_COLOR));
    _commandLinePreview.setEditable(false);
    _commandLinePreview.setBackground(Color.LIGHT_GRAY);
    _commandLinePreview.setSelectedTextColor(Color.LIGHT_GRAY);
    JScrollPane commandLinePreviewSP = new JScrollPane(_commandLinePreview);
    commandLinePreviewSP.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    gridbag.setConstraints(commandLinePreviewSP, c);
    main.add(commandLinePreviewSP);
    c.weightx = 0.0;
    c.weighty = 0.0;
    c.gridwidth = 1;
    c.insets = labelInsets;
    JLabel workDirLabel = new JLabel("Work directory:");
    gridbag.setConstraints(workDirLabel, c);
    main.add(workDirLabel);
    c.weightx = 1.0;
    c.weighty = 8.0;
    c.gridwidth = GridBagConstraints.RELATIVE;
    c.insets = compInsets;
    _commandWorkDirLine = new JTextPane();
    _commandWorkDirLine.addKeyListener(
        new KeyListener() {
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              e.consume();
            } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
              e.consume();
              if (e.isShiftDown()) {
                _commandLine.requestFocus();
              } else {
                _insertCommandButton.setEnabled(false);
                if (_editMode) {
                  _saveCommandButton.requestFocus();
                } else {
                  _runCommandButton.requestFocus();
                }
              }
            }
          }

          public void keyReleased(KeyEvent e) {}

          public void keyTyped(KeyEvent e) {}
        });
    JScrollPane commandWorkDirLineSP = new JScrollPane(_commandWorkDirLine);
    commandWorkDirLineSP.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    gridbag.setConstraints(commandWorkDirLineSP, c);
    main.add(commandWorkDirLineSP);
    c.weightx = 0.0;
    c.weighty = 0.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = compInsets;
    _commandWorkDirBtn = new JButton("...");
    _commandWorkDirBtn.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            chooseFile(_commandWorkDirLine);
          }
        });
    gridbag.setConstraints(_commandWorkDirBtn, c);
    main.add(_commandWorkDirBtn);
    c.weightx = 0.0;
    c.weighty = 0.0;
    c.gridwidth = 1;
    c.insets = labelInsets;
    JLabel commandWorkDirLinePreviewLabel = new JLabel("Work directory preview:");
    commandWorkDirLinePreviewLabel.setToolTipText(STALE_TOOLTIP);
    gridbag.setConstraints(commandWorkDirLinePreviewLabel, c);
    main.add(commandWorkDirLinePreviewLabel);
    c.weightx = 1.0;
    c.weighty = 8.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = compInsets;
    _commandWorkDirLinePreview = new JTextPane();
    _commandWorkDirLinePreview.setToolTipText(STALE_TOOLTIP);
    _commandWorkDirLineDoc = ((StyledDocument) (_commandWorkDirLinePreview.getDocument()));
    _commandWorkDirLinePreview.setEditable(false);
    _commandWorkDirLinePreview.setBackground(Color.LIGHT_GRAY);
    _commandWorkDirLinePreview.setSelectedTextColor(Color.LIGHT_GRAY);
    JScrollPane commandWorkDirLinePreviewSP = new JScrollPane(_commandWorkDirLinePreview);
    commandWorkDirLinePreviewSP.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    gridbag.setConstraints(commandWorkDirLinePreviewSP, c);
    main.add(commandWorkDirLinePreviewSP);
    panel.add(main, BorderLayout.CENTER);
    JPanel bottom = new JPanel();
    bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
    bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
    bottom.add(Box.createHorizontalGlue());
    if (!_editMode) {
      bottom.add(_runCommandButton);
    }
    bottom.add(_saveCommandButton);
    bottom.add(_insertCommandButton);
    bottom.add(_cancelCommandButton);
    bottom.add(Box.createHorizontalGlue());
    panel.add(bottom, BorderLayout.SOUTH);
    _documentListener =
        new DocumentListener() {
          public void update(DocumentEvent e) {
            try {
              _commandLineDoc.remove(0, _commandLineDoc.getLength());
              StringBuilder sb = new StringBuilder();
              String text = StringOps.replaceVariables(_commandLine.getText(), ONLY, TO_STRING);
              _commandLineDoc.insertString(_commandLineDoc.getLength(), text, null);
              colorVariables(
                  _commandLine,
                  ONLY,
                  this,
                  _commandLineCmdAS,
                  _varCommandLineCmdStyle,
                  _varErrorCommandLineCmdStyle);
            } catch (BadLocationException ble) {
              _commandLinePreview.setText("Error.");
            }
          }

          public void changedUpdate(DocumentEvent e) {
            update(e);
          }

          public void insertUpdate(DocumentEvent e) {
            update(e);
          }

          public void removeUpdate(DocumentEvent e) {
            update(e);
          }
        };
    _commandLine.getDocument().addDocumentListener(_documentListener);
    _documentListener.changedUpdate(null);
    _workDirDocumentListener =
        new DocumentListener() {
          public void update(DocumentEvent e) {
            try {
              _commandWorkDirLineDoc.remove(0, _commandWorkDirLineDoc.getLength());
              String text =
                  StringOps.replaceVariables(_commandWorkDirLine.getText(), ONLY, TO_STRING);
              _commandWorkDirLineDoc.insertString(0, text, null);
              colorVariables(
                  _commandWorkDirLine,
                  ONLY,
                  this,
                  _commandLineCmdAS,
                  _varCommandLineCmdStyle,
                  _varErrorCommandLineCmdStyle);
            } catch (BadLocationException ble) {
              _commandLinePreview.setText("Error: " + ble);
            }
          }

          public void changedUpdate(DocumentEvent e) {
            update(e);
          }

          public void insertUpdate(DocumentEvent e) {
            update(e);
          }

          public void removeUpdate(DocumentEvent e) {
            update(e);
          }
        };
    _commandWorkDirLine.getDocument().addDocumentListener(_workDirDocumentListener);
    _commandWorkDirLine.setText("${user.dir}");
    _workDirDocumentListener.changedUpdate(null);
    DrJava.getConfig()
        .addOptionListener(
            DEFINITIONS_COMMENT_COLOR,
            new OptionListener<Color>() {
              public void optionChanged(OptionEvent<Color> oce) {
                StyleConstants.setForeground(_javaCommandLineExecutableStyle, oce.value);
                _documentListener.changedUpdate(null);
                _workDirDocumentListener.changedUpdate(null);
              }
            });
    DrJava.getConfig()
        .addOptionListener(
            DEFINITIONS_KEYWORD_COLOR,
            new OptionListener<Color>() {
              public void optionChanged(OptionEvent<Color> oce) {
                StyleConstants.setForeground(_javaCommandLineJVMStyle, oce.value);
                _documentListener.changedUpdate(null);
                _workDirDocumentListener.changedUpdate(null);
              }
            });
    _lastCommandFocus = _commandLine;
    _commandLine.addFocusListener(
        new FocusAdapter() {
          @SuppressWarnings("unchecked")
          public void focusGained(FocusEvent e) {
            _lastCommandFocus = ((JTextPane) (e.getComponent()));
            _insertCommandButton.setEnabled(true);
          }

          public void focusLost(FocusEvent e) {
            if ((e.getOppositeComponent() == _commandLinePreview)
                || (e.getOppositeComponent() == _commandWorkDirLinePreview)) {
              _commandLine.requestFocus();
            }
          }
        });
    _commandWorkDirLine.addFocusListener(
        new FocusAdapter() {
          @SuppressWarnings("unchecked")
          public void focusGained(FocusEvent e) {
            _lastCommandFocus = ((JTextPane) (e.getComponent()));
            _insertCommandButton.setEnabled(true);
          }

          public void focusLost(FocusEvent e) {
            if ((e.getOppositeComponent() == _commandLinePreview)
                || (e.getOppositeComponent() == _commandWorkDirLinePreview)) {
              _commandWorkDirLine.requestFocus();
            }
          }
        });
    return panel;
  }
}

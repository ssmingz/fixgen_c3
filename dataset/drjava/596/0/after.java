class PlaceHold {
  private JPanel makeJavaPane() {
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
    c.gridwidth = 1;
    c.insets = labelInsets;
    JLabel jvmArgsLabel = new JLabel("JVM arguments:");
    gridbag.setConstraints(jvmArgsLabel, c);
    main.add(jvmArgsLabel);
    c.weightx = 1.0;
    c.weighty = 32.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = compInsets;
    _jvmLine = new JTextPane();
    _jvmLineDoc = ((StyledDocument) (_jvmLine.getDocument()));
    _jvmLine.addKeyListener(
        new KeyListener() {
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              e.consume();
            } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
              e.consume();
              if (e.isShiftDown()) {
                _insertJavaButton.setEnabled(false);
                _tabbedPane.requestFocus();
              } else {
                _javaCommandLine.requestFocus();
              }
            }
          }

          public void keyReleased(KeyEvent e) {}

          public void keyTyped(KeyEvent e) {}
        });
    JScrollPane jvmLineSP = new JScrollPane(_jvmLine);
    jvmLineSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    gridbag.setConstraints(jvmLineSP, c);
    main.add(jvmLineSP);
    c.weightx = 0.0;
    c.weighty = 0.0;
    c.gridwidth = 1;
    c.insets = labelInsets;
    JLabel javaCommandLineLabel = new JLabel("Java command line:");
    gridbag.setConstraints(javaCommandLineLabel, c);
    main.add(javaCommandLineLabel);
    c.weightx = 1.0;
    c.weighty = 32.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = compInsets;
    _javaCommandLine = new JTextPane();
    _javaCommandLine.addKeyListener(
        new KeyListener() {
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              e.consume();
            } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
              e.consume();
              if (e.isShiftDown()) {
                _jvmLine.requestFocus();
              } else {
                _javaCommandWorkDirLine.requestFocus();
              }
            }
          }

          public void keyReleased(KeyEvent e) {}

          public void keyTyped(KeyEvent e) {}
        });
    JScrollPane javaCommandLineSP = new JScrollPane(_javaCommandLine);
    javaCommandLineSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    gridbag.setConstraints(javaCommandLineSP, c);
    main.add(javaCommandLineSP);
    c.weightx = 0.0;
    c.weighty = 0.0;
    c.gridwidth = 1;
    c.insets = labelInsets;
    JLabel javaCommandLinePreviewLabel = new JLabel("Command line preview:");
    javaCommandLinePreviewLabel.setToolTipText(STALE_TOOLTIP);
    gridbag.setConstraints(javaCommandLinePreviewLabel, c);
    main.add(javaCommandLinePreviewLabel);
    c.weightx = 1.0;
    c.weighty = 32.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = compInsets;
    _javaCommandLinePreview = new JTextPane();
    _javaCommandLinePreview.setToolTipText(STALE_TOOLTIP);
    _javaCommandLineDoc = ((StyledDocument) (_javaCommandLinePreview.getDocument()));
    _javaCommandLineExecutableStyle = _javaCommandLineDoc.addStyle("ExecutableStyle", null);
    StyleConstants.setItalic(_javaCommandLineExecutableStyle, true);
    StyleConstants.setForeground(
        _javaCommandLineExecutableStyle, DrJava.getConfig().getSetting(DEFINITIONS_COMMENT_COLOR));
    _javaCommandLineJVMStyle = _javaCommandLineDoc.addStyle("JVMStyle", null);
    StyleConstants.setForeground(
        _javaCommandLineJVMStyle, DrJava.getConfig().getSetting(DEFINITIONS_KEYWORD_COLOR));
    _javaCommandLineJVMAS = new SimpleAttributeSet();
    StyleConstants.setForeground(
        _javaCommandLineJVMAS, DrJava.getConfig().getSetting(DEFINITIONS_KEYWORD_COLOR));
    _javaVarCommandLineJVMStyle = new SimpleAttributeSet();
    StyleConstants.setForeground(
        _javaVarCommandLineJVMStyle, DrJava.getConfig().getSetting(DEFINITIONS_KEYWORD_COLOR));
    StyleConstants.setBackground(
        _javaVarCommandLineJVMStyle, DrJava.getConfig().getSetting(DEFINITIONS_MATCH_COLOR));
    _javaVarErrorCommandLineJVMStyle = new SimpleAttributeSet();
    StyleConstants.setForeground(
        _javaVarErrorCommandLineJVMStyle, DrJava.getConfig().getSetting(DEFINITIONS_NORMAL_COLOR));
    StyleConstants.setBackground(
        _javaVarErrorCommandLineJVMStyle, DrJava.getConfig().getSetting(DEBUG_BREAKPOINT_COLOR));
    _javaVarCommandLineCmdStyle = new SimpleAttributeSet();
    StyleConstants.setBackground(
        _javaVarCommandLineJVMStyle, DrJava.getConfig().getSetting(DEFINITIONS_MATCH_COLOR));
    _javaCommandLineCmdAS = new SimpleAttributeSet();
    StyleConstants.setForeground(
        _javaCommandLineCmdAS, DrJava.getConfig().getSetting(DEFINITIONS_NORMAL_COLOR));
    _javaVarCommandLineCmdStyle = new SimpleAttributeSet();
    StyleConstants.setBackground(
        _javaVarCommandLineCmdStyle, DrJava.getConfig().getSetting(DEFINITIONS_MATCH_COLOR));
    _javaVarErrorCommandLineCmdStyle = new SimpleAttributeSet();
    StyleConstants.setBackground(
        _javaVarErrorCommandLineCmdStyle, DrJava.getConfig().getSetting(DEBUG_BREAKPOINT_COLOR));
    _javaVarCommandLineCmdStyle = new SimpleAttributeSet();
    StyleConstants.setBackground(
        _javaVarCommandLineCmdStyle, DrJava.getConfig().getSetting(DEFINITIONS_MATCH_COLOR));
    _javaCommandLinePreview.setEditable(false);
    _javaCommandLinePreview.setBackground(Color.LIGHT_GRAY);
    _javaCommandLinePreview.setSelectedTextColor(Color.LIGHT_GRAY);
    JScrollPane javaCommandLinePreviewSP = new JScrollPane(_javaCommandLinePreview);
    javaCommandLinePreviewSP.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    gridbag.setConstraints(javaCommandLinePreviewSP, c);
    main.add(javaCommandLinePreviewSP);
    c.weightx = 0.0;
    c.weighty = 0.0;
    c.gridwidth = 1;
    c.insets = labelInsets;
    JLabel javaWorkDirLabel = new JLabel("Work directory:");
    gridbag.setConstraints(javaWorkDirLabel, c);
    main.add(javaWorkDirLabel);
    c.weightx = 1.0;
    c.weighty = 12.0;
    c.gridwidth = GridBagConstraints.RELATIVE;
    c.insets = compInsets;
    _javaCommandWorkDirLine = new JTextPane();
    _javaCommandWorkDirLine.addKeyListener(
        new KeyListener() {
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              e.consume();
            } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
              e.consume();
              if (e.isShiftDown()) {
                _javaCommandLine.requestFocus();
              } else {
                _insertJavaButton.setEnabled(false);
                _runJavaButton.requestFocus();
              }
            }
          }

          public void keyReleased(KeyEvent e) {}

          public void keyTyped(KeyEvent e) {}
        });
    JScrollPane javaCommandWorkDirLineSP = new JScrollPane(_javaCommandWorkDirLine);
    javaCommandWorkDirLineSP.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    gridbag.setConstraints(javaCommandWorkDirLineSP, c);
    main.add(javaCommandWorkDirLineSP);
    c.weightx = 0.0;
    c.weighty = 0.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = compInsets;
    _javaCommandWorkDirBtn = new JButton("...");
    _javaCommandWorkDirBtn.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            chooseFile(_javaCommandWorkDirLine);
          }
        });
    gridbag.setConstraints(_javaCommandWorkDirBtn, c);
    main.add(_javaCommandWorkDirBtn);
    c.weightx = 0.0;
    c.weighty = 0.0;
    c.gridwidth = 1;
    c.insets = labelInsets;
    JLabel javaCommandWorkDirLinePreviewLabel = new JLabel("Work directory preview:");
    javaCommandWorkDirLinePreviewLabel.setToolTipText(STALE_TOOLTIP);
    gridbag.setConstraints(javaCommandWorkDirLinePreviewLabel, c);
    main.add(javaCommandWorkDirLinePreviewLabel);
    c.weightx = 1.0;
    c.weighty = 12.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = compInsets;
    _javaCommandWorkDirLinePreview = new JTextPane();
    _javaCommandWorkDirLinePreview.setToolTipText(STALE_TOOLTIP);
    _javaCommandWorkDirLineDoc = ((StyledDocument) (_javaCommandWorkDirLinePreview.getDocument()));
    _javaCommandWorkDirLinePreview.setEditable(false);
    _javaCommandWorkDirLinePreview.setBackground(Color.LIGHT_GRAY);
    _javaCommandWorkDirLinePreview.setSelectedTextColor(Color.LIGHT_GRAY);
    JScrollPane javaCommandWorkDirLinePreviewSP = new JScrollPane(_javaCommandWorkDirLinePreview);
    javaCommandWorkDirLinePreviewSP.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    gridbag.setConstraints(javaCommandWorkDirLinePreviewSP, c);
    main.add(javaCommandWorkDirLinePreviewSP);
    panel.add(main, BorderLayout.CENTER);
    JPanel bottom = new JPanel();
    bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
    bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
    bottom.add(Box.createHorizontalGlue());
    if (!_editMode) {
      bottom.add(_runJavaButton);
    }
    bottom.add(_saveJavaButton);
    bottom.add(_insertJavaButton);
    bottom.add(_cancelJavaButton);
    bottom.add(Box.createHorizontalGlue());
    panel.add(bottom, BorderLayout.SOUTH);
    _javaDocumentListener =
        new DocumentListener() {
          public void update(DocumentEvent e) {
            try {
              _javaCommandLineDoc.remove(0, _javaCommandLineDoc.getLength());
              StringBuilder sb = new StringBuilder();
              sb.append(ExecJVM.getExecutable());
              sb.append(' ');
              _javaCommandLineDoc.insertString(
                  _javaCommandLineDoc.getLength(), sb.toString(), _javaCommandLineExecutableStyle);
              sb = new StringBuilder();
              String text = StringOps.replaceVariables(_jvmLine.getText(), ONLY, TO_STRING);
              List<String> cmds = StringOps.commandLineToList(text);
              for (String s : cmds) {
                sb.append(s);
                sb.append(' ');
              }
              _javaCommandLineDoc.insertString(
                  _javaCommandLineDoc.getLength(), sb.toString(), _javaCommandLineJVMStyle);
              sb = new StringBuilder();
              text = StringOps.replaceVariables(_javaCommandLine.getText(), ONLY, TO_STRING);
              cmds = StringOps.commandLineToList(text);
              for (String s : cmds) {
                sb.append(s);
                sb.append(' ');
              }
              _javaCommandLineDoc.insertString(
                  _javaCommandLineDoc.getLength(), sb.toString(), null);
              colorVariables(
                  _jvmLine,
                  ONLY,
                  this,
                  _javaCommandLineJVMAS,
                  _javaVarCommandLineJVMStyle,
                  _javaVarErrorCommandLineJVMStyle);
              colorVariables(
                  _javaCommandLine,
                  ONLY,
                  this,
                  _javaCommandLineCmdAS,
                  _javaVarCommandLineCmdStyle,
                  _javaVarErrorCommandLineCmdStyle);
            } catch (BadLocationException ble) {
              _javaCommandLinePreview.setText("Error.");
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
    _javaCommandLine.getDocument().addDocumentListener(_javaDocumentListener);
    _jvmLine.getDocument().addDocumentListener(_javaDocumentListener);
    _javaDocumentListener.changedUpdate(null);
    _javaWorkDirDocumentListener =
        new DocumentListener() {
          public void update(DocumentEvent e) {
            try {
              _javaCommandWorkDirLineDoc.remove(0, _javaCommandWorkDirLineDoc.getLength());
              String text =
                  StringOps.replaceVariables(_javaCommandWorkDirLine.getText(), ONLY, TO_STRING);
              _javaCommandWorkDirLineDoc.insertString(0, text, null);
              colorVariables(
                  _javaCommandWorkDirLine,
                  ONLY,
                  this,
                  _javaCommandLineCmdAS,
                  _javaVarCommandLineCmdStyle,
                  _javaVarErrorCommandLineCmdStyle);
            } catch (BadLocationException ble) {
              _javaCommandWorkDirLinePreview.setText("Error: " + ble);
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
    _javaCommandWorkDirLine.getDocument().addDocumentListener(_javaWorkDirDocumentListener);
    _javaCommandWorkDirLine.setText("${drjava.working.dir}");
    _javaWorkDirDocumentListener.changedUpdate(null);
    DrJava.getConfig()
        .addOptionListener(
            DEFINITIONS_COMMENT_COLOR,
            new OptionListener<Color>() {
              public void optionChanged(OptionEvent<Color> oce) {
                StyleConstants.setForeground(_javaCommandLineExecutableStyle, oce.value);
                _javaDocumentListener.changedUpdate(null);
              }
            });
    DrJava.getConfig()
        .addOptionListener(
            DEFINITIONS_KEYWORD_COLOR,
            new OptionListener<Color>() {
              public void optionChanged(OptionEvent<Color> oce) {
                StyleConstants.setForeground(_javaCommandLineJVMStyle, oce.value);
                _javaDocumentListener.changedUpdate(null);
              }
            });
    _lastJavaFocus = _javaCommandLine;
    _javaCommandLine.addFocusListener(
        new FocusAdapter() {
          @SuppressWarnings("unchecked")
          public void focusGained(FocusEvent e) {
            _lastJavaFocus = ((JTextPane) (e.getComponent()));
            _insertJavaButton.setEnabled(true);
          }

          public void focusLost(FocusEvent e) {
            if ((e.getOppositeComponent() == _javaCommandLinePreview)
                || (e.getOppositeComponent() == _javaCommandWorkDirLinePreview)) {
              _javaCommandLine.requestFocus();
            }
          }
        });
    _jvmLine.addFocusListener(
        new FocusAdapter() {
          @SuppressWarnings("unchecked")
          public void focusGained(FocusEvent e) {
            _lastJavaFocus = ((JTextPane) (e.getComponent()));
            _insertJavaButton.setEnabled(true);
          }

          public void focusLost(FocusEvent e) {
            if ((e.getOppositeComponent() == _javaCommandLinePreview)
                || (e.getOppositeComponent() == _javaCommandWorkDirLinePreview)) {
              _jvmLine.requestFocus();
            }
          }
        });
    _javaCommandWorkDirLine.addFocusListener(
        new FocusAdapter() {
          @SuppressWarnings("unchecked")
          public void focusGained(FocusEvent e) {
            _lastJavaFocus = ((JTextPane) (e.getComponent()));
            _insertJavaButton.setEnabled(true);
          }

          public void focusLost(FocusEvent e) {
            if ((e.getOppositeComponent() == _javaCommandLinePreview)
                || (e.getOppositeComponent() == _javaCommandWorkDirLinePreview)) {
              _javaCommandWorkDirLine.requestFocus();
            }
          }
        });
    return panel;
  }
}

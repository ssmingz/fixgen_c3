class PlaceHold {
  protected void _init() {
    setLayout(new BorderLayout());
    javax.swing.JPanel panel = new javax.swing.JPanel(new GridLayout(2, 1));
    /* Show toolbar */
    showToolbar = new javax.swing.JCheckBox(jEdit.getProperty("options.toolbar.showToolbar"));
    showToolbar.setSelected(jEdit.getBooleanProperty("view.showToolbar"));
    panel.add(showToolbar);
    panel.add(new javax.swing.JLabel(jEdit.getProperty("options.toolbar.caption")));
    add(BorderLayout.NORTH, panel);
    String toolbar = jEdit.getProperty("view.toolbar");
    StringTokenizer st = new StringTokenizer(toolbar);
    listModel = new javax.swing.DefaultListModel();
    while (st.hasMoreTokens()) {
      String actionName = st.nextToken();
      if (actionName.equals("-"))
        listModel.addElement(
            new org.gjt.sp.jedit.options.ToolBarOptionPane.Button("-", null, null, "-"));
      else {
        EditAction action = jEdit.getAction(actionName);
        if (action == null) continue;

        String label = action.getLabel();
        if (label == null) continue;

        javax.swing.Icon icon;
        String iconName;
        if (actionName.equals("-")) {
          iconName = null;
          icon = null;
        } else {
          iconName = jEdit.getProperty(actionName + ".icon");
          if (iconName == null) icon = GUIUtilities.loadIcon("BrokenImage.png");
          else {
            icon = GUIUtilities.loadIcon(iconName);
            if (icon == null) icon = GUIUtilities.loadIcon("BrokenImage.png");
          }
        }
        listModel.addElement(new Button(actionName, iconName, icon, label));
      }
    }
    list = new javax.swing.JList(listModel);
    list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    list.addListSelectionListener(new ListHandler());
    list.setCellRenderer(new ButtonCellRenderer());
    add(BorderLayout.CENTER, new javax.swing.JScrollPane(list));
    // {{{ Create buttons
    javax.swing.JPanel buttons = new javax.swing.JPanel();
    buttons.setBorder(new javax.swing.border.EmptyBorder(3, 0, 0, 0));
    buttons.setLayout(new javax.swing.BoxLayout(buttons, javax.swing.BoxLayout.X_AXIS));
    ActionHandler actionHandler = new ActionHandler();
    add = new RolloverButton(GUIUtilities.loadIcon("Plus.png"));
    add.setToolTipText(jEdit.getProperty("options.toolbar.add"));
    add.addActionListener(actionHandler);
    buttons.add(add);
    buttons.add(javax.swing.Box.createHorizontalStrut(6));
    remove = new RolloverButton(GUIUtilities.loadIcon("Minus.png"));
    remove.setToolTipText(jEdit.getProperty("options.toolbar.remove"));
    remove.addActionListener(actionHandler);
    buttons.add(remove);
    buttons.add(javax.swing.Box.createHorizontalStrut(6));
    moveUp = new RolloverButton(GUIUtilities.loadIcon("ArrowU.png"));
    moveUp.setToolTipText(jEdit.getProperty("options.toolbar.moveUp"));
    moveUp.addActionListener(actionHandler);
    buttons.add(moveUp);
    buttons.add(javax.swing.Box.createHorizontalStrut(6));
    moveDown = new RolloverButton(GUIUtilities.loadIcon("ArrowD.png"));
    moveDown.setToolTipText(jEdit.getProperty("options.toolbar.moveDown"));
    moveDown.addActionListener(actionHandler);
    buttons.add(moveDown);
    buttons.add(javax.swing.Box.createHorizontalStrut(6));
    edit = new RolloverButton(GUIUtilities.loadIcon("ButtonProperties.png"));
    edit.setToolTipText(jEdit.getProperty("options.toolbar.edit"));
    edit.addActionListener(actionHandler);
    buttons.add(edit);
    buttons.add(javax.swing.Box.createGlue());
    // }}}
    updateButtons();
    add(BorderLayout.SOUTH, buttons);
    // {{{ Ceate icons list
    iconList = new javax.swing.DefaultComboBoxModel();
    st = new StringTokenizer(jEdit.getProperty("icons"));
    while (st.hasMoreElements()) {
      String icon = st.nextToken();
      iconList.addElement(new IconListEntry(GUIUtilities.loadIcon(icon), icon));
    } // }}}
  }
}

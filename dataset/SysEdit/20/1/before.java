class PlaceHold {
  protected void _init() {
    setLayout(new BorderLayout());

    JPanel panel = new JPanel(new GridLayout(2, 1));

    /* Show toolbar */
    showToolbar = new JCheckBox(jEdit.getProperty("options.toolbar.showToolbar"));
    showToolbar.setSelected(jEdit.getBooleanProperty("view.showToolbar"));
    panel.add(showToolbar);

    panel.add(new JLabel(jEdit.getProperty("options.toolbar.caption")));

    add(BorderLayout.NORTH, panel);

    String toolbar = jEdit.getProperty("view.toolbar");
    StringTokenizer st = new StringTokenizer(toolbar);
    listModel = new DefaultListModel();
    while (st.hasMoreTokens()) {
      String actionName = (String) st.nextToken();
      if (actionName.equals("-"))
        listModel.addElement(new ToolBarOptionPane.Button("-", null, null, "-"));
      else {
        EditAction action = jEdit.getAction(actionName);
        if (action == null) continue;
        String label = action.getLabel();
        if (label == null) continue;

        Icon icon;
        String iconName;
        if (actionName.equals("-")) {
          iconName = null;
          icon = null;
        } else {
          iconName = jEdit.getProperty(actionName + ".icon");
          if (iconName == null) continue;

          icon = GUIUtilities.loadIcon(iconName);
        }
        listModel.addElement(new Button(actionName, iconName, icon, label));
      }
    }

    list = new JList(listModel);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.addListSelectionListener(new ListHandler());
    list.setCellRenderer(new ButtonCellRenderer());

    add(BorderLayout.CENTER, new JScrollPane(list));

    JPanel buttons = new JPanel();
    buttons.setBorder(new EmptyBorder(3, 0, 0, 0));
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
    buttons.add(Box.createGlue());
    ActionHandler actionHandler = new ActionHandler();
    add = new JButton(jEdit.getProperty("options.toolbar.add"));
    add.addActionListener(actionHandler);
    buttons.add(add);
    buttons.add(Box.createHorizontalStrut(6));
    remove = new JButton(jEdit.getProperty("options.toolbar.remove"));
    remove.addActionListener(actionHandler);
    buttons.add(remove);
    buttons.add(Box.createHorizontalStrut(6));
    moveUp = new JButton(jEdit.getProperty("options.toolbar.moveUp"));
    moveUp.addActionListener(actionHandler);
    buttons.add(moveUp);
    buttons.add(Box.createHorizontalStrut(6));
    moveDown = new JButton(jEdit.getProperty("options.toolbar.moveDown"));
    moveDown.addActionListener(actionHandler);
    buttons.add(moveDown);
    buttons.add(Box.createGlue());

    updateButtons();
    add(BorderLayout.SOUTH, buttons);

    iconList = new DefaultComboBoxModel();
    st = new StringTokenizer(jEdit.getProperty("icons"));
    while (st.hasMoreElements()) {
      String icon = st.nextToken();
      iconList.addElement(new IconListEntry(GUIUtilities.loadIcon(icon), icon));
    }
  }
}

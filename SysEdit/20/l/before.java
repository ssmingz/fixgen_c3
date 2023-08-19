class PlaceHold {
  protected void _init() {
    setLayout(new BorderLayout());
    javax.swing.JLabel caption =
        new javax.swing.JLabel(jEdit.getProperty("options.context.caption"));
    add(BorderLayout.NORTH, caption);
    String contextMenu = jEdit.getProperty("view.context");
    StringTokenizer st = new StringTokenizer(contextMenu);
    listModel = new javax.swing.DefaultListModel();
    while (st.hasMoreTokens()) {
      String actionName = ((String) (st.nextToken()));
      if (actionName.equals("-"))
        listModel.addElement(new org.gjt.sp.jedit.options.ContextOptionPane.MenuItem("-", "-"));
      else {
        EditAction action = jEdit.getAction(actionName);
        if (action == null) continue;

        String label = action.getLabel();
        if (label == null) continue;

        listModel.addElement(
            new org.gjt.sp.jedit.options.ContextOptionPane.MenuItem(actionName, label));
      }
    }
    list = new javax.swing.JList(listModel);
    list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    list.addListSelectionListener(new ListHandler());
    add(BorderLayout.CENTER, new javax.swing.JScrollPane(list));
    javax.swing.JPanel buttons = new javax.swing.JPanel();
    buttons.setBorder(new javax.swing.border.EmptyBorder(3, 0, 0, 0));
    buttons.setLayout(new javax.swing.BoxLayout(buttons, javax.swing.BoxLayout.X_AXIS));
    buttons.add(javax.swing.Box.createGlue());
    ActionHandler actionHandler = new ActionHandler();
    add = new javax.swing.JButton(jEdit.getProperty("options.context.add"));
    add.addActionListener(actionHandler);
    buttons.add(add);
    buttons.add(javax.swing.Box.createHorizontalStrut(6));
    remove = new javax.swing.JButton(jEdit.getProperty("options.context.remove"));
    remove.addActionListener(actionHandler);
    buttons.add(remove);
    buttons.add(javax.swing.Box.createHorizontalStrut(6));
    moveUp = new javax.swing.JButton(jEdit.getProperty("options.context.moveUp"));
    moveUp.addActionListener(actionHandler);
    buttons.add(moveUp);
    buttons.add(javax.swing.Box.createHorizontalStrut(6));
    moveDown = new javax.swing.JButton(jEdit.getProperty("options.context.moveDown"));
    moveDown.addActionListener(actionHandler);
    buttons.add(moveDown);
    buttons.add(javax.swing.Box.createGlue());
    updateButtons();
    add(BorderLayout.SOUTH, buttons);
  }
}

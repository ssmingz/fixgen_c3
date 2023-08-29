class PlaceHold {
  protected void _init() {
    setLayout(new BorderLayout());

    JLabel caption = new JLabel(jEdit.getProperty("options.context.caption"));
    add(BorderLayout.NORTH, caption);

    String contextMenu = jEdit.getProperty("view.context");
    StringTokenizer st = new StringTokenizer(contextMenu);
    listModel = new DefaultListModel();
    while (st.hasMoreTokens()) {
      String actionName = (String) st.nextToken();
      if (actionName.equals("-")) listModel.addElement(new ContextOptionPane.MenuItem("-", "-"));
      else {
        EditAction action = jEdit.getAction(actionName);
        if (action == null) continue;
        String label = action.getLabel();
        if (label == null) continue;
        listModel.addElement(new ContextOptionPane.MenuItem(actionName, label));
      }
    }
    list = new JList(listModel);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.addListSelectionListener(new ListHandler());

    add(BorderLayout.CENTER, new JScrollPane(list));

    JPanel buttons = new JPanel();
    buttons.setBorder(new EmptyBorder(3, 0, 0, 0));
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
    buttons.add(Box.createGlue());
    ActionHandler actionHandler = new ActionHandler();
    add = new JButton(jEdit.getProperty("options.context.add"));
    add.addActionListener(actionHandler);
    buttons.add(add);
    buttons.add(Box.createHorizontalStrut(6));
    remove = new JButton(jEdit.getProperty("options.context.remove"));
    remove.addActionListener(actionHandler);
    buttons.add(remove);
    buttons.add(Box.createHorizontalStrut(6));
    moveUp = new JButton(jEdit.getProperty("options.context.moveUp"));
    moveUp.addActionListener(actionHandler);
    buttons.add(moveUp);
    buttons.add(Box.createHorizontalStrut(6));
    moveDown = new JButton(jEdit.getProperty("options.context.moveDown"));
    moveDown.addActionListener(actionHandler);
    buttons.add(moveDown);
    buttons.add(Box.createGlue());

    updateButtons();
    add(BorderLayout.SOUTH, buttons);
  }
}

class PlaceHold {
  protected void _init() {
    setLayout(new BorderLayout());

    JLabel caption = new JLabel(jEdit.getProperty("options.context.caption"));
    add(BorderLayout.NORTH, caption);

    String contextMenu = jEdit.getProperty("view.context");
    StringTokenizer st = new StringTokenizer(contextMenu);
    listModel = new DefaultListModel();
    while (st.hasMoreTokens()) {
      String actionName = st.nextToken();
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
    ActionHandler actionHandler = new ActionHandler();
    add = new RolloverButton(GUIUtilities.loadIcon("Plus.png"));
    add.setToolTipText(jEdit.getProperty("common.add"));
    add.addActionListener(actionHandler);
    buttons.add(add);
    buttons.add(Box.createHorizontalStrut(6));
    remove = new RolloverButton(GUIUtilities.loadIcon("Minus.png"));
    remove.setToolTipText(jEdit.getProperty("common.remove"));
    remove.addActionListener(actionHandler);
    buttons.add(remove);
    buttons.add(Box.createHorizontalStrut(6));
    moveUp = new RolloverButton(GUIUtilities.loadIcon("ArrowU.png"));
    moveUp.setToolTipText(jEdit.getProperty("common.moveUp"));
    moveUp.addActionListener(actionHandler);
    buttons.add(moveUp);
    buttons.add(Box.createHorizontalStrut(6));
    moveDown = new RolloverButton(GUIUtilities.loadIcon("ArrowD.png"));
    moveDown.setToolTipText(jEdit.getProperty("common.moveDown"));
    moveDown.addActionListener(actionHandler);
    buttons.add(moveDown);
    buttons.add(Box.createGlue());

    updateButtons();
    add(BorderLayout.SOUTH, buttons);
  }
}

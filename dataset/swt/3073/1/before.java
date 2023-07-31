class ControlExample {
  public ControlExample(Composite parent) {
    initResources();
    tabFolder = new TabFolder(parent, SWT.NULL);
    Tab[] tabs =
        new Tab[] {
          new ButtonTab(this),
          new ComboTab(this),
          new CComboTab(this),
          new CoolBarTab(this),
          new CTabFolderTab(this),
          new DialogTab(this),
          new LabelTab(this),
          new CLabelTab(this),
          new ListTab(this),
          new ProgressBarTab(this),
          new SashTab(this),
          shellTab = new ShellTab(this),
          new SliderTab(this),
          new TableTab(this),
          new TextTab(this),
          new ToolBarTab(this),
          new TreeTab(this)
        };
    for (int i = 0; i < tabs.length; i++) {
      TabItem item = new TabItem(tabFolder, SWT.NULL);
      item.setText(tabs[i].getTabText());
      item.setControl(tabs[i].createTabFolderPage(tabFolder));
    }
  }
}

class PlaceHold {
  Tab[] createTabs() {
    return new Tab[] {
      new ButtonTab(this),
      new ComboTab(this),
      new CoolBarTab(this),
      new DialogTab(this),
      new LabelTab(this),
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
  }
}

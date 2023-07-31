class PlaceHold {
  Tab[] createTabs() {
    return new Tab[] {
      new ButtonTab(this),
      new CanvasTab(this),
      new ComboTab(this),
      new CoolBarTab(this),
      new DateTimeTab(this),
      new DialogTab(this),
      new ExpandBarTab(this),
      new GroupTab(this),
      new LabelTab(this),
      new LinkTab(this),
      new ListTab(this),
      new MenuTab(this),
      new ProgressBarTab(this),
      new SashTab(this),
      new ScaleTab(this),
      shellTab = new ShellTab(this),
      new SliderTab(this),
      new SpinnerTab(this),
      new TabFolderTab(this),
      new TableTab(this),
      new TextTab(this),
      new ToolBarTab(this),
      new TreeTab(this)
    };
  }
}

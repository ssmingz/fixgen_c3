class PlaceHold {
  Tab[] createTabs() {
    return new Tab[] {
      new CComboTab(this),
      new CTabFolderTab(this),
      new CLabelTab(this),
      new SashFormTab(this),
      new TableTreeTab(this)
    };
  }
}

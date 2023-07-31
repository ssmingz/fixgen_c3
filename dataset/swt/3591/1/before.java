class MenuItem {
  MenuItem(Menu parent, Menu menu, int style, int index) {
    super(parent, checkStyle(style));
    this.parent = parent;
    this.menu = menu;
    if (menu != null) {
      menu.cascade = this;
    }
    Decorations shell = parent.parent;
    shell.add(this);
  }
}

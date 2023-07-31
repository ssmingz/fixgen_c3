class Menu {
  public Menu(Control parent) {
    this(checkNull(parent).getShell(), POP_UP);
  }
}

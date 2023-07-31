class PlaceHold {
  static TaskBarItem getTaskBarItem() {
    TaskBar bar = display.getSystemTaskBar();
    if (bar == null) {
      return null;
    }
    TaskBarItem item = bar.getItem(null);
    if (item == null) {
      item = bar.getItem(shell);
    }
    return item;
  }
}

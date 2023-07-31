class PlaceHold {
  static TaskBarItem getTaskBarItem() {
    TaskBar bar = display.getSystemTaskBar();
    TaskBarItem item = bar.getItem(null);
    if (item == null) {
      item = bar.getItem(shell);
    }
    return item;
  }
}

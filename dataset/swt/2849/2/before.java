class PlaceHold {
  boolean traverseGroup(boolean next) {
    Control root = computeTabRoot();
    Control group = computeTabGroup();
    Control[] list = root.computeTabList();
    int length = list.length;
    int index = 0;
    while (index < length) {
      if (list[index] == group) {
        break;
      }
      index++;
    }
    if (index == length) {
      return false;
    }
    int start = index;
    int offset = (next) ? 1 : -1;
    while ((index = ((index + offset) + length) % length) != start) {
      Control control = list[index];
      if ((!control.isDisposed()) && control.setTabGroupFocus(next)) {
        return true;
      }
    }
    if (group.isDisposed()) {
      return false;
    }
    return group.setTabGroupFocus(next);
  }
}

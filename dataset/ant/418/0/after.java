class PlaceHold {
  private void accountForIncludedDir(String name, File file, boolean fast) {
    if ((dirsIncluded.contains(name) || dirsExcluded.contains(name))
        || dirsDeselected.contains(name)) {
      return;
    }
    boolean included = false;
    if (isExcluded(name)) {
      dirsExcluded.addElement(name);
    } else if (isSelected(name, file)) {
      included = true;
      dirsIncluded.addElement(name);
    } else {
      dirsDeselected.addElement(name);
    }
    everythingIncluded &= included;
    if ((fast && couldHoldIncluded(name)) && (!contentsExcluded(name))) {
      scandir(file, name + File.separator, fast);
    }
  }
}

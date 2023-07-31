class PlaceHold {
  boolean sendHelpEvent(int helpType) {
    Control control = this;
    while (control != null) {
      if (control.hooks(Help)) {
        control.postEvent(Help);
        return true;
      }
      control = control.parent;
    }
    return false;
  }
}

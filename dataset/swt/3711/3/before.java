class PlaceHold {
  void sendHelpEvent(int helpType) {
    Control control = this;
    while (control != null) {
      if (control.hooks(Help)) {
        control.postEvent(Help);
        return;
      }
      control = control.parent;
    }
  }
}

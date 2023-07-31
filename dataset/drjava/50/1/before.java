class PlaceHold {
  void findNext() {
    _machine.setSearchBackwards(false);
    _findLabelBot.setText("Next");
    _doFind();
  }
}

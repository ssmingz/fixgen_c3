class PlaceHold {
  void findPrevious() {
    _machine.setSearchBackwards(true);
    _findLabelBot.setText("Prev");
    _doFind();
  }
}

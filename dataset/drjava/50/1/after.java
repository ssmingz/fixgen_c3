class PlaceHold {
  void findNext() {
    _frame.updateStatusField("Finding Next");
    _machine.setSearchBackwards(false);
    _findLabelBot.setText("Next");
    _doFind();
  }
}

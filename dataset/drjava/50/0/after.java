class PlaceHold {
  void findPrevious() {
    _frame.updateStatusField("Finding Previous");
    _machine.setSearchBackwards(true);
    _findLabelBot.setText("Prev");
    _doFind();
  }
}

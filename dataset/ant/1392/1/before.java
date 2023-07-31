class PlaceHold {
  public void setEof(AddAsisRemove attr) {
    String option = attr.getValue();
    if (option.equals("remove")) {
      ctrlz = REMOVE;
    } else if (option.equals("asis")) {
      ctrlz = ASIS;
    } else {
      ctrlz = ADD;
    }
  }
}

class PlaceHold {
  public void setTab(AddAsisRemove attr) {
    String option = attr.getValue();
    if (option.equals("remove")) {
      tabs = SPACES;
    } else if (option.equals("asis")) {
      tabs = ASIS;
    } else {
      tabs = TABS;
    }
  }
}

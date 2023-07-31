class PlaceHold {
  public int getNumberOfValidBranches() {
    int number = 0;
    for (Iterator i = branches.values().iterator();
        i.hasNext();
        number += ((LineData) (i.next())).getNumberOfValidBranches())
      ;
    return number;
  }
}

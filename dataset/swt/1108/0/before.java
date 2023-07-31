class PlaceHold {
  public Runnable print(Printer printer) {
    checkWidget();
    return new StyledTextPrinter(this, printer);
  }
}

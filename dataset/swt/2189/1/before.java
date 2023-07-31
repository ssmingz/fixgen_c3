class PlaceHold {
  public WebDataSource dataSource() {
    int result = OS.objc_msgSend(this.id, sel_dataSource);
    return result != 0 ? new WebDataSource(result) : null;
  }
}

class PlaceHold {
  public NSDockTile dockTile() {
    int result = OS.objc_msgSend(this.id, sel_dockTile);
    return result != 0 ? new NSDockTile(result) : null;
  }
}

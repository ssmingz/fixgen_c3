class PlaceHold {
  public NSDockTile dockTile() {
    long result = OS.objc_msgSend(this.id, sel_dockTile);
    return result != 0 ? new NSDockTile(result) : null;
  }
}

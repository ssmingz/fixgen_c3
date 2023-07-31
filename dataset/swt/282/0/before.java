class PlaceHold {
  public void add(Rectangle rect) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (rect == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if ((rect.width < 0) || (rect.height < 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (handle == 0) {
      return;
    }
    int tile_ptr = OS.PhGetTile();
    PhTile_t tile = new PhTile_t();
    tile.rect_ul_x = ((short) (rect.x));
    tile.rect_ul_y = ((short) (rect.y));
    tile.rect_lr_x = ((short) ((rect.x + rect.width) - 1));
    tile.rect_lr_y = ((short) ((rect.y + rect.height) - 1));
    OS.memmove(tile_ptr, tile, sizeof);
    if (handle == EMPTY_REGION) {
      handle = tile_ptr;
    } else {
      handle = OS.PhAddMergeTiles(handle, tile_ptr, null);
    }
  }
}

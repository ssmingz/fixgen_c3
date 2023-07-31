class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((type != SWT.BITMAP) || (memGC != null)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    PhImage_t phImage = new PhImage_t();
    OS.memmove(phImage, handle, sizeof);
    PhDim_t dim = new PhDim_t();
    dim.w = phImage.size_w;
    dim.h = phImage.size_h;
    PhPoint_t trans = new PhPoint_t();
    int pmMC = OS.PmMemCreateMC(handle, dim, trans);
    if (pmMC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    data.device = device;
    data.image = this;
    return pmMC;
  }
}

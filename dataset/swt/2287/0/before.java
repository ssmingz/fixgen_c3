class PlaceHold {
  int createArrowImage() {
    short width = 5;
    short height = 4;
    int image = OS.PhCreateImage(null, width, height, Pg_IMAGE_DIRECT_888, 0, 0, 0);
    if (image == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    PhDim_t dim = new PhDim_t();
    dim.w = width;
    dim.h = height;
    int mc = OS.PmMemCreateMC(image, dim, new PhPoint_t());
    if (mc == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.PmMemStart(mc);
    OS.PgSetFillColor(0xffffff);
    OS.PgDrawIRect(0, 0, width, height, Pg_DRAW_FILL);
    OS.PgSetStrokeColor(0x0);
    OS.PgSetFillColor(0x0);
    short[] points =
        new short[] {
          ((short) (0)), ((short) (1)), ((short) (2)), ((short) (3)), ((short) (4)), ((short) (1))
        };
    OS.PgDrawPolygon(
        points,
        points.length / 2,
        new PhPoint_t(),
        (OS.Pg_DRAW_FILL | OS.Pg_DRAW_STROKE) | OS.Pg_CLOSED);
    OS.PmMemFlush(mc, image);
    OS.PmMemStop(mc);
    OS.PmMemReleaseMC(mc);
    OS.PhMakeTransBitmap(image, 0xffffff);
    return image;
  }
}

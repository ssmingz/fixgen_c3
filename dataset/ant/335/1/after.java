class PlaceHold {
  public int read() throws IOException {
    int ch = -1;
    if ((queuedData != null) && (queuedData.length() == 0)) {
      queuedData = null;
    }
    if (queuedData != null) {
      ch = queuedData.charAt(0);
      queuedData = queuedData.substring(1);
      if (queuedData.length() == 0) {
        queuedData = null;
      }
    } else {
      final String clazz = readFully();
      if ((clazz == null) || (clazz.length() == 0)) {
        ch = -1;
      } else {
        final byte[] bytes = clazz.getBytes(ISO_8859_1);
        try {
          final Class javaClassHelper = Class.forName(JAVA_CLASS_HELPER);
          if (javaClassHelper != null) {
            final Class[] params = new Class[] {byte[].class};
            final Method getConstants = javaClassHelper.getMethod("getConstants", params);
            final Object[] args = new Object[] {bytes};
            final StringBuffer sb = ((StringBuffer) (getConstants.invoke(null, args)));
            if (sb.length() > 0) {
              queuedData = sb.toString();
              return read();
            }
          }
        } catch (NoClassDefFoundError ex) {
          throw ex;
        } catch (RuntimeException ex) {
          throw ex;
        } catch (InvocationTargetException ex) {
          Throwable t = ex.getTargetException();
          if (t instanceof NoClassDefFoundError) {
            throw ((NoClassDefFoundError) (t));
          }
          if (t instanceof RuntimeException) {
            throw ((RuntimeException) (t));
          }
          throw new BuildException(t);
        } catch (Exception ex) {
          throw new BuildException(ex);
        }
      }
    }
    return ch;
  }
}

class PlaceHold {
  private Driver getDriver() throws BuildException {
    if (driver == null) {
      throw new BuildException("Driver attribute must be set!", getLocation());
    }
    Driver driverInstance = null;
    try {
      Class dc;
      if (classpath != null) {
        synchronized (loaderMap) {
          if (caching) {
            loader = ((AntClassLoader) (loaderMap.get(driver)));
          }
          if (loader == null) {
            log(
                (("Loading " + driver) + " using AntClassLoader with classpath ") + classpath,
                MSG_VERBOSE);
            loader = new AntClassLoader(getProject(), classpath);
            if (caching) {
              loaderMap.put(driver, loader);
            }
          } else {
            log(("Loading " + driver) + " using a cached AntClassLoader.", MSG_VERBOSE);
          }
        }
        dc = loader.loadClass(driver);
      } else {
        log(("Loading " + driver) + " using system loader.", MSG_VERBOSE);
        dc = Class.forName(driver);
      }
      driverInstance = ((Driver) (dc.newInstance()));
    } catch (ClassNotFoundException e) {
      throw new BuildException(
          ("Class Not Found: JDBC driver " + driver) + " could not be loaded", getLocation());
    } catch (IllegalAccessException e) {
      throw new BuildException(
          ("Illegal Access: JDBC driver " + driver) + " could not be loaded", getLocation());
    } catch (InstantiationException e) {
      throw new BuildException(
          ("Instantiation Exception: JDBC driver " + driver) + " could not be loaded",
          getLocation());
    }
    return driverInstance;
  }
}

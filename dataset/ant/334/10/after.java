class PlaceHold {
  private boolean generateChecksums() throws BuildException {
    boolean checksumMatches = true;
    FileInputStream fis = null;
    FileOutputStream fos = null;
    try {
      for (Enumeration e = includeFileMap.keys(); e.hasMoreElements(); ) {
        messageDigest.reset();
        File src = ((File) (e.nextElement()));
        if (!isCondition) {
          log((("Calculating " + algorithm) + " checksum for ") + src);
        }
        fis = new FileInputStream(src);
        DigestInputStream dis = new DigestInputStream(fis, messageDigest);
        while (dis.read() != (-1))
          ;
        dis.close();
        fis.close();
        fis = null;
        byte[] fileDigest = messageDigest.digest();
        String checksum = "";
        for (int i = 0; i < fileDigest.length; i++) {
          String hexStr = Integer.toHexString(0xff & fileDigest[i]);
          if (hexStr.length() < 2) {
            checksum += "0";
          }
          checksum += hexStr;
        }
        Object destination = includeFileMap.get(src);
        if (destination instanceof String) {
          String prop = ((String) (destination));
          if (isCondition) {
            checksumMatches = checksum.equals(property);
          } else {
            project.setProperty(prop, checksum);
          }
        } else if (destination instanceof File) {
          if (isCondition) {
            File existingFile = ((File) (destination));
            if (existingFile.exists() && (existingFile.length() == checksum.length())) {
              fis = new FileInputStream(existingFile);
              InputStreamReader isr = new InputStreamReader(fis);
              BufferedReader br = new BufferedReader(isr);
              String suppliedChecksum = br.readLine();
              fis.close();
              fis = null;
              br.close();
              isr.close();
              checksumMatches = checksum.equals(suppliedChecksum);
            } else {
              checksumMatches = false;
            }
          } else {
            File dest = ((File) (destination));
            fos = new FileOutputStream(dest);
            fos.write(checksum.getBytes());
            fos.close();
            fos = null;
          }
        }
      }
    } catch (Exception e) {
      throw new BuildException("Error", e);
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
        }
      }
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
        }
      }
    }
    return checksumMatches;
  }
}

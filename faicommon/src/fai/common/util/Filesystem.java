package fai.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;

import it.swdes.servlet.zip4j.io.ZipOutputStream;



public class Filesystem {
  
  
  static Logger logger = Logger.getLogger(Filesystem.class);


  public static String getFullPath(String dir, String filename) throws Exception {
    if (dir.endsWith(File.separator)) {
      dir = dir.substring(0, dir.length()-File.separator.length());
    }
    return dir + File.separator + filename;
  }
  
  public static String suppressExt(String filename)  {
    return chengeExt(filename, null);
  }

  
  public static String chengeExt(String filename, String newExt)  {
    int idx = filename.lastIndexOf(".");
    if (idx >= 0) {
      filename = filename.substring(0, idx);
    }
    return filename + (newExt == null ? "" : "."+newExt);
  }

  
  
  public static void store(String content, File targetFile) throws Exception {
    
    Writer writer = getWriter(targetFile);
    writer.write(content);
    writer.flush();
    writer.close();
  }

  public static void store(byte [] b, File targetFile) throws Exception {
    FileOutputStream fos = new FileOutputStream(targetFile);
    fos.write(b);
    fos.flush();
    fos.close();
  }

  /**
   * @param targetFile
   * @return
   * @throws FileNotFoundException
   */
  public static Writer getWriter(File targetFile) throws FileNotFoundException {
    OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(targetFile));
    return writer;
  }

  public static List<String> getContentAsTextLines(File f) throws Exception {
    return getContentAsTextLines(new FileInputStream(f));
  }
  public static List<String> getContentAsTextLines(InputStream is) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    List<String> lines = new ArrayList<String>();
    String line = null;
    while ((line = reader.readLine())!=null) {
      lines.add(line);
    }
    reader.close();
    return lines;
  }


  public static String getContentAsText(File f) throws Exception {
    return getContentAsText(new FileInputStream(f));
  }
  
  public static String getContentAsText(InputStream is) throws Exception {
    return getContentAsText(is, false); 
  }
  
  public static String getContentAsText(InputStream is, boolean closeStream) throws Exception {
    List<String> lines = getContentAsTextLines(is);
    StringBuffer text = new StringBuffer("");
    for (String line : lines) {
      text.append(line);
      text.append("\r\n");
    }
    if (closeStream) {
      is.close();
    }
    return text.toString();
  }
  
  /**
   * Solo per file di dimensioni non eccessive.<br/>
   * File compressi di divesi MB che, magari, decompressi
   * corrispondono a 1GB non possono essere gestiti con questo metodo
   * 
   * @param f
   * @return
   * @throws Exception
   */
  public static String unzipFirstEntryAsText(File f) throws Exception {
    return unzipFirstEntryAsText(new FileInputStream(f));
  }

  /**
   * Solo per file di dimensioni non eccessive.<br/>
   * File compressi di divesi MB che, magari, decompressi
   * corrispondono a 1GB non possono essere gestiti con questo metodo
   * 
   * @param is
   * @return
   * @throws Exception
   */
  public static String unzipFirstEntryAsText(InputStream is) throws Exception {
    ZipInputStream zis = new ZipInputStream(is);
    ZipEntry entry = zis.getNextEntry();
    if (entry == null) return null;
    //
    logger.debug("trovata entry "+entry.getName()+" ...");
    if (entry.isDirectory()) {
      logger.debug("l'entry "+entry.getName()+" è una directory: nulla da estrarre");
      return null;
    }
    //
    String text = Filesystem.getContentAsText(zis);
    is.close();
    //
    return text;
  }
  
  /**
   * Chiusura dell'{@link InputStream} a cura del chiamante
   * 
   * @param zippedInputStream {@link InputStream} <i>zippato</i>
   * @return {@link InputStream} <i>unzippato</i>
   * @throws Exception
   */
  public static InputStream getFirstEntryAsInputStream(InputStream zippedInputStream) throws Exception {
    ZipInputStream zis = new ZipInputStream(zippedInputStream);
    ZipEntry entry = zis.getNextEntry();
    if (entry == null) return null;
    //
    logger.debug("trovata entry "+entry.getName()+" ...");
    if (entry.isDirectory()) {
      logger.debug("l'entry "+entry.getName()+" è una directory: nulla da estrarre");
      return null;
    }
    //
    return zis;
  }
  
  public static void store(InputStream is, File f) throws Exception {
    OutputStream os = new FileOutputStream(f);
    byte[] buffer = new byte[8 * 1024];
    int bytesRead;
    while ((bytesRead = is.read(buffer)) != -1) {
        os.write(buffer, 0, bytesRead);
    }
    is.close();
    os.flush();
    os.close();    
    
  }
  
  
  public static List<File> dir(File root , boolean recursive) throws Exception {
    return dir(root, null, recursive);
  }
  
  private static List<File> dir(File root , List<File> files, boolean recursive) throws Exception {
    if (files == null) files = new ArrayList<File>();
    File [] f = root.listFiles();
    for (int i = 0; i < f.length; i++) {
      if (f[i].isDirectory()) {
        if (recursive) {
          dir(f[i], files, true);
        }
      }
      else {
        files.add(f[i]);
      }
    }
    return files;
  }

  public static void closeWithoutThrowingException(OutputStream os, String aDescriptionForThisStream) {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    if (os == null) return;
    try {
      os.close();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME+"; chiusura dell'istanza di "+OutputStream.class.getName()+" «"+aDescriptionForThisStream+"» non riuscita";
      logger.error(msg);
    }
  }
  
  public static void closeWithoutThrowingException(InputStream is, String aDescriptionForThisStream) {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    if (is == null) return;
    try {
      is.close();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME+"; chiusura dell'istanza di "+OutputStream.class.getName()+" «"+aDescriptionForThisStream+"» non riuscita";
      logger.error(msg);
    }
  }
  
  
  
}
